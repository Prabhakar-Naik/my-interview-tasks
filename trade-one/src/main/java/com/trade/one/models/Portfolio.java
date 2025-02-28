package com.trade.one.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_account_id", unique = true, nullable = false)
    private UserAccount userAccount;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PortfolioStock> stocks = new ArrayList<>();

    public Portfolio() {
        super();
    }

    public Portfolio(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Portfolio(Long id, UserAccount userAccount, List<PortfolioStock> stocks) {
        this.id = id;
        this.userAccount = userAccount;
        this.stocks = stocks;
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

    public List<PortfolioStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<PortfolioStock> stocks) {
        this.stocks = stocks;
    }
}