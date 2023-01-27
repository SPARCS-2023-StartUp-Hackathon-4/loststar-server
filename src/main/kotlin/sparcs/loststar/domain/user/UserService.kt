package sparcs.loststar.domain.user

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import sparcs.loststar.config.jwt.JwtProvider
import sparcs.loststar.config.jwt.TokenDto
import sparcs.loststar.config.security.SecurityUtils
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) {

    fun login(loginRequest: LoginRequest): TokenDto {
        val loginToken = loginRequest.toAuthenticationToken()
        val authenticate = authenticationManagerBuilder.getObject().authenticate(loginToken)
        return jwtProvider.generateToken(authenticate)
    }

    fun signup(signRequest: SignRequest): TokenDto {
        val nicknameRandom = getRandomNickname()
        when {
            !isDuplicateNickname(nicknameRandom) -> {
                val user = User(
                    email = signRequest.email,
                    password = passwordEncoder.encode(signRequest.email + "1234"),
                    address = signRequest.address,
                    profile = signRequest.profile,
                    nickname = nicknameRandom,
                    fcmToken = ""
                )
                val signUser = userRepository.save(user)
                return login(signUser.toUserDto().toLoginRequest())

            }
            else -> throw RuntimeException()
        }
    }

    fun getRandomNickname(): String {
        val url = "https://nickname.hwanmoo.kr/?format=json&count=1&max_length=6"
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val request = HttpEntity<MultiValueMap<String, String>>(headers)
        val response: ResponseEntity<Map<String, Object>> =
            RestTemplate().exchange(
                url,
                HttpMethod.GET,
                request,
                object : ParameterizedTypeReference<Map<String, Object>>() {})
        val stringObjectMap = Objects.requireNonNull(response.body)
        return stringObjectMap!!["words"].toString().replace("\\[".toRegex(), "").replace("]".toRegex(), "")
    }

    fun isDuplicateNickname(nickname: String) = userRepository.existsByNickname(nickname)

    fun getLoginUser(): User = userRepository
        .findByEmail(SecurityUtils.currentAccountEmail).orElseThrow()


}