package sparcs.loststar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableAsync

@EnableAspectJAutoProxy
@EnableAsync
@SpringBootApplication
class LoststarApplication

fun main(args: Array<String>) {
	runApplication<LoststarApplication>(*args)
}
