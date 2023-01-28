package sparcs.loststar.domain


import sparcs.loststar.common.Category
import sparcs.loststar.common.Location
import sparcs.loststar.domain.user.User
import sparcs.loststar.domain.user.UserDto

data class PageParams(
    val type: LostFoundType,
    val category: Category? = null,
    val location: Location,
    val page: Int? = 0,
    val size: Int? = 10,
)

data class LostFoundRequest(
    val type: LostFoundType,
    val title: String,
    val category: Category,
    val location: String,
    val locationDetail: String,
    val date: String,
    val time: String,
    val image: String,
    val description: String,
    val reward: Int = 0,
    val boost: Boolean,
) {
    fun toEntity(user: User) = LostFound(
        title = title,
        category = category,
        location = location,
        locationDetail = locationDetail,
        date = date,
        time = time,
        image = image,
        description = description,
        boost = boost,
        reward = reward,
        type = type,
        user = user,
    )
}


data class LostFoundResponse(
    val writer: UserDto,
    val title: String,
    val category: Category,
    val location: String,
    val locationDetail: String,
    val date: String,
    val time: String,
    val image: String,
    val description: String,
    val useBoost: Boolean,
    val reward: Int,
) {
    constructor(lostFound: LostFound, user: User) : this(
        writer = UserDto(user),
        title = lostFound.title,
        category = lostFound.category,
        location = lostFound.location,
        locationDetail = lostFound.locationDetail,
        date = lostFound.date,
        time = lostFound.time,
        image = lostFound.image,
        description = lostFound.description,
        useBoost = lostFound.boost,
        reward = lostFound.reward,
    )
}

data class CardResponse(
    val id: Long,
    val image: String,
    val title: String,
    val location: String,
    val date: String,
    val time: String,
    val boost: Boolean,
    val reward: Int,
) {
    constructor(lostFound: LostFound) : this(
        id = lostFound.id,
        image = lostFound.image,
        title = lostFound.title,
        location = lostFound.location,
        date = lostFound.date,
        time = lostFound.time,
        boost = lostFound.boost,
        reward = lostFound.reward,
    )
}

data class ListResponse(
    val list: List<CardResponse>
)

data class ResolveRequest(
    val userId: Long,
    val reward: Int
)