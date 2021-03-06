package my.test.divisor.springboot.mvc.web.controller;

import my.test.divisor.springboot.mvc.domain.model.FilteringOption;
import my.test.divisor.springboot.mvc.domain.service.DivisorService;
import my.test.divisor.springboot.mvc.web.api.FindDivisorsApi;
import my.test.divisor.springboot.mvc.web.model.NumberArrayResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Spring controller which implements {@link FindDivisorsApi}.
 * The {@link FindDivisorsApi} interface is autogenerated and defines the contract which should be implemented in this class.
 */
@RestController
@RequestMapping("/calculator")
public class FindDivisorsController implements FindDivisorsApi {

    /**
     * service which implements the &quot;business&quot; logic
     */
    private final DivisorService divisorService;

    /**
     * Creates a new instance of the controller with the injected service
     *
     * @param divisorService service to inject
     */
    public FindDivisorsController(DivisorService divisorService) {
        this.divisorService = divisorService;
    }

    /**
     * Returns a list of numbers between 1 and 10 (inclusive) by which the value provided in the url can be divided (divisors).
     * Uses {@link #divisorService} to find the divisor list.
     *
     * @param numberValue The number provided by the user (required), used as a dividend for finding divisors
     * @param evenOrOdd   Whether the results should contain even or odd numbers (optional)
     * @return response entity containing list of divisors
     */
    @Override
    public ResponseEntity<NumberArrayResult> getDivisorsForGivenNumber(Integer numberValue, String evenOrOdd) {
        List<Integer> divisors = divisorService.findDivisor(numberValue, FilteringOption.byValue(evenOrOdd));
        return ResponseEntity.ok(new NumberArrayResult().results(divisors));
    }
}
