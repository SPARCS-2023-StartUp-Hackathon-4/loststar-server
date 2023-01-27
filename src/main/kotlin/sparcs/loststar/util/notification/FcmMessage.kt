package sparcs.loststar.util.notification


data class FcmMessage(
    val validateOnly: Boolean = false,
    val message: Message
)

data class Message(
    val data: Notification,
    val token: String
)

data class Notification(
    val title: String,
    val body: String
)