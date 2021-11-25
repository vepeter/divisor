package my.test.divisor.springboot.mvc.web.controller;

import my.test.divisor.springboot.mvc.domain.model.FilteringOption;
import my.test.divisor.springboot.mvc.domain.service.DivisorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = FindDivisorsController.class,
        properties = "spring.main.banner-mode=off")
class FindDivisorsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DivisorService divisorService;

    @Test
    @DisplayName("should delegate to DivisorService for finding list of divisors with no filtering option")
    void testGetDivisorsForGivenNumber() throws Exception {
        given(divisorService.findDivisor(any(), any())).willReturn(List.of(1, 2, 4, 5, 10));

        mockMvc.perform(get("/findDivisors/20"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"results\":[1,2,4,5,10]}"));

        then(divisorService).should().findDivisor(20, empty());
    }

    @Test
    @DisplayName("should delegate to DivisorService for finding list of divisors with missing filtering value")
    void testGetDivisorsForGivenNumberWithFilterValue() throws Exception {
        given(divisorService.findDivisor(any(), any())).willReturn(List.of(1, 2, 4, 5, 10));

        mockMvc.perform(get("/findDivisors/20").param("evenOrOdd", ""))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"results\":[1,2,4,5,10]}"));

        then(divisorService).should().findDivisor(20, empty());
    }

    @Test
    @DisplayName("should delegate to DivisorService for finding list of divisors with resolved filtering option")
    void testGetDivisorsForGivenNumberWithFilter() throws Exception {
        given(divisorService.findDivisor(any(), any())).willReturn(List.of(2, 4, 10));

        mockMvc.perform(get("/findDivisors/20").param("evenOrOdd", "eVeN"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"results\":[2,4,10]}"));

        then(divisorService).should().findDivisor(20, of(FilteringOption.EVEN));
    }
}