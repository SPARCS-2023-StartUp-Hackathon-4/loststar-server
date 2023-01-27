package sparcs.loststar.common

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import sparcs.loststar.util.logger

@ControllerAdvice
class GlobalExceptionHandler {
    val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String {
        log.warn("{}", e.message)
        return e.message ?: "error"
    }
}