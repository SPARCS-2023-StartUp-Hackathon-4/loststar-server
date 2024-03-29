package sparcs.loststar.domain.user

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import sparcs.loststar.config.jwt.JwtProvider
import sparcs.loststar.config.jwt.TokenDto
import sparcs.loststar.config.security.SecurityUtils.currentAccountEmail
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun kakaoLogin(kakaoLoginRequest: KakaoLoginRequest) : TokenDto {
        val kakaoDI = getKakaoDI(kakaoLoginRequest.accessToken)
        val user = userRepository.findByEmail(kakaoDI)
        if (user.isPresent) {
            val tokenDto = login(user.get().toUserDto().toLoginRequest())
            user.get().fcmToken = kakaoLoginRequest.fcmToken
            return tokenDto
        } else {
            return signUp(signRequest = SignRequest(kakaoDI, kakaoLoginRequest.address, kakaoLoginRequest.profile))
        }
    }

    fun getKakaoDI(accessToken: String): String {
        val url = "https://kapi.kakao.com/v2/user/me"
        val headers = setHeaders(accessToken)
        val request = HttpEntity<MultiValueMap<String, String>>(headers!!)
        val response = RestTemplate().exchange(url, HttpMethod.GET, request, object : ParameterizedTypeReference<Map<String, Object>>() {})
        val kakaoId: Map<String, Any>? = response.body
        return kakaoId!!["id"].toString()
    }

    fun setHeaders(accessToken : String) : HttpHeaders{
        val headers = HttpHeaders()
        headers["Authorization"] = "Bearer $accessToken"
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        return headers
    }

    fun login(loginRequest: LoginRequest): TokenDto {
        val authenticate = authenticationManagerBuilder.getObject()
            .authenticate(loginRequest.toAuthenticationToken())
        return jwtProvider.generateToken(authenticate)
    }

    fun signUp(signRequest: SignRequest): TokenDto {
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

    fun isDuplicateNickname(nickname: String): Boolean {
        // 있으면 true, 없으면 false
        return userRepository.existsByNickname(nickname)
    }

    fun getLoginUser(): User = userRepository
        .findByEmail(currentAccountEmail).orElseThrow()

    fun getUser(email: String): User = userRepository
        .findByEmail(email).orElseThrow()
    fun getMyInfo(): UserDto {
        return userRepository.findByEmail(currentAccountEmail).get().toUserDto()
    }

    fun getUserInfo(userId: Long): UserDto {
        return userRepository.findById(userId).get().toUserDto()
    }

    fun updateProfile(userDto: ProfileRequest): UserDto {
        val user = userRepository.findByEmail(currentAccountEmail).get()
        user.address = userDto.address
        user.profile = userDto.profile

        val saveUser = userRepository.save(user)
        return saveUser.toUserDto()
    }

    fun updateAddress(address: String) : UserDto{
        val user = userRepository.findByEmail(currentAccountEmail).get()
        user.address = address
        val saveUser = userRepository.save(user)
        return saveUser.toUserDto()
    }

}