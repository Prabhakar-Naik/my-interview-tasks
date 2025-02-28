package com.trade.one.models;

import com.trade.one.dtos.StockDTO;
import jakarta.persistence.*;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tickerSymbol;
    private Double currentPrice;
    @ManyToOne
    @JoinColumn(name = "portfolio_id") // This field connects to Portfolio
    private Portfolio portfolio;

    public Stock() {
        super();
    }
    public Stock(String name, String tickerSymbol, Double currentPrice, Portfolio portfolio) {
        super();
        this.name = name;
        this.tickerSymbol = tickerSymbol;
        this.currentPrice = currentPrice;
        this.portfolio = portfolio;
    }

    public Stock(StockDTO dto) {
        this.name = dto.getName();
        this.tickerSymbol = dto.getTickerSymbol();
        this.currentPrice = dto.getCurrentPrice();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Portfolio getPortfolio() {
        return portfolio;
    }
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

}