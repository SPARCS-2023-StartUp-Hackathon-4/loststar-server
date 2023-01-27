package sparcs.loststar.config.jwt

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)