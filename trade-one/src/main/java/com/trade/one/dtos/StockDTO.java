package com.trade.one.dtos;

import com.trade.one.models.Stock;
/**
 * @author prabhakar, @Date 25-02-2025
 */
public class StockDTO {
    private String name;
    private String tickerSymbol;
    private Double currentPrice;
    
    public StockDTO() {}
    
    public StockDTO(Stock stock) {
        this.name = stock.getName();
        this.tickerSymbol = stock.getTickerSymbol();
        this.currentPrice = stock.getCurrentPrice();
    }

    public StockDTO(String name, String tickerSymbol, Double currentPrice) {
        this.name = name;
        this.tickerSymbol = tickerSymbol;
        this.currentPrice = currentPrice;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTickerSymbol() {
        return tickerSymbol;
    }
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }
    public Double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

}