package sparcs.loststar.domain

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.support.PageableExecutionUtils
import sparcs.loststar.common.GlobalConstants.OFFSET_DEFAULT
import sparcs.loststar.common.GlobalConstants.PAGE_SIZE_DEFAULT
import sparcs.loststar.domain.QLostFound.lostFound
import java.util.*

interface LostFoundRepository : JpaRepository<LostFound, Long>, LostFoundRepositoryCustom {
    @Query("SELECT lf FROM LostFound lf WHERE lf.type = :type AND lf.id = :id")
    fun findByType(@Param("id") id: Long, @Param("type") type: LostFoundType): Optional<LostFound>
}

interface LostFoundRepositoryCustom {
    fun getList(pageParams: PageParams): Page<LostFound>
    fun getBoosts(type: LostFoundType): List<LostFound>
}


class LostFoundRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : LostFoundRepositoryCustom {
    override fun getList(pageParams: PageParams): Page<LostFound> {
        val page: Int = pageParams.page ?: OFFSET_DEFAULT
        val size: Int = pageParams.size ?: PAGE_SIZE_DEFAULT

        val categoryCondition = pageParams.category?.let {
            lostFound.category.eq(pageParams.category)
        }

        val pageable = PageRequest.of(page, size)

        val query = queryFactory.selectFrom(lostFound)
            .where(
                categoryCondition,
                lostFound.type.eq(pageParams.type),
                lostFound.location.eq(pageParams.location.name)
            )

        return PageableExecutionUtils.getPage(
            query
                .orderBy(lostFound.createdAt.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch(),
            pageable
        ) {
            query.fetch().size.toLong()
        }
    }

    override fun getBoosts(type: LostFoundType): List<LostFound> {
        return queryFactory.selectFrom(lostFound)
            .where(
                lostFound.type.eq(type),
                lostFound.boost.eq(true)
            )
            .orderBy(lostFound.createdAt.desc())
            .limit(PAGE_SIZE_DEFAULT.toLong())
            .fetch()
    }
}