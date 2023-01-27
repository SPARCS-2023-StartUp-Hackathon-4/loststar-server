package sparcs.loststar.domain.found

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.support.PageableExecutionUtils
import sparcs.loststar.common.Category
import sparcs.loststar.domain.found.QFound.found

interface FoundRepository : JpaRepository<Found, Long>, FoundRepositoryCustom {
}

interface FoundRepositoryCustom {
    fun findFounds(category: Category, page: Int, size: Int): Page<Found>
}

class FoundRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : FoundRepositoryCustom {
    override fun findFounds(category: Category, page: Int, size: Int): Page<Found> {
        val pageable = PageRequest.of(page, size)
        return PageableExecutionUtils.getPage(
            queryFactory.selectFrom(found)
                .where(found.category.eq(category))
                .orderBy(found.createdAt.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch(),
            pageable
        ) {
            queryFactory.selectFrom(found)
                .where(found.category.eq(category))
                .fetch().size.toLong()
        }
    }
}