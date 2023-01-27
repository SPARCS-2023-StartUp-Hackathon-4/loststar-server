package sparcs.loststar.util.fcm


data class FcmMessage(
    val validateOnly: Boolean = false,
    val message: Message
)

data class Message(
    val notification: Notification,
    val token: String
)

data class Notification(
    val title: String,
    val body: String
)