package sparcs.loststar.common

import org.springframework.data.domain.Page

data class PageResponse<T>(
    val totalPages: Int,
    val totalElements: Long,
    val last: Boolean,
    val content: List<T>
) {
    constructor(page: Page<T>) : this(
        totalPages = page.totalPages,
        totalElements = page.totalElements,
        last = page.isLast,
        content = page.content
    )
}