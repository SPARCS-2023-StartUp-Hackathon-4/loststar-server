package sparcs.loststar.util

import org.springframework.data.domain.Page
import sparcs.loststar.common.Category
import sparcs.loststar.common.PageResponse
import sparcs.loststar.util.DateFormatter.getFormatter
import java.time.LocalDateTime

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, getFormatter())
fun LocalDateTime.toMyString(): String = this.format(getFormatter())

fun <T> Page<T>.toPageResponse(): PageResponse<T> = PageResponse(this)
