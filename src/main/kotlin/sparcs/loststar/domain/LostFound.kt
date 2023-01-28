package sparcs.loststar.domain

import sparcs.loststar.common.BaseTimeEntity
import sparcs.loststar.common.Category
import sparcs.loststar.domain.user.User
import sparcs.loststar.util.toLocalDateTime
import sparcs.loststar.util.toMyString
import javax.persistence.*

@Entity
class LostFound(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_found_id")
    var id: Long = 0L,
    var title: String,

    @Enumerated(EnumType.STRING)
    var category: Category,

    var location: String,
    var locationDetail: String,
    var date: String,
    var time: String,

    var image: String,
    var description: String,

    @Enumerated(EnumType.STRING)
    var type: LostFoundType,
    var boost: Boolean,
    var boostEndDateTime: String = "",

    // only lost
    var reward: Int = 0,
    var resolve: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null,

    ) : BaseTimeEntity() {
    fun setBoostEndDateTime() {
        if (boost) {
            boostEndDateTime = createdAt.toLocalDateTime()
                .plusDays(7).toMyString()
        }
    }

    fun update(lostFoundRequest: LostFoundRequest) {
        title = lostFoundRequest.title
        category = lostFoundRequest.category
        location = lostFoundRequest.location
        locationDetail = lostFoundRequest.locationDetail
        date = lostFoundRequest.date
        time = lostFoundRequest.time
        image = lostFoundRequest.image
        description = lostFoundRequest.description
        reward = lostFoundRequest.reward
        boost = lostFoundRequest.boost
        setBoostEndDateTime()
    }

    fun resolve() {
        resolve = true
    }


}