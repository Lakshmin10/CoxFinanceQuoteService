package com.finance.quoteservice.controller;

import com.finance.quoteservice.model.StockPriceData;
import com.finance.quoteservice.model.StockPrices;
import com.finance.quoteservice.model.WatchableStock;
import com.finance.quoteservice.model.WatchableStockList;
import com.finance.quoteservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: raji
 * Date: 11.03.2021
 */
@RestController
//@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private WatchableStockList stockData;

    @Autowired
    private StockService stockService;

    /*
     * This API POST call adds the specified stock symbol to Watched List. It returns response code as 201
     */
    @PostMapping(value = {"/api/stocks/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStockWatch(@Validated @RequestBody WatchableStock newItem
    ) {
        stockData.getStockItems().add(newItem);
    }

    /*
     * This API call DELETE the existing stock from Watched List. It returns response code as 204
     */
    @DeleteMapping(value = {"/api/stocks/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStockWatch(
            @Validated @RequestBody WatchableStock delItem
    ) {
        stockData.getStockItems().removeIf(watchableStock -> watchableStock.getSymbol().equalsIgnoreCase(delItem.getSymbol()));
    }



    /**
     * This API Service will provide All Price details of the given stock symbol.
     * 
     * @param symbol String. The name of the symbol
     * @return Object with all price values
     */
    @GetMapping(value = {"/api/stocks/symbol/{symbol}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public StockPrices getAllPriceDataByStock(@PathVariable("symbol") String symbol) {
        return stockService.getPriceData(symbol);
    }


    /**
     * This API Service will provide the current Price of the given stock symbol.
     * 
     * @param symbol String. The name of the symbol
     * @return BigDecimal value of the Current Price
     */
    @GetMapping(value = {"/api/stocks/symbol/{symbol}/price"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal getPriceDataByStock(@PathVariable("symbol") String symbol) {
        return stockService.getPrice(symbol);
    }


    /**
     * This API Service will provide Price Average of 50 Days of the given stock symbol.
     * 
     * @param symbol String. The name of the symbol
     * @return BigDecimal value of the Price Average 50
     */
    @GetMapping(value = {"/api/stocks/symbol/{symbol}/priceAvg50"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal getPriceAvg50DataByStock(@PathVariable("symbol") String symbol) {
        return stockService.getPriceAvg50(symbol);
    }

    /**
     * This API Service will provide Price Average of 200 Days of the given stock symbol.
     * 
     * @param symbol String. The name of the symbol
     * @return BigDecimal value of the Price Average 200
     */
    @GetMapping(value = {"/api/stocks/symbol/{symbol}/priceAvg200"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal getPriceAvg200DataByStock(@PathVariable("symbol") String symbol) {
        return stockService.getPriceAvg200(symbol);
    }

    /**
     * This API Service will provide all price details of the watched stock symbol.
     * 
     * @return List of Price details object with the Symbol name. 
     */
    @GetMapping(value = {"/api/stocks/all-prices"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockPriceData> getAllWatchStickPriceData() {
        return stockService.getAllPriceData();
    }

}
