package sparcs.loststar.domain.found

import sparcs.loststar.common.BaseTimeEntity
import sparcs.loststar.common.Category
import sparcs.loststar.domain.user.User
import javax.persistence.*

@Entity
class Found(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "found_id")
    var id: Long = 0L,
    var title: String,

    @Enumerated(EnumType.STRING)
    var category: Category,

    var location: String, // 분실지역
    var locationDetail: String, // 상세주소
    var foundDate: String,
    var foundTime: String,

    var link: String, // 오카방 링크
    var image: String,
    var description: String,

    var boost: Boolean, // 궤도 진입 추진기 사용 여부
    var boostEndDateTime: String = "", // 궤도 진입 추진기 종료 일시

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null,
) : BaseTimeEntity() {
    fun update(foundRequest: FoundRequest) {
        title = foundRequest.title
        category = foundRequest.category
        location = foundRequest.location
        locationDetail = foundRequest.locationDetail
        foundDate = foundRequest.foundDate
        foundTime = foundRequest.foundTime
        link = foundRequest.link
        image = foundRequest.image
        description = foundRequest.description
    }
}