package sparcs.loststar.config.jwt

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import sparcs.loststar.util.logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    val log = logger()
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.warn("로그인 되어 있지 않음")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }
}