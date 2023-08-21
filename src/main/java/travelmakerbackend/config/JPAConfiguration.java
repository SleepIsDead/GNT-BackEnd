package travelmakerbackend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "travelmakerbackend")
@EnableJpaRepositories(basePackages = "travelmakerbackend")
public class JPAConfiguration {

}
