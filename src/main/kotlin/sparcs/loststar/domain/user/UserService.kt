package sparcs.loststar.domain.user

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import sparcs.loststar.config.jwt.JwtProvider
import sparcs.loststar.config.jwt.TokenDto

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) {

    fun login(loginRequest: LoginRequest) : TokenDto{
        val loginRequest = loginRequest.toAuthenticationToken()
        val authenticate = authenticationManagerBuilder.getObject().authenticate(loginRequest)
        return jwtProvider.generateToken(authenticate)
    }

    fun signup(signRequest : SignRequest) : TokenDto{
        val user = User(
            email = signRequest.email,
            password = passwordEncoder.encode(signRequest.email + "1234"),
            address = signRequest.address,
            profile = signRequest.profile,
            nickname = "",
            fcmToken = ""
        )
        val signUser = userRepository.save(user)
        return login(signUser.toUserDto().toLoginRequest())
    }

}