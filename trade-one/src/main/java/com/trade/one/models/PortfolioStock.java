package com.trade.one.models;

import jakarta.persistence.*;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Entity
public class PortfolioStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    private int quantity;
    private Double averagePrice;

    public PortfolioStock() {
        super();
    }



    public PortfolioStock(Portfolio portfolio, Stock stock, int quantity, Double averagePrice) {
        this.portfolio = portfolio;
        this.stock = stock;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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
