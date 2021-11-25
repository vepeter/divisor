package my.test.divisor.kotlin.springboot.mvc.web.controller

import my.test.divisor.kotlin.springboot.mvc.domain.model.FilteringOption
import my.test.divisor.kotlin.springboot.mvc.domain.service.DivisorService
import my.test.divisor.kotlin.springboot.mvc.web.api.FindDivisorsApi
import my.test.divisor.kotlin.springboot.mvc.web.model.NumberArrayResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

/**
 * Spring controller which implements [FindDivisorsApi]
 */
@RestController
class FindDivisorsController(
    private val divisorService: DivisorService
) : FindDivisorsApi {

    /**
     * Returns a list of numbers between 1 and 10 (inclusive) by which the value provided in the url can be divided (divisors).
     * Uses [divisorService] to find the divisor list.
     *
     * @param numberValue The number provided by the user (required), used as a dividend for finding divisors
     * @param evenOrOdd   Whether the results should contain even or odd numbers (optional)
     * @return response entity containing list of divisors
     */
    override fun getDivisorsForGivenNumber(numberValue: Int, evenOrOdd: String?): ResponseEntity<NumberArrayResult> {
        val divisors = divisorService.findDivisor(numberValue, FilteringOption.byValue(evenOrOdd))
        return ResponseEntity.ok(NumberArrayResult(divisors))
    }
}