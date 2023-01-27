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
    var name: String,

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
    var boostEndDateTime: String = "" // 궤도 진입 추진기 종료 일시

) : BaseTimeEntity() {
    fun setBoostEndDateTime() {
        if (boost) {
            boostEndDateTime = createdAt.toLocalDateTime()
                .plusDays(7).toMyString()
        }
    }

}