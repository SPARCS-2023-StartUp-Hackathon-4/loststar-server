package sparcs.loststar.common

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.domain.LostFoundService
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserRepository
import javax.annotation.PostConstruct

@Component
@Transactional
class DummyGenerator(
    private val lostFoundService: LostFoundService,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @PostConstruct
    fun init() {
        val admin = userRepository.save(User(
            email = "admin",
            password = passwordEncoder.encode("1234"),
            starPiece = 1000
        ))
        repeat(30) {
            lostFoundService.create(admin, getLostFoundDummyFormList().random())
        }
    }

}
