package com.trade.one.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Service
public class StockPriceService {

    private static final String API_URL = "https://api.example.com/getStockPrice?symbol="; // Replace with actual API URL

    private final RestTemplate restTemplate;

    public StockPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Double getLiveStockPrice(String stockSymbol) {

        // Make API request to fetch stock price
        Map<String, Object> response = restTemplate.getForObject(API_URL + stockSymbol, Map.class);

        // Extract and return the price (modify according to API response)
        return response != null ? (Double) response.get("price") : null;
    }
    // another style

    // Replace with actual API URL
    private static final String STOCK_API_URL = "https://api.example.com/stock-price?symbol={symbol}";


    public Double getStockPrice(String symbol) {
        ResponseEntity<Map> response = restTemplate.getForEntity(STOCK_API_URL, Map.class, symbol);
        if (response.getBody() != null && response.getBody().containsKey("price")) {
            return (Double) response.getBody().get("price");
        }
        return null;
    }
}
