package com.finance.quoteservice.model;

import lombok.Builder;
import lombok.Data;

/**
 * User: raji
 * Date: 11.03.2021
 */
/*
 * This Model represents Symbol & Price details of the Symbol.
 */

@Data
@Builder
public class StockPriceData {
    private String symbol;
    private StockPrices priceData;
}
