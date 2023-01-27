package sparcs.loststar.domain.user

import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0L,
    var email: String,
    var password: String,
    var nickname: String,
    var address: String,
    var role: Role = Role.USER,
    var anchorStar : Int = 0,
    var starPiece : Int = 0,
    var boostItem : Int = 0,
    var speakerItem : Int = 0,
    var fcmToken : String
) {

    enum class Role {
        USER, ADMIN
    }


}