package sparcs.loststar.domain.lost

import sparcs.loststar.common.BaseTimeEntity
import sparcs.loststar.common.Category
import sparcs.loststar.util.toLocalDateTime
import sparcs.loststar.util.toMyString
import javax.persistence.*

@Entity
class Lost(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lost_id")
    var id: Long = 0L,
    var title: String,

    @Enumerated(EnumType.STRING)
    var category: Category,

    var location: String, // 분실지역
    var locationDetail: String, // 상세주소
    var lostAt: String, // 분실일시

    var link: String, // 오카방 링크
    var image: String,
    var description: String,

    var reward: Int, // 별조각 보상
    var boost: Boolean, // 궤도 진입 추진기 사용 여부
    var boostEndDateTime: String = "", // 궤도 진입 추진기 종료 일시

    var resolve: Boolean = false, // 분실물 해결 여부

    @OneToMany(mappedBy = "lost", cascade = [CascadeType.ALL], orphanRemoval = true)
    var finderList: MutableList<Finder> = mutableListOf(),

    ) : BaseTimeEntity() {
    fun setBoostEndDateTime() {
        if (boost) {
            boostEndDateTime = createdAt.toLocalDateTime()
                .plusDays(7).toMyString()
        }
    }

    fun update(lostRequest: LostRequest) {
        title = lostRequest.title
        category = lostRequest.category
        location = lostRequest.location
        locationDetail = lostRequest.locationDetail
        lostAt = lostRequest.lostAt
        link = lostRequest.link
        image = lostRequest.image
        description = lostRequest.description
        reward = lostRequest.reward
        boost = lostRequest.useBoost
        setBoostEndDateTime()
    }

    fun resolve() {
        resolve = true
    }

    fun addFinder(finder: Finder) {
        finderList.add(finder)
    }

}