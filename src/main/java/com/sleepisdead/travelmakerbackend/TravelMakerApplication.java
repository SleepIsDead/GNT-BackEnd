package com.sleepisdead.travelmakerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing
@SpringBootApplication
public class TravelMakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelMakerApplication.class, args);
    }

    public RestTemplate restTemplate() {return new RestTemplate();}
}
