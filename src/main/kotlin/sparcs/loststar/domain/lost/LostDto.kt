package sparcs.loststar.domain.lost

import sparcs.loststar.common.Category

data class LostRequest(
    val name: String,
    val category: Category,
    val location: String, // 분실지역
    val locationDetail: String, // 상세주소
    val lostAt: String, // 분실일시
    val link: String, // 오카방 링크
    val image: String,
    val description: String,
    val reward: Int, // 별조각 보상
    val useBoost: Boolean, // 궤도 진입 추진기 사용 여부
    val useSpeaker: Boolean // 확성기 사용 여부
) {
    fun toEntity() = Lost(
        name = name,
        category = category,
        location = location,
        locationDetail = locationDetail,
        lostAt = lostAt,
        link = link,
        image = image,
        description = description,
        reward = reward,
        boost = useBoost
    )
}