package sparcs.loststar.domain.store

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import sparcs.loststar.domain.user.UserService
import sparcs.loststar.util.logger

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogMoney()

@Aspect
@Component
class MoneyLogAspect(private val userService: UserService) {

    private val log = logger()

    @Around("@annotation(LogMoney)")
    fun logMoney(joinPoint: ProceedingJoinPoint): Any? {
        val user = userService.getLoginUser()
        val starPiece = user.starPiece
        val anchorStar = user.anchorStar
        log.info("before 별조각={}, 닻별={}", starPiece, anchorStar)
        val result = joinPoint.proceed()
        log.info("after 별조각={}, 닻별={}", user.starPiece, user.anchorStar)
        return result
    }
}