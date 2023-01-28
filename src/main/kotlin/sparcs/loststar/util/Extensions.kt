package sparcs.loststar.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import sparcs.loststar.common.IdResponse
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.CardResponse
import sparcs.loststar.domain.ListResponse
import sparcs.loststar.util.DateFormatter.getFormatter
import java.time.LocalDateTime

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, getFormatter())
fun LocalDateTime.toMyString(): String = this.format(getFormatter())

fun <T> Page<T>.toPageResponse(): PageResponse<T> = PageResponse(this)


fun Long.toResponse(): IdResponse = IdResponse(this)
fun List<CardResponse>.toResponse(): ListResponse = ListResponse(this)