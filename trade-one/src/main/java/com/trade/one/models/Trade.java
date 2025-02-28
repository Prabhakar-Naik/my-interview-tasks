package com.trade.one.models;

import jakarta.persistence.*;
/**
 * @author prabhakar, @Date 26-02-2025
 */
@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "buy_order_id")
    private Order buyOrder;

    @ManyToOne
    @JoinColumn(name = "sell_order_id")
    private Order sellOrder;

    private int quantity;
    private Double price;
    private String timestamp;

    public Trade() {
        super();
    }

    public Trade(Order buyOrder, Order sellOrder, int quantity, Double price, String timestamp) {
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(Order buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Order getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(Order sellOrder) {
        this.sellOrder = sellOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
