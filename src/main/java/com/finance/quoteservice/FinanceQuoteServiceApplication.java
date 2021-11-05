package com.finance.quoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* 
 * 
 */
@SpringBootApplication(scanBasePackages = "com.finance.quoteservice")
@EntityScan(basePackages = {"com.finance.quoteservice"})
@Configuration
public class FinanceQuoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceQuoteServiceApplication.class, args);
    }

}
