package sparcs.loststar.util.fcm

import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FcmController(private val fcmService: FcmService) {

    @PostMapping("/send")
    fun send(@RequestBody messageDto: MessageDto): MessageDto {
        fcmService.sendDirectTo(
            messageDto.targetToken,
            messageDto.title,
            messageDto.body
        )
        return messageDto
    }
}