package com.dreamteam3.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.dreamteam3.data",
        "com.dreamteam3.rest"
})
@EnableJpaRepositories(basePackages = {"com.dreamteam3.data.repository"})
@EntityScan(basePackages = {"com.dreamteam3.data.model"})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
