package sparcs.loststar.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserRepository
import java.util.*

@Service
class PrincipalDetailService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: Optional<User> = userRepository.findByEmail(username!!)
        return PrincipalDetails(user.get())
    }
}