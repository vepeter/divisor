package my.test.divisor.springboot.mvc.domain.model;

import java.util.Arrays;
import java.util.Optional;

/**
 * Type to define what filtering should be applied on numbers.
 */
public enum FilteringOption {
    EVEN, ODD;

    /**
     * Iterates over possible enum types and compares the provided value with the name of the type (ignoring case).
     *
     * @param value name of the type to find
     * @return found type or {@linkplain Optional#empty()} if the &quot;value&quot; is <dd>null</dd> or the type cannot be found
     */
    public static Optional<FilteringOption> byValue(String value) {
        return Arrays.stream(values())
                .filter(option -> option.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
