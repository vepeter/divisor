package my.test.divisor.springboot.mvc.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FilteringOptionTest {

    @Test
    @DisplayName("should return an empty Optional if the provided value is null")
    void testByValueWhenNull() {
        Optional<FilteringOption> result = FilteringOption.byValue(null);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should return an Optional with FilteringOption.EVEN if the provided value is EVEN in lower case")
    void testByValueWhenEvenLowerCase() {
        Optional<FilteringOption> result = FilteringOption.byValue("even");

        assertThat(result).isEqualTo(Optional.of(FilteringOption.EVEN));
    }

    @Test
    @DisplayName("should return an Optional with FilteringOption.EVEN if the provided value is EVEN in upper case")
    void testByValueWhenEvenUpperCase() {
        Optional<FilteringOption> result = FilteringOption.byValue("EVEN");

        assertThat(result).isEqualTo(Optional.of(FilteringOption.EVEN));
    }

    @Test
    @DisplayName("should return an Optional with FilteringOption.EVEN if the provided value is EVEN in mixed case")
    void testByValueWhenEvenMixedCase() {
        Optional<FilteringOption> result = FilteringOption.byValue("eVeN");

        assertThat(result).isEqualTo(Optional.of(FilteringOption.EVEN));
    }

    @Test
    @DisplayName("should return an Optional with FilteringOption.ODD if the provided value is ODD in lower case")
    void testByValueWhenOddLowerCase() {
        Optional<FilteringOption> result = FilteringOption.byValue("odd");

        assertThat(result).isEqualTo(Optional.of(FilteringOption.ODD));
    }

    @Test
    @DisplayName("should return an Optional with FilteringOption.ODD if the provided value is ODD in upper case")
    void testByValueWhenOddUpperCase() {
        Optional<FilteringOption> result = FilteringOption.byValue("ODD");

        assertThat(result).isEqualTo(Optional.of(FilteringOption.ODD));
    }

    @Test
    @DisplayName("should return an Optional with FilteringOption.ODD if the provided value is ODD in mixed case")
    void testByValueWhenOddMixedCase() {
        Optional<FilteringOption> result = FilteringOption.byValue("oDd");

        assertThat(result).isEqualTo(Optional.of(FilteringOption.ODD));
    }

    @Test
    @DisplayName("should return empty Optional if the provided value is unknown")
    void testByValueWhenUnknownValue() {
        Optional<FilteringOption> result = FilteringOption.byValue("unknown");

        assertThat(result).isEmpty();
    }
}