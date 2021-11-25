package my.test.divisor.java.springboot.webflux.domain.service;

import my.test.divisor.java.springboot.webflux.domain.model.FilteringOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static java.util.Optional.empty;
import static java.util.Optional.of;

class DivisorServiceTest {

    @Test
    @DisplayName("should generate proper divisors when no filtering option is provided")
    void testFindDivisorNoFiltering() {
        Flux<Integer> result = new DivisorService(1, 20).findDivisor(20, empty());

        StepVerifier.create(result)
                .expectNext(1)
                .expectNext(2)
                .expectNext(4)
                .expectNext(5)
                .expectNext(10)
                .expectNext(20)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should generate proper divisors when the ODD filtering option is provided")
    void testFindDivisorWithOddFiltering() {
        Flux<Integer> result = new DivisorService(1, 10).findDivisor(20, of(FilteringOption.ODD));

        StepVerifier.create(result)
                .expectNext(1)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should generate proper divisors when the EVEN filtering option is provided")
    void testFindDivisorWithEvenFiltering() {
        Flux<Integer> result = new DivisorService(1, 10).findDivisor(20, of(FilteringOption.EVEN));

        StepVerifier.create(result)
                .expectNext(2)
                .expectNext(4)
                .expectNext(10)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("should generate empty stream when null number is provided")
    void testFindDivisorWithNullNumberValue() {
        Flux<Integer> result = new DivisorService(1, 20).findDivisor(null, of(FilteringOption.EVEN));

        StepVerifier.create(result).expectNoEvent(Duration.ofMillis(100));
    }
}