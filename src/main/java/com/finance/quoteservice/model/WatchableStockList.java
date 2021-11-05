package com.finance.quoteservice.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: raji
 * Date: 11.03.2021
 */
/* 
 * This Static class maintains the watchable stock symbols
 */
@Component
@ConfigurationProperties("static")
public class WatchableStockList {
    private final List<WatchableStock> stockItems;

    public WatchableStockList(List<WatchableStock> stockItems) {
        this.stockItems = stockItems;
    }

    public List<WatchableStock> getStockItems(){
        return this.stockItems;
    }
}
