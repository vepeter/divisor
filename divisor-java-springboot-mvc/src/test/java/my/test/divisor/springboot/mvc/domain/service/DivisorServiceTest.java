package my.test.divisor.springboot.mvc.domain.service;

import my.test.divisor.springboot.mvc.domain.model.FilteringOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

class DivisorServiceTest {

    @Test
    @DisplayName("should generate proper divisors when no filtering option is provided")
    void testFindDivisorNoFiltering() {
        List<Integer> result = new DivisorService(1, 20).findDivisor(20, empty());

        assertThat(result).containsExactly(1, 2, 4, 5, 10, 20);
    }

    @Test
    @DisplayName("should generate proper divisors when the ODD filtering option is provided")
    void testFindDivisorWithOddFiltering() {
        List<Integer> result = new DivisorService(1, 10).findDivisor(20, of(FilteringOption.ODD));

        assertThat(result).containsExactly(1, 5);
    }

    @Test
    @DisplayName("should generate proper divisors when the EVEN filtering option is provided")
    void testFindDivisorWithEvenFiltering() {
        List<Integer> result = new DivisorService(1, 10).findDivisor(20, of(FilteringOption.EVEN));

        assertThat(result).containsExactly(2, 4, 10);
    }

    @Test
    @DisplayName("should generate empty list when null number is provided")
    void testFindDivisorWithNullNumberValue() {
        List<Integer> result = new DivisorService(1, 20).findDivisor(null, of(FilteringOption.EVEN));

        assertThat(result).isEmpty();
    }
}