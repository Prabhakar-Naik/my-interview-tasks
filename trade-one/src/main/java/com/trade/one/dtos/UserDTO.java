package com.trade.one.dtos;
/**
 * @author prabhakar, @Date 25-02-2025
 */
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;

    public UserDTO() {
        super();
    }
    public UserDTO(Long id, String fullName, String email, String phoneNumber) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}