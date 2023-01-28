package sparcs.loststar.domain.store

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparcs.loststar.domain.user.UserService
import sparcs.loststar.util.getStarPieceRatio

@Service
@Transactional
class StoreService(
    private val userService: UserService
) {
    @LogMoney
    fun exchange(exchangeRequest: ExchangeRequest) {
        val user = userService.getLoginUser()
        val needStarPieces = exchangeRequest.anchorStarCount.getStarPieceRatio()
        user.exchange(needStarPieces, exchangeRequest.anchorStarCount)
    }

    @LogMoney
    fun buyBoost() = userService.getLoginUser().buyBoost()


    @LogMoney
    fun buySpeaker() = userService.getLoginUser().buySpeaker()
}