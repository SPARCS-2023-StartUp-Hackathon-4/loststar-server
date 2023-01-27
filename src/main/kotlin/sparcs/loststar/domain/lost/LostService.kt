package sparcs.loststar.domain.lost

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LostService(private val lostRepository: LostRepository) {

    @Transactional
    fun createLost(lostRequest: LostRequest): Long {
        val lost = lostRepository.save(lostRequest.toEntity())
        lost.setBoostEndDateTime()
        return lost.id
    }
}