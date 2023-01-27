package sparcs.loststar.config.jwt

object TokenInfo {
    const val authoritiesKey: String = "AUTH"
    const val accessTokenExpireTime: Long = 1000 * 60 * 60 * 24 * 7 * 7L
    const val refreshTokenExpireTime: Long = 1000 * 60 * 60 * 24 * 7 * 100L
}