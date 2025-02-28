package com.trade.one.models;

import jakarta.persistence.*;
/**
 * @author prabhakar, @Date 25-02-2025
 */
@Entity
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    
    @OneToOne
    private UserAccount userAccount;

    public ResetToken() {
        super();
    }

    public ResetToken(String token, UserAccount userAccount) {
        this.token = token;
        this.userAccount = userAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserAccount getUser() {
        return userAccount;
    }

    public void setUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
