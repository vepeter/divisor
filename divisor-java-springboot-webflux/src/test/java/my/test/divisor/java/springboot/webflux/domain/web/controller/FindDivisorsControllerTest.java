package my.test.divisor.java.springboot.webflux.domain.web.controller;

import my.test.divisor.java.springboot.webflux.domain.model.FilteringOption;
import my.test.divisor.java.springboot.webflux.domain.service.DivisorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@WebFluxTest(
        controllers = FindDivisorsController.class,
        properties = "spring.main.banner-mode=off")
class FindDivisorsControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private DivisorService divisorService;

    @Test
    @DisplayName("should delegate to DivisorService for finding list of divisors with no filtering option")
    void testGetDivisorsForGivenNumber() throws Exception {
        given(divisorService.findDivisor(any(), any())).willReturn(Flux.just(1, 2, 4, 5, 10));

        webClient.get()
                .uri("/calculator/findDivisors/20")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(("{\"results\":[1,2,4,5,10]}"));

        then(divisorService).should().findDivisor(20, empty());
    }

    @Test
    @DisplayName("should delegate to DivisorService for finding list of divisors with missing filtering value")
    void testGetDivisorsForGivenNumberWithFilterValue() throws Exception {
        given(divisorService.findDivisor(any(), any())).willReturn(Flux.just(1, 2, 4, 5, 10));

        webClient.get()
                .uri("/calculator/findDivisors/20?evenOrOdd=")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(("{\"results\":[1,2,4,5,10]}"));

        then(divisorService).should().findDivisor(20, empty());
    }

    @Test
    @DisplayName("should delegate to DivisorService for finding list of divisors with resolved filtering option")
    void testGetDivisorsForGivenNumberWithFilter() throws Exception {
        given(divisorService.findDivisor(any(), any())).willReturn(Flux.just(2, 4, 10));

        webClient.get()
                .uri("/calculator/findDivisors/20?evenOrOdd=eVeN")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(("{\"results\":[2,4,10]}"));

        then(divisorService).should().findDivisor(20, of(FilteringOption.EVEN));
    }
}