package sparcs.loststar.util

import java.time.format.DateTimeFormatter

object DateFormatter {
    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    fun getFormatter(): DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
}
