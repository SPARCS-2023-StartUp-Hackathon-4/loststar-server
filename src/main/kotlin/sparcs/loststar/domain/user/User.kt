package sparcs.loststar.domain.user

import sparcs.loststar.domain.LostFound
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
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER,
    var location: String = "",
    var anchorStar: Int = 0,
    var starPiece: Int = 0,
    var boostItem: Int = 0,
    var speakerItem: Int = 0,
    var fcmToken: String = "",
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lostFoundList: MutableList<LostFound> = mutableListOf()
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

    fun addLostFound(lostFound: LostFound) {
        lostFoundList.add(lostFound)
    }

    enum class Role {
        USER, ADMIN
    }
}