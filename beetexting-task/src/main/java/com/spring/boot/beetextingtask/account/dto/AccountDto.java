package com.spring.boot.beetextingtask.account.dto;

import java.math.BigDecimal;

public class AccountDto {

    private String accountNumber;
    private Double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
