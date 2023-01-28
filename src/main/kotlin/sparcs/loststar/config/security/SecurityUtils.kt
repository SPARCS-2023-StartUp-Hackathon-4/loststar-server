package sparcs.loststar.config.security

import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtils {
    val currentAccountEmail: String
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication == null || authentication.name == null) {
                throw RuntimeException("로그인 되어있지 않습니다.")
            }
            return authentication.name
        }
}