package com.finance.quoteservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EntityScan(basePackages = {"com.finance.quoteservice"})
@ComponentScan("com.finance.quoteservice")
@Configuration
public class FinanceQuoteServiceTestConfig {

}
