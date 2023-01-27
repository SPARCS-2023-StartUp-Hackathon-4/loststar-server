package sparcs.loststar.domain.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import sparcs.loststar.config.jwt.TokenDto

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): TokenDto {
        return userService.login(loginRequest)
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signRequest: SignRequest): TokenDto {
        return userService.signup(signRequest)
    }

    @GetMapping("/nickname")
    fun nicknameRandom(): String {
        return userService.getRandomNickname()
    }
}