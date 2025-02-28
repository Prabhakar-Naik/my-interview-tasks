package com.trade.one.models;

import jakarta.persistence.*;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
public class KycDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    private String panCardNumber;
    private String aadhaarNumber;
    private String bankAccountNumber;
    private String ifscCode;
    private boolean isVerified;

    public KycDetails() {
        super();
    }

    public KycDetails(Long id, UserAccount userAccount, String panCardNumber, String aadhaarNumber, String bankAccountNumber, String ifscCode, boolean isVerified) {
        this.id = id;
        this.userAccount = userAccount;
        this.panCardNumber = panCardNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.ifscCode = ifscCode;
        this.isVerified = isVerified;
    }

    // Getters and Setters


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

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
