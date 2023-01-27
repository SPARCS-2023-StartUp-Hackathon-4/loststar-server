package sparcs.loststar.common

import org.springframework.stereotype.Component
import sparcs.loststar.domain.lost.LostService
import javax.annotation.PostConstruct

@Component
class DummyGenerator(
    private val lostService: LostService,
) {

    @PostConstruct
    fun init() {
    }

}