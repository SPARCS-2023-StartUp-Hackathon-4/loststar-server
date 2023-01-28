package sparcs.loststar.util.notification

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import sparcs.loststar.domain.LostFoundService

@RestController
class NotificationController(
    private val notificationService: NotificationService,
    private val lostFoundService: LostFoundService,
) {

    @PostMapping("/notify-all")
    fun useSpeaker(@RequestBody useSpeakerRequest: UseSpeakerRequest) {
        val lostFound = lostFoundService.getEntity(useSpeakerRequest.id)
        notificationService.notifyAll(
            lostFound.location,
            lostFound.locationDetail,
            lostFound.title
        )
    }
}