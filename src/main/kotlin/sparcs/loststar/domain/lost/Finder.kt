package sparcs.loststar.domain.lost

import sparcs.loststar.domain.user.User
import javax.persistence.*


@Entity
class Finder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finder_id")
    var id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_id")
    var lost: Lost,

    var selected: Boolean = false
)