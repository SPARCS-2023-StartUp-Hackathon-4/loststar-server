package sparcs.loststar.domain

import org.springframework.web.bind.annotation.*
import sparcs.loststar.common.IdResponse
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.user.UserService

@RestController
@RequestMapping("/lost-found")
class LostFoundController(
    private val lostFoundService: LostFoundService,
    private val userService: UserService
) {
    @PostMapping
    fun create(@RequestBody lostFoundRequest: LostFoundRequest): IdResponse =
        lostFoundService.create(
            userService.getLoginUser(), lostFoundRequest
        )

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = lostFoundService.get(id)

    @GetMapping
    fun getList(@ModelAttribute pageParams: PageParams): PageResponse<CardResponse> =
        lostFoundService.getList(pageParams)

    @GetMapping("/boosts")
    fun getBoosts(@RequestParam type: LostFoundType): ListResponse =
        lostFoundService.getBoosts(type)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody foundRequest: LostFoundRequest
    ): IdResponse = lostFoundService.update(id, foundRequest)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): IdResponse = lostFoundService.delete(id)

    /**
     * lost only
     */
    @PutMapping("/{id}/resolve")
    fun resolveFound(
        @PathVariable id: Long,
        @RequestBody resolveRequest: ResolveRequest
    ): IdResponse = lostFoundService.resolve(id, resolveRequest)
}