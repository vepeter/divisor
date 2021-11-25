package my.test.divisor.springboot.mvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DivisorSpringbootMvcApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer applicationPort;

    @Test
    @DisplayName("should respond with proper divisor list with no filtering")
    void testFindDivisors() {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("http://localhost:%s/findDivisors/20", applicationPort), String.class);

        assertAll("response",
                () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> JSONAssert.assertEquals("{\"results\": [1, 2, 4, 5, 10]}", response.getBody(), JSONCompareMode.STRICT)
        );
    }

    @Test
    @DisplayName("should respond with proper divisor list with filtering results")
    void testFindDivisorsWithFiltering() {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("http://localhost:%s/findDivisors/30?evenOrOdd=odd", applicationPort), String.class);

        assertAll("response",
                () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> JSONAssert.assertEquals("{\"results\": [1, 3, 5]}", response.getBody(), JSONCompareMode.STRICT)
        );
    }

}
