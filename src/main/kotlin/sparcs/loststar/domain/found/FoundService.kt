package sparcs.loststar.domain.found

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.common.Category
import sparcs.loststar.common.PageResponse
import sparcs.loststar.domain.user.User
import sparcs.loststar.util.toPageResponse

@Service
@Transactional(readOnly = true)
class FoundService(private val foundRepository: FoundRepository) {

    @Transactional
    fun createFound(user: User, foundRequest: FoundRequest): Long {
        val found = foundRepository.save(foundRequest.toEntity(user))
        user.addFound(found)
        return found.id
    }

    fun getFoundList(category: Category, page: Int, size: Int): PageResponse<FoundCardResponse> {
        return foundRepository.findFounds(category, page, size)
            .map { FoundCardResponse(it) }
            .toPageResponse()
    }

    fun getFound(id: Long) = FoundResponse(
        foundRepository.findById(id).orElseThrow()
    )

    @Transactional
    fun updateFound(id: Long, foundRequest: FoundRequest): Long {
        val found = foundRepository.findById(id).orElseThrow()
        found.update(foundRequest)
        return found.id
    }

    @Transactional
    fun deleteFound(id: Long): Long {
        foundRepository.deleteById(id)
        return id
    }

}