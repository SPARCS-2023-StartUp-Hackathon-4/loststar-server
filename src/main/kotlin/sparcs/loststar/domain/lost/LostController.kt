package sparcs.loststar.domain.lost

import org.springframework.web.bind.annotation.*
import sparcs.loststar.common.Category
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.user.UserService

@RestController
@RequestMapping("/losts")
class LostController(
    private val lostService: LostService,
    private val userService: UserService) {

    @PostMapping
    fun createLost(@RequestBody lostRequest: LostRequest): Long =
        lostService.createLost(userService.getLoginUser(), lostRequest)

    @GetMapping("/{id}")
    fun getLost(@PathVariable id: Long): LostResponse = lostService.getLost(id)

    @GetMapping
    fun getLostList(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam category: Category
    ): PageResponse<LostCardResponse> = lostService.getLostList(category, page, size)


    @PutMapping("/{id}")
    fun updateLost(
        @PathVariable id: Long,
        @RequestBody lostRequest: LostRequest
    ): Long = lostService.updateLost(id, lostRequest)

    @PutMapping("/{id}/resolve")
    fun resolve(@PathVariable id: Long): Long = lostService.resolve(id)

    @DeleteMapping("/{id}")
    fun deleteLost(@PathVariable id: Long) = lostService.deleteLost(id)

    /**
     * finders
     */
    @PostMapping("/{lostId}/finders/{userId}")
    fun addFinder(
        @PathVariable("lostId") lostId: Long,
        @PathVariable("userId") userId: Long
    ): Long = lostService.addFinder(lostId, userId)
}