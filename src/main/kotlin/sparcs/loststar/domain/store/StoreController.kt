package sparcs.loststar.domain.store

import org.springframework.web.bind.annotation.*

@RestController
class StoreController(private val storeService: StoreService) {

    @PutMapping("/exchange")
    fun exchange(@RequestBody exchangeRequest: ExchangeRequest) =
        storeService.exchange(exchangeRequest)

    @PostMapping("/purchase")
    fun buyItem(@RequestParam item: String) {
        when (item) {
            "boost" -> storeService.buyBoost()
            "speaker" -> storeService.buySpeaker()
        }
    }
}