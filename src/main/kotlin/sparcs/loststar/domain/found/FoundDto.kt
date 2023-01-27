package sparcs.loststar.domain.found

import sparcs.loststar.common.Category
import sparcs.loststar.domain.user.User

data class FoundRequest(
    val title: String,
    val category: Category,
    val location: String,
    val locationDetail: String,
    val foundDate: String,
    val foundTime: String,
    val link: String,
    val image: String,
    val description: String,
    val useBoost: Boolean,
    val useSpeaker: Boolean
) {
    fun toEntity(user: User) = Found(
        title = title,
        category = category,
        location = location,
        locationDetail = locationDetail,
        foundDate = foundDate,
        foundTime = foundTime,
        link = link,
        image = image,
        description = description,
        boost = useBoost,
        user = user,
    )
}


data class FoundResponse(
    val name: String,
    val category: Category,
    val location: String, // 분실지역
    val locationDetail: String, // 상세주소
    val foundDate: String, // 분실날짜
    val foundTime: String, // 분실시간
    val link: String, // 오카방 링크
    val image: String,
    val description: String,
    val useBoost: Boolean, // 궤도 진입 추진기 사용 여부
    val useSpeaker: Boolean // 확성기 사용 여부
) {
    constructor(found: Found) : this(
        name = found.title,
        category = found.category,
        location = found.location,
        locationDetail = found.locationDetail,
        foundDate = found.foundDate,
        foundTime = found.foundTime,
        link = found.link,
        image = found.image,
        description = found.description,
        useBoost = found.boost,
        useSpeaker = false
    )
}

data class FoundCardResponse(
    val id: Long,
    val image: String,
    val title: String,
    val location: String,
    val foundDate: String,
    val foundTime: String,
) {
    constructor(found: Found) : this(
        id = found.id,
        image = found.image,
        title = found.title,
        location = found.location,
        foundDate = found.foundDate,
        foundTime = found.foundTime,
    )
}