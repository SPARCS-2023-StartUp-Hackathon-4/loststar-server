package sparcs.loststar.domain.user

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

data class UserDto (
    val id: Long,
    val email: String,
    val nickname : String,
    val address: String,
    val profile : String,
    val fcmToken : String,
    val anchorStar : Int,
    val starPiece : Int,
    val boostItem : Int,
    val speakerItem : Int
){
    fun toLoginRequest(): LoginRequest {
        return LoginRequest(email, password = email + "1234")
    }

}

data class LoginRequest(
    val email: String,
    val password: String
){
    fun toAuthenticationToken(): UsernamePasswordAuthenticationToken {
       return UsernamePasswordAuthenticationToken(email, password)
    }
}

data class SignRequest(
    val email: String,
    val address: String,
    val profile: String
)

data class KakaoLoginRequest(
    val accessToken: String,
    val address: String,
    val profile: String
)