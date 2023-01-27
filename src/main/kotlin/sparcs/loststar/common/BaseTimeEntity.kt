package sparcs.loststar.common

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import sparcs.loststar.util.DateFormatter
import sparcs.loststar.util.DateFormatter.getFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {

    var createdAt: String = ""
    var updateAt: String = ""

    private fun setTime() = LocalDateTime.now().format(getFormatter())

    @PrePersist
    fun prePersist() {
        createdAt = setTime()
        updateAt = setTime()
    }

    @PreUpdate
    fun preUpdate() {
        updateAt = setTime()
    }
}