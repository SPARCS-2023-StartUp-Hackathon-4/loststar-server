package sparcs.loststar.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import sparcs.loststar.util.logger

data class ErrorResponse(
    val errorMessage: String
)

@ControllerAdvice
class GlobalExceptionHandler {
    val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        log.warn("{}", e.message)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message ?: "unknown"))
    }
}