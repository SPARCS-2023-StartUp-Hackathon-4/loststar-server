package sparcs.loststar.domain.lost

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.support.PageableExecutionUtils
import sparcs.loststar.common.Category
import sparcs.loststar.domain.lost.QLost.lost

interface LostRepository : JpaRepository<Lost, Long>, LostRepositoryCustom {
}

interface LostRepositoryCustom {
    fun findLosts(category: Category, page: Int, size: Int): Page<Lost>
}


class LostRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : LostRepositoryCustom {
    override fun findLosts(category: Category, page: Int, size: Int): Page<Lost> {
        val pageable = PageRequest.of(page, size)
        return PageableExecutionUtils.getPage(
            queryFactory.selectFrom(lost)
                .where(lost.category.eq(category))
                .orderBy(lost.createdAt.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch(),
            pageable
        ) {
            queryFactory.selectFrom(lost)
                .where(lost.category.eq(category))
                .fetch().size.toLong()
        }
    }
}