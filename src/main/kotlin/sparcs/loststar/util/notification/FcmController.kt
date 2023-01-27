package sparcs.loststar.util.notification

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