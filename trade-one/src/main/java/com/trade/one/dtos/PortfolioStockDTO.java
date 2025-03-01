package com.trade.one.dtos;
/**
 * @author prabhakar, @Date 26-02-2025
 */
public class PortfolioStockDTO {
    private String stockSymbol;
    private int quantity;
    private Double averagePrice;

    public PortfolioStockDTO() {
        super();
    }

    public PortfolioStockDTO(String stockSymbol, int quantity, Double averagePrice) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
