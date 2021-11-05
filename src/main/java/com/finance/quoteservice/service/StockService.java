package com.finance.quoteservice.service;

import com.finance.quoteservice.model.StockPriceData;
import com.finance.quoteservice.model.StockPrices;
import com.finance.quoteservice.model.WatchableStock;
import com.finance.quoteservice.model.WatchableStockList;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * User: raji
 * Date: 11.03.2021
 */

/*
 * This Service access the Yahoo Finance API and provides respective data to the controller
 */
@Service
@Log
public class StockService {
    @Autowired
    private WatchableStockList stockData;

    /*
     * This Method gets the Quote-Price value of the specified Stock Symbol
     */
    public BigDecimal getPrice(String symbol) {
        try {
            Stock stock = YahooFinance.get(symbol);
            return stock.getQuote(true).getPrice();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    /*
     * This Method gets the 50 day average price value of the specified Stock Symbol
     */
    public BigDecimal getPriceAvg50(String symbol) {
        try {
            Stock stock = YahooFinance.get(symbol);
            return stock.getQuote(true).getPriceAvg50();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    /*
     * This Method gets the 200 day average price value of the specified Stock Symbol
     */
    public BigDecimal getPriceAvg200(String symbol) {
        try {
            Stock stock = YahooFinance.get(symbol);
            return stock.getQuote(true).getPriceAvg200();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    
    /*
     * This Method gets all price details of the specified Stock Symbol
     */

    public StockPrices getPriceData(String symbol) {
        try {
            Stock stock = YahooFinance.get(symbol);
            return StockPrices
                    .builder()
                    .priceAvg50(stock.getQuote().getPriceAvg50())
                    .price(stock.getQuote().getPrice())
                    .priceAvg200(stock.getQuote().getPriceAvg200())
                    .currency(stock.getCurrency())
                    .build();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
    /*
     * This Method gets all price details of the all watched list Stock Symbols
     */
    public List<StockPriceData> getAllPriceData() {
        try {
            String[] symbols = stockData.getStockItems().stream().map(WatchableStock::getSymbol).toArray(String[]::new);
            Map<String, Stock> stockData = YahooFinance.get(symbols);
            List<StockPriceData> stockPriceList = new ArrayList<>();
            stockData.forEach((symbol, stock) -> {
                stockPriceList.add(StockPriceData
                        .builder()
                                .symbol(symbol)
                                .priceData(
                                        StockPrices
                                                .builder()
                                                .priceAvg50(stock.getQuote().getPriceAvg50())
                                                .price(stock.getQuote().getPrice())
                                                .priceAvg200(stock.getQuote().getPriceAvg200())
                                                .currency(stock.getCurrency())
                                                .build()
                                )
                        .build());
            });
            return stockPriceList;
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
}
