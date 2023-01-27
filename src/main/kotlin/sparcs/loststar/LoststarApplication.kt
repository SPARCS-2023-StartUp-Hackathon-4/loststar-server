package sparcs.loststar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class LoststarApplication

fun main(args: Array<String>) {
	runApplication<LoststarApplication>(*args)
}
