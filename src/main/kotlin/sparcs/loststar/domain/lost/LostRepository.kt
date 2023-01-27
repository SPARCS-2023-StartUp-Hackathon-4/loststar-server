package sparcs.loststar.domain.lost

import org.springframework.data.jpa.repository.JpaRepository

interface LostRepository : JpaRepository<Lost, Long> {
}