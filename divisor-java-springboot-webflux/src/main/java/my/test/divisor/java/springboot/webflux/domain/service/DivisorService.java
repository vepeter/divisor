package my.test.divisor.java.springboot.webflux.domain.service;

import my.test.divisor.java.springboot.webflux.domain.model.FilteringOption;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Service to find divisors for provided
 */
public class DivisorService {

    /**
     * predicate to check that the number is even
     */
    private static Predicate<Integer> isEven = number -> number % 2 == 0;

    /**
     * predicate to check that the number is odd
     */
    private static Predicate<Integer> isOdd = isEven.negate();

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
     * Finds stream of divisors for the provided dividend, filters the resulting stream of divisors if the numberValue
     * is not <dd>null</dd>
     *
     * @param numberValue     dividend
     * @param filteringOption defines what filtering should be applied on the resulting stream of divisors
     * @return stream of found divisors, empty stream if &quot;numberValue&quot; is <dd>null</dd>
     */
    public Flux<Integer> findDivisor(Integer numberValue, Optional<FilteringOption> filteringOption) {
        if (numberValue == null) {
            return Flux.empty();
        }
        int count = divisorRangeUpperEnd - divisorRangeLowerEnd + 1;
        Flux<Integer> divisorStream = Flux.range(divisorRangeLowerEnd, count)
                .filter(candidate -> numberValue % candidate == 0);
        return filterDivisors(divisorStream, filteringOption);
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
    private Flux<Integer> filterDivisors(Flux<Integer> divisorStream, Optional<FilteringOption> filteringOption) {
        if (filteringOption.isEmpty()) {
            return divisorStream;
        }
        Predicate<Integer> predicateToApply = filteringOption.get() == FilteringOption.ODD ? isOdd : isEven;
        return divisorStream.filter(predicateToApply);
    }
}
