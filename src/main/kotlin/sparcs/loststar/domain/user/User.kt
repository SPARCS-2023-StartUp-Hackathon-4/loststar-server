package sparcs.loststar.domain.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val email: String,
    val password: String,
    val nickname: String,
    val address: String,
    val role: Role = Role.USER,
    val anchorStar : Int = 0,
    val starPiece : Int = 0,
    val boostItem : Int = 0,
    val speakerItem : Int = 0,
    val fcmToken : String
) {

    enum class Role {
        USER, ADMIN
    }


}