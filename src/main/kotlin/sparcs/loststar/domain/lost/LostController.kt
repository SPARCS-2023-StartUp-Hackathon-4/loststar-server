package sparcs.loststar.domain.lost

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/losts")
class LostController(private val lostService: LostService) {

    @PostMapping
    fun createLost(@RequestBody lostRequest: LostRequest): Long = lostService.createLost(lostRequest)

}