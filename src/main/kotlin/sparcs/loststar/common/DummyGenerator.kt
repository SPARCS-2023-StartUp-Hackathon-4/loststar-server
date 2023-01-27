package sparcs.loststar.common

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.domain.lost.LostRequest
import sparcs.loststar.domain.lost.LostService
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserRepository
import sparcs.loststar.domain.user.UserService
import javax.annotation.PostConstruct
import kotlin.random.Random

@Component
@Transactional
class DummyGenerator(
    private val lostService: LostService,
    private val userService: UserService,
    private val userRepository: UserRepository,
) {

    @PostConstruct
    fun init() {
        val admin = userRepository.save(User(
            email = "admin",
            password = "1234"
        ))
        val categories = mutableListOf(
            Category.가방,
            Category.의류,
            Category.휴대폰
        )
        repeat(25) {
            val category = categories.random()
            val form = LostRequest(
                title = "$category 제목$it",
                category = category,
                location = "위치$it",
                locationDetail = "상세주소$it",
                lostDate = "2023.01.28",
                lostTime = "22시경",
                link = "https://okky.kr",
                image = "$category 이미지$it",
                description = "$category 설명$it",
                reward = Random.nextInt(200),
                useBoost = true,
                useSpeaker = false
            )
            lostService.createLost(admin, form)
        }
    }

}