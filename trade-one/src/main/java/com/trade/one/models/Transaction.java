package com.trade.one.models;

import jakarta.persistence.*;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    private String type; // DEPOSIT or WITHDRAWAL
    private Double amount;
    private String status;

    public Transaction() {
        super();
    }
    public Transaction(Long id, UserAccount userAccount, String type, Double amount, String status) {
        super();
        this.id = id;
        this.userAccount = userAccount;
        this.type = type;
        this.amount = amount;
        this.status = status;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}