package com.trade.one.dtos;
/**
 * @author prabhakar, @Date 25-02-2025
 */

public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO() {
        super();
    }
    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
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

}