package my.test.divisor.springboot.mvc.domain.service;

import my.test.divisor.springboot.mvc.domain.model.FilteringOption;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Service to find divisors for provided
 */
public class DivisorService {

    /**
     * predicate to check that the number is even
     */
    private static IntPredicate isEven = number -> number % 2 == 0;

    /**
     * predicate to check that the number is odd
     */
    private static IntPredicate isOdd = isEven.negate();

    /**
     * lower end for the divisor range
     */
    private final int divisorRangeLowerEnd;

    /**
     * upper end for the divisor range
     */
    private final int divisorRangeUpperEnd;

    /**
     * Creates a new instance of DivisorService with provided parameters for divisor range.
     *
     * @param divisorRangeLowerEnd lower end for the divisor range
     * @param divisorRangeUpperEnd upper end for the divisor range
     */
    public DivisorService(int divisorRangeLowerEnd, int divisorRangeUpperEnd) {
        this.divisorRangeLowerEnd = divisorRangeLowerEnd;
        this.divisorRangeUpperEnd = divisorRangeUpperEnd;
    }

    /**
     * Finds list of divisors for the provided divident, filters the resulting list of divisors if the numberValue
     * is <dd>null</dd>
     *
     * @param numberValue     divident
     * @param filteringOption defines what filtering should be applied on the resulting list of divisors
     * @return list of found divisors, empty list if &quot;numberValue&quot; is <dd>null</dd>
     */
    public List<Integer> findDivisor(Integer numberValue, Optional<FilteringOption> filteringOption) {
        if (numberValue == null) {
            return Collections.emptyList();
        }
        IntStream divisorStream = IntStream.rangeClosed(divisorRangeLowerEnd, divisorRangeUpperEnd)
                .filter(candidate -> numberValue % candidate == 0);
        return filterDivisors(divisorStream, filteringOption)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Applies filtering of the provided stream. A proper predicate {@link #isEven} or {@link #isOdd} is applied based
     * on the &quot;filteringOption&quot; parameter value. The source stream is returned if &quot;filteringOption&quot;
     * is empty
     *
     * @param divisorStream   source stream to apply filtering
     * @param filteringOption difines what filtering should be applied
     * @return source stream if the &quot;filteringOption&quot; parameter is empty or the filtered stream otherwise
     */
    private IntStream filterDivisors(IntStream divisorStream, Optional<FilteringOption> filteringOption) {
        if (filteringOption.isEmpty()) {
            return divisorStream;
        }
        IntPredicate predicateToApply = filteringOption.get() == FilteringOption.ODD ? isOdd : isEven;
        return divisorStream.filter(predicateToApply);
    }
}
