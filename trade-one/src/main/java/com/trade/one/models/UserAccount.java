package com.trade.one.models;

import jakarta.persistence.*;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
    private boolean isKycCompleted;
    private boolean isAccountActivated;

    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private KycDetails kycDetails;

    public UserAccount(Long id) {
        this.id = id;
    }

    public UserAccount() {
        super();
    }


    public UserAccount(String fullName, String email, String password, String phoneNumber, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isEmailVerified = false;
        this.isPhoneVerified = false;
        this.isKycCompleted = false;
        this.isAccountActivated = false;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public boolean isKycCompleted() {
        return isKycCompleted;
    }

    public void setKycCompleted(boolean kycCompleted) {
        isKycCompleted = kycCompleted;
    }

    public boolean isAccountActivated() {
        return isAccountActivated;
    }

    public void setAccountActivated(boolean accountActivated) {
        isAccountActivated = accountActivated;
    }

    public KycDetails getKycDetails() {
        return kycDetails;
    }

    public void setKycDetails(KycDetails kycDetails) {
        this.kycDetails = kycDetails;
    }
}
