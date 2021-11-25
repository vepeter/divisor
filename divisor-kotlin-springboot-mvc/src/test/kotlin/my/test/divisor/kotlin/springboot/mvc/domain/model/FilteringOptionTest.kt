package my.test.divisor.kotlin.springboot.mvc.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FilteringOptionTest {

    @Test
    fun `should return an empty Optional if the provided value is null`() {
        val result = FilteringOption.byValue(null)

        assertThat(result).isNull()
    }

    @Test
    fun `should return an Optional with FilteringOption_EVEN if the provided value is EVEN in lower case`() {
        val result = FilteringOption.byValue("even")

        assertThat(result).isEqualTo(FilteringOption.EVEN)
    }

    @Test
    fun `should return an Optional with FilteringOption_EVEN if the provided value is EVEN in upper case`() {
        val result = FilteringOption.byValue("EVEN")

        assertThat(result).isEqualTo(FilteringOption.EVEN)
    }

    @Test
    fun `should return an Optional with FilteringOption_EVEN if the provided value is EVEN in mixed case`() {
        val result = FilteringOption.byValue("eVeN")

        assertThat(result).isEqualTo(FilteringOption.EVEN)
    }

    @Test
    fun `should return an Optional with FilteringOption_ODD if the provided value is ODD in lower case`() {
        val result = FilteringOption.byValue("odd")

        assertThat(result).isEqualTo(FilteringOption.ODD)
    }

    @Test
    fun `should return an Optional with FilteringOption_ODD if the provided value is ODD in upper case`() {
        val result = FilteringOption.byValue("ODD")

        assertThat(result).isEqualTo(FilteringOption.ODD)
    }

    @Test
    fun `should return an Optional with FilteringOption_ODD if the provided value is ODD in mixed case`() {
        val result = FilteringOption.byValue("oDd")

        assertThat(result).isEqualTo(FilteringOption.ODD)
    }

    @Test
    fun `should return empty Optional if the provided value is unknown`() {
        val result = FilteringOption.byValue("unknown")

        assertThat(result).isNull()
    }
}