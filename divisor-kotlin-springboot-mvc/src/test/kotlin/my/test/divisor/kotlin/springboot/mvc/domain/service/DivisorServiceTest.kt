package my.test.divisor.kotlin.springboot.mvc.domain.service

import my.test.divisor.kotlin.springboot.mvc.domain.model.FilteringOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DivisorServiceTest {

    @Test
    fun `should generate proper divisors when no filtering option is provided`() {
        val result = DivisorService(1, 20).findDivisor(20, null)

        assertThat(result).containsExactly(1, 2, 4, 5, 10, 20)
    }

    @Test
    fun `should generate proper divisors when the ODD filtering option is provided`() {
        val result = DivisorService(1, 10).findDivisor(20, FilteringOption.ODD)

        assertThat(result).containsExactly(1, 5)
    }

    @Test
    fun `should generate proper divisors when the EVEN filtering option is provided`() {
        val result = DivisorService(1, 10).findDivisor(20, FilteringOption.EVEN)

        assertThat(result).containsExactly(2, 4, 10)
    }
}