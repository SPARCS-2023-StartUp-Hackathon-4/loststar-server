package sparcs.loststar.util.fcm

data class MessageDto(
    val targetToken: String,
    val title: String,
    val body: String
)