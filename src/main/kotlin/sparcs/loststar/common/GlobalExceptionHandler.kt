package sparcs.loststar.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import sparcs.loststar.util.logger

@ControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
class GlobalExceptionHandler {
    val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String {
        log.warn("{}", e.message)
        return e.message ?: "error"
    }
}