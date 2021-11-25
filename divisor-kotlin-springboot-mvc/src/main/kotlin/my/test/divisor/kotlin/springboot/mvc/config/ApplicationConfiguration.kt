package my.test.divisor.kotlin.springboot.mvc.config

import my.test.divisor.kotlin.springboot.mvc.domain.service.DivisorService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val DIVISOR_RANGE_LOWER_END = 1
private val DIVISOR_RANGE_UPPER_END = 10

@Configuration
class ApplicationConfiguration {

    @Bean
    fun divisorService() = DivisorService(DIVISOR_RANGE_LOWER_END, DIVISOR_RANGE_UPPER_END)
}