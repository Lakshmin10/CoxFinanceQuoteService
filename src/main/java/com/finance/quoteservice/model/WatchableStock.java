package com.finance.quoteservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * User: raji
 * Date: 11.03.2021
 */

/*
 * This Model represents Stock Symbol for the Watch list
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WatchableStock {
    public String symbol;
}
