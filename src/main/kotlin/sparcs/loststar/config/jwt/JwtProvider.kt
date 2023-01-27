package sparcs.loststar.config.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import sparcs.loststar.config.security.PrincipalDetailService
import java.security.Key
import java.util.*
import java.util.stream.Collectors

@Component
class JwtProvider(
    private val PrincipalDetailService: PrincipalDetailService,
    @Value("\${jwt.secret}")
    private val secretKey: String
) {
    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun generateToken(authentication: Authentication): TokenDto {
        val authenticate = authentication.authorities.stream()
            .map { obj -> obj.authority }
            .collect(Collectors.joining(","))

        val now = Date().time
        val accessTokenExpiresIn = Date(now + TokenInfo.accessTokenExpireTime)

        val accessToken = Jwts.builder()
            .setSubject(authentication.name)
            .claim(TokenInfo.authoritiesKey, authenticate)
            .setExpiration(accessTokenExpiresIn)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        val refreshToken = Jwts.builder()
            .setExpiration(Date(now + TokenInfo.refreshTokenExpireTime))
            .setSubject(authentication.name)
            .claim(TokenInfo.authoritiesKey, authenticate)
            .signWith(key,  SignatureAlgorithm.HS256)
            .compact()

        return TokenDto(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun validateToken(token: String?): Boolean {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            ?: return false
        return claims.body.expiration.after(Date())
    }

    fun getAuthentication(token: String): Authentication? {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        val principal = PrincipalDetailService.loadUserByUsername(claims.subject)
        val authorities : Collection<SimpleGrantedAuthority> = claims[TokenInfo.authoritiesKey].toString().split(",").stream()
            .map { obj -> SimpleGrantedAuthority(obj) }.collect(Collectors.toList())
        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }
}