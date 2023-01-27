package sparcs.loststar.domain.lost

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.common.Category
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserRepository
import sparcs.loststar.util.notification.NotificationService
import sparcs.loststar.util.toPageResponse

@Service
@Transactional(readOnly = true)
class LostService(
    private val lostRepository: LostRepository,
    private val userRepository: UserRepository,
    private val notificationService: NotificationService
) {

    @Transactional
    fun createLost(user: User, lostRequest: LostRequest): Long {
        val lost = lostRepository.save(lostRequest.toEntity(user))
        user.addLost(lost)
        if (lostRequest.useBoost) {
            notificationService.notifyAll(lostRequest.location, lostRequest.locationDetail)
        }
        lost.setBoostEndDateTime()
        return lost.id
    }

    fun getLostList(category: Category, page: Int, size: Int): PageResponse<LostCardResponse> {
        return lostRepository.findLosts(category, page, size)
            .map { LostCardResponse(it) }
            .toPageResponse()
    }

    fun getLost(id: Long) = LostResponse(
        lostRepository.findById(id).orElseThrow()
    )

    @Transactional
    fun updateLost(id: Long, lostRequest: LostRequest): Long {
        val lost = lostRepository.findById(id).orElseThrow()
        lost.update(lostRequest)
        return lost.id
    }

    @Transactional
    fun resolve(id: Long): Long {
        val lost = lostRepository.findById(id).orElseThrow()
        lost.resolve()
        return id
    }

    @Transactional
    fun deleteLost(id: Long): Long {
        lostRepository.deleteById(id)
        return id
    }

    /**
     * finders
     */
    @Transactional
    fun addFinder(lostId: Long, userId: Long): Long {
        val lost = lostRepository.findById(lostId).orElseThrow()
        val user = userRepository.findById(userId).orElseThrow()
        if (lost.finderList.any { it.user.id == userId })
            throw RuntimeException(DUPLICATE_FINDER)
        lost.addFinder(Finder(user = user, lost = lost))
        return lost.id
    }
}