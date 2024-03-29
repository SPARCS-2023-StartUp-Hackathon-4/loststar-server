package sparcs.loststar.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.common.IdResponse
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.store.LogMoney
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserRepository
import sparcs.loststar.util.notification.NotificationService
import sparcs.loststar.util.toPageResponse
import sparcs.loststar.util.toResponse

@Service
@Transactional(readOnly = true)
class LostFoundService(
    private val lostFoundRepository: LostFoundRepository,
    private val notificationService: NotificationService,
    private val userRepository: UserRepository
) {

    @Transactional
    fun create(user: User, lostFoundRequest: LostFoundRequest): IdResponse {
        val lostFound = lostFoundRepository.save(lostFoundRequest.toEntity(user))
        user.addLostFound(lostFound)
        lostFound.setBoostEndDateTime()
        return lostFound.id.toResponse()
    }

    fun getList(pageParams: PageParams): PageResponse<CardResponse> {
        return lostFoundRepository.getList(pageParams)
            .map { CardResponse(it) }
            .toPageResponse()
    }

    fun getBoosts(type: LostFoundType): ListResponse {
        return lostFoundRepository.getBoosts(type)
            .map { CardResponse(it) }
            .toResponse()
    }

    fun get(id: Long): LostFoundResponse {
        val lostFound = lostFoundRepository.findById(id).orElseThrow()
        return LostFoundResponse(lostFound, lostFound.user!!)
    }

    fun getEntity(id: Long): LostFound {
        return lostFoundRepository.findById(id).orElseThrow()
    }

    @Transactional
    fun update(id: Long, lostFoundRequest: LostFoundRequest): IdResponse {
        val lost = lostFoundRepository.findById(id).orElseThrow()
        lost.update(lostFoundRequest)
        return lost.id.toResponse()
    }

    @Transactional
    fun delete(id: Long): IdResponse {
        lostFoundRepository.deleteById(id)
        return id.toResponse()
    }

    /**
     * lost only
     */
    @LogMoney
    @Transactional
    fun resolve(id: Long, resolveRequest: ResolveRequest): IdResponse {
        val lost = lostFoundRepository.findByType(id, LostFoundType.LOST).orElseThrow()
        val finder = userRepository.findById(resolveRequest.userId).orElseThrow()
        lost.resolve()
        finder.starPiece += resolveRequest.reward
        return id.toResponse()
    }
}