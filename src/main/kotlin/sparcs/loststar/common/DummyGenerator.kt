package sparcs.loststar.common

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.domain.lost.LostRequest
import sparcs.loststar.domain.lost.LostService
import sparcs.loststar.domain.user.SignRequest
import sparcs.loststar.domain.user.UserRepository
import sparcs.loststar.domain.user.UserService
import javax.annotation.PostConstruct

@Component
@Transactional
class DummyGenerator(
    private val lostService: LostService,
    private val userService: UserService,
    private val userRepository: UserRepository,
) {

    @PostConstruct
    fun init() {
        userService.signUp(SignRequest("admin", "address", ""))
        repeat(13) {
            val form = LostRequest(
                title = "제목$it",
                category = Category.가방,
                location = "위치$it",
                locationDetail = "상세주소$it",
                lostAt = "2020-01-01 00:00:00",
                link = "https://okky.kr",
                image = "이미지$it",
                description = "설명$it",
                reward = 100,
                useBoost = true,
                useSpeaker = false
            )
//            lostService.createLost(admin,form)
        }
    }

}