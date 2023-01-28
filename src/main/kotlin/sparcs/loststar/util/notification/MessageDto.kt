package sparcs.loststar.util.notification

data class MessageDto(
    val targetToken: String,
    val title: String,
    val body: String
)

data class UseSpeakerRequest(
    val id: Long
)