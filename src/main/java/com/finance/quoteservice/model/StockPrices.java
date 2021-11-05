package com.finance.quoteservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * User: raji
 * Date: 11.03.2021
 */

/*
 * This Model represents the price details of a Stock Symbol
 */
@Data
@Builder
public class StockPrices {
    private String currency;
    private BigDecimal price;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
}
