package sparcs.loststar.domain.user

import sparcs.loststar.common.GlobalConstants.BOOST_ANCHOR_PRICE
import sparcs.loststar.common.GlobalConstants.LACK_ANCHOR_MESSAGE
import sparcs.loststar.common.GlobalConstants.LACK_STAR_PIECE_MESSAGE
import sparcs.loststar.common.GlobalConstants.SPEAKER_ANCHOR_PRICE
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

    fun exchange(starPiece: Int, anchorStar: Int) {
        if (this.starPiece < starPiece) {
            throw IllegalArgumentException(LACK_STAR_PIECE_MESSAGE)
        }
        this.starPiece -= starPiece
        this.anchorStar += anchorStar
    }

    fun buyBoost() {
        if (this.anchorStar < BOOST_ANCHOR_PRICE) {
            throw IllegalArgumentException(LACK_ANCHOR_MESSAGE)
        }
        this.anchorStar -= BOOST_ANCHOR_PRICE
        this.boostItem += 1
    }

    fun buySpeaker() {
        if (this.anchorStar < SPEAKER_ANCHOR_PRICE) {
            throw IllegalArgumentException(LACK_ANCHOR_MESSAGE)
        }
        this.anchorStar -= SPEAKER_ANCHOR_PRICE
        this.speakerItem += 1
    }

    enum class Role {
        USER, ADMIN
    }
}