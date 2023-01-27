package sparcs.loststar.domain.user

import sparcs.loststar.domain.lost.Lost
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0L,
    var email: String,
    var password: String,
    var nickname: String = "",
    var address: String = "",
    var profile: String = "",
    var role: Role = Role.USER,
    var anchorStar: Int = 0,
    var starPiece: Int = 0,
    var boostItem: Int = 0,
    var speakerItem: Int = 0,
    var fcmToken: String = "",

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lostList: MutableList<Lost> = mutableListOf(),

    ) {
    fun toUserDto(): UserDto {
        return UserDto(
            id = id,
            email = email,
            nickname = nickname,
            address = address,
            profile = profile,
            fcmToken = fcmToken,
            anchorStar = anchorStar,
            starPiece = starPiece,
            boostItem = boostItem,
            speakerItem = speakerItem
        )
    }

    fun addLost(lost: Lost) {
        lostList.add(lost)
    }

    enum class Role {
        USER, ADMIN
    }


}