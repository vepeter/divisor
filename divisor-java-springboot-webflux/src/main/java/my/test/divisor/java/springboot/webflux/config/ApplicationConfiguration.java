package my.test.divisor.java.springboot.webflux.config;

import my.test.divisor.java.springboot.webflux.domain.service.DivisorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private static final int DIVISOR_RANGE_LOWER_END = 1;
    private static final int DIVISOR_RANGE_UPPER_END = 10;

    @Bean
    public DivisorService divisorService() {
        return new DivisorService(DIVISOR_RANGE_LOWER_END, DIVISOR_RANGE_UPPER_END);
    }
}
