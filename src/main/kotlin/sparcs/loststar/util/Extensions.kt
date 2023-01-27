package sparcs.loststar.util

import sparcs.loststar.util.DateFormatter.getFormatter
import java.time.LocalDateTime

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, getFormatter())
fun LocalDateTime.toMyString(): String = this.format(getFormatter())
