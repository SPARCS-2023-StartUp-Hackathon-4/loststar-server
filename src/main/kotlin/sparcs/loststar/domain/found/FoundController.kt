package sparcs.loststar.domain.found

import org.springframework.web.bind.annotation.*
import sparcs.loststar.common.Category
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.user.UserService

@RestController
@RequestMapping("/founds")
class FoundController(
    private val foundService: FoundService,
    private val userService: UserService
) {
    @PostMapping
    fun createFound(@RequestBody foundRequest: FoundRequest): Long =
        foundService.createFound(userService.getLoginUser(), foundRequest)

    @GetMapping("/{id}")
    fun getFound(@PathVariable id: Long) = foundService.getFound(id)

    @GetMapping
    fun getFounds(
        @RequestParam category: Category,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): PageResponse<FoundCardResponse> = foundService.getFoundList(category, page, size)

    @PutMapping("/{id}")
    fun updateFound(
        @PathVariable id: Long,
        @RequestBody foundRequest: FoundRequest
    ): Long = foundService.updateFound(id, foundRequest)

    @DeleteMapping("/{id}")
    fun deleteFound(@PathVariable id: Long): Long = foundService.deleteFound(id)
}