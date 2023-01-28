package sparcs.loststar.config.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import sparcs.loststar.domain.user.UserRepository

@Service
class PrincipalDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return PrincipalDetails(userRepository.findByEmail(username).orElseThrow())
    }
}