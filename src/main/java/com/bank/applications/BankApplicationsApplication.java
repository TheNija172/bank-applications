package com.bank.applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BankApplicationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplicationsApplication.class, args);
    }

}
