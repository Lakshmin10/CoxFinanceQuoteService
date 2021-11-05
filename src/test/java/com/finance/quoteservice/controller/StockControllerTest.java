package com.finance.quoteservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.finance.quoteservice.config.FinanceQuoteServiceTestConfig;
import com.finance.quoteservice.model.StockPriceData;
import com.finance.quoteservice.model.StockPrices;
import com.finance.quoteservice.model.WatchableStock;
import com.finance.quoteservice.model.WatchableStockList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: raji
 * Date: 11.03.2021
 */

@ActiveProfiles("test")
@SpringBootTest(classes = {FinanceQuoteServiceTestConfig.class})
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureWebMvc
class StockControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WatchableStockList stockData;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addStockWatch() throws Exception {
        this.addStockBySymbol("INTC");
    }

    private void addStockBySymbol(String symbol) throws Exception {
        WatchableStock newCampaign = new WatchableStock(symbol);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(newCampaign);
        this.mockMvc
            .perform(post("/api/stocks/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void deleteStockWatch() throws Exception {
    	this.addStockBySymbol("INTC");
        WatchableStock newCampaign = new WatchableStock("INTC");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(newCampaign);
        this.mockMvc
                .perform(delete("/api/stocks/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void getPriceDataByStock() throws Exception {
        this.addStockBySymbol("INTC");
        ResultActions resultActions = this.mockMvc
                .perform(get(String.format("/api/stocks/symbol/%s", "INTC")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").exists());

        MvcResult getResult = resultActions.andReturn();
        String response = getResult.getResponse().getContentAsString();
        StockPrices stockPrices = objectMapper.readValue(response, StockPrices.class);
        assertNotNull(stockPrices);
    }

    @Test
    void getAllPriceData() throws Exception {
        this.addStockBySymbol("BABA");
        ResultActions resultActions = this.mockMvc
                .perform(get("/api/stocks/all-prices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                ;

        MvcResult getResult = resultActions.andReturn();
        String response = getResult.getResponse().getContentAsString();
        List<StockPriceData> data = objectMapper.readValue(response, new TypeReference<List<StockPriceData>>() {});
        assertFalse(data.isEmpty());
    }
}
