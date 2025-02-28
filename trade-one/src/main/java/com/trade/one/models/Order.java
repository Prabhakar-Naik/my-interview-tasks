package com.trade.one.models;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    
    private int quantity;
    private Double price;
    private String orderType; // BUY or SELL
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    public Order() {
        super();
    }
    public Order(UserAccount userAccount, Stock stock, int quantity, Double price, String orderType, String status, Date timestamp) {
        super();
        this.userAccount = userAccount;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}