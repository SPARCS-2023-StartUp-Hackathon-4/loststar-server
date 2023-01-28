package sparcs.loststar.common

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.domain.LostFoundRequest
import sparcs.loststar.domain.LostFoundService
import sparcs.loststar.domain.LostFoundType
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserRepository
import javax.annotation.PostConstruct
import kotlin.random.Random

@Component
@Transactional
class DummyGenerator(
    private val lostFoundService: LostFoundService,
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
        val types = mutableListOf(
            LostFoundType.LOST,
            LostFoundType.FOUND
        )
        val locations = mutableListOf(
            Location.강남,
            Location.강북,
            Location.강동
        )
        repeat(25) {
            val category = categories.random()
            val form = LostFoundRequest(
                title = "$category 제목$it",
                category = category,
                location = locations.random().name,
                locationDetail = "상세주소$it",
                date = "2023.01.28",
                time = "22시경",
                image = "$category 이미지$it",
                description = "$category 설명$it",
                reward = Random.nextInt(200),
                boost = true,
                type = types.random(),
            )
            lostFoundService.create(admin, form)
        }
    }

}
