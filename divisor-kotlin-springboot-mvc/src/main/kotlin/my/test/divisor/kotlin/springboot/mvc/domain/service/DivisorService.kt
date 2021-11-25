package my.test.divisor.kotlin.springboot.mvc.domain.service

import my.test.divisor.kotlin.springboot.mvc.domain.model.FilteringOption

/**
 * Service to find divisors for provided
 */
class DivisorService(
    private val divisorRangeLowerEnd: Int,
    private val divisorRangeUpperEnd: Int
) {

    /**
     * Finds list of divisors for the provided dividend number, filtered if the numberValue is not [null].
     *
     * @param numberValue the dividend number to find the divisors for
     * @param filteringOption defines what filtering should be applied on the resulting list of divisors
     * @return list of found and optionally filtered divisors or empty list if [numberValue] is [null]
     */
    fun findDivisor(numberValue: Int, filteringOption: FilteringOption?): List<Int> =
        (divisorRangeLowerEnd..divisorRangeUpperEnd)
            .filter { numberValue % it == 0 }
            .let { divisors ->
                when (filteringOption) {
                    null -> divisors
                    FilteringOption.EVEN -> divisors.filter { it % 2 == 0 }
                    FilteringOption.ODD -> divisors.filter { it % 2 != 0 }
                }
            }
}