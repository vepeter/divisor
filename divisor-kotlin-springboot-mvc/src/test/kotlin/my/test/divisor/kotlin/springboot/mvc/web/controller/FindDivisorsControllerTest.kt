package my.test.divisor.kotlin.springboot.mvc.web.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import my.test.divisor.kotlin.springboot.mvc.domain.model.FilteringOption
import my.test.divisor.kotlin.springboot.mvc.domain.service.DivisorService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(
    controllers = [FindDivisorsController::class],
    properties = ["spring.main.banner-mode=off"]
)
internal class FindDivisorsControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    private lateinit var divisorService: DivisorService

    @Test
    fun `should delegate to DivisorService for finding list of divisors with no filtering option`() {
        every {
            divisorService.findDivisor(any(), any())
        } returns listOf(1, 2, 4, 5, 10)

        mockMvc.get("/calculator/findDivisors/20") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                json(
                    """{
                      |    "results": [1, 2, 4, 5, 10]
                      |}""".trimMargin()
                )
            }
        }

        verify { divisorService.findDivisor(20, null) }
    }


    @Test
    fun `should delegate to DivisorService for finding list of divisors with missing filtering value`() {
        every {
            divisorService.findDivisor(any(), any())
        } returns listOf(1, 2, 4, 5, 10)

        mockMvc.get("/calculator/findDivisors/20?evenOrOdd=") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                json(
                    """{
                      |    "results": [1, 2, 4, 5, 10]
                      |}""".trimMargin()
                )
            }
        }

        verify { divisorService.findDivisor(20, null) }
    }

    @Test
    fun `should delegate to DivisorService for finding list of divisors with resolved filtering option`() {
        every {
            divisorService.findDivisor(any(), any())
        } returns listOf(2, 4, 10)

        mockMvc.get("/calculator/findDivisors/20?evenOrOdd=even") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                json(
                    """{
                      |    "results": [2, 4, 10]
                      |}""".trimMargin()
                )
            }
        }

        verify { divisorService.findDivisor(20, FilteringOption.EVEN) }
    }
}