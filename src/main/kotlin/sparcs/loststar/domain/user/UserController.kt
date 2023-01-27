package sparcs.loststar.domain.user

import org.springframework.web.bind.annotation.*
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
        return userService.signUp(signRequest)
    }

    @GetMapping("/nickname")
    fun nicknameRandom(): String {
        return userService.nicknameRandom()
    }

    @PostMapping("/kakao")
    fun kakaoLogin(@RequestBody kakaoLoginRequest: KakaoLoginRequest): TokenDto {
        return userService.kakaoLogin(kakaoLoginRequest)
    }

    @GetMapping("/users/me")
    fun getMyInfo(): UserDto {
        return userService.getMyInfo()
    }

    @GetMapping("/users/{userId}")
    fun getUserInfo(@PathVariable userId: Long): UserDto {
        return userService.getUserInfo(userId)
    }

    @PutMapping("/users/address")
    fun updateAddress(@RequestBody address: String): UserDto {
        return userService.updateAddress(address)
    }

    @PutMapping("/users/profile")
    fun updateProfile(@RequestBody profile: ProfileRequest): UserDto {
        return userService.updateProfile(profile)
    }
}