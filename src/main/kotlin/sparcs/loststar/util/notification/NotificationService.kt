package sparcs.loststar.util.notification

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import sparcs.loststar.config.security.SecurityUtils
import sparcs.loststar.domain.user.UserRepository

@Service
class NotificationService(
    private val fcmService: FcmService,
    private val userRepository: UserRepository
) {
    @Async
    fun notifyAll(location: String, locationDetail: String, productName: String) {
        userRepository.findAll()
            .filter { it.location.contains(location) }
            .filter { it.fcmToken.length > 1 }
            .forEach { fcmService.sendDirectTo(
                it.fcmToken,
                "\uD83D\uDCE2 서울시 ${location}구민 주목!",
                "${locationDetail}에서 잃어버린 ${productName}을 찾습니다!")
            }
    }

    @Async
    fun notify(userId: Long, chatMessage: String) {
        val receiver = userRepository.findById(userId).orElseThrow()
        val sender = userRepository.findByEmail(SecurityUtils.currentAccountEmail).orElseThrow()
        fcmService.sendDirectTo(receiver.fcmToken,
            "${sender.nickname}채팅 도착!", chatMessage)
    }
}