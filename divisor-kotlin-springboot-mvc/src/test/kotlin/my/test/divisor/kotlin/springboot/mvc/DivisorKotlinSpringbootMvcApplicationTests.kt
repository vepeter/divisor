package my.test.divisor.kotlin.springboot.mvc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert.assertEquals
import org.skyscreamer.jsonassert.JSONCompareMode.STRICT
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DivisorKotlinSpringbootMvcApplicationTests(
    @LocalServerPort private val applicationPort: Int
) {

    private val restTemplate = TestRestTemplate()

    @Test
    fun `should respond with proper divisor list with no filtering`() {
        val response =
            restTemplate.getForEntity<String>("http://localhost:$applicationPort/calculator/findDivisors/20", String::class.java)

        assertAll("response",
            {
                assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
            },
            {
                assertEquals(
                    """{
					  |    "results": [1, 2, 4, 5, 10]
					  |}
					""".trimMargin(),
                    response.body,
                    STRICT
                )
            }
        )
    }

    @Test
    fun `should respond with proper divisor list with filtering results`() {
        val response = restTemplate.getForEntity<String>(
            "http://localhost:$applicationPort/calculator/findDivisors/30?evenOrOdd=odd",
            String::class.java
        )

        assertAll("response",
            {
                assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
            },
            {
                assertEquals(
                    """{
					  |    "results": [1, 3, 5]
					  |}
					""".trimMargin(),
                    response.body,
                    STRICT
                )
            }
        )
    }

}
