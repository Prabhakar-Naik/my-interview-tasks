package com.trade.one.dtos;

import com.trade.one.models.KycDetails;
import com.trade.one.models.UserAccount;

/**
 * @author prabhakar, @Date 25-02-2025
 */

public class KycDetailsDTO {
    private Long userId;
    private String panCardNumber;
    private String aadhaarNumber;
    private String bankAccountNumber;
    private String ifscCode;

    public KycDetails toEntity(UserAccount userAccount) {
        KycDetails kycDetails = new KycDetails();
        kycDetails.setUserAccount(userAccount);
        kycDetails.setPanCardNumber(this.panCardNumber);
        kycDetails.setAadhaarNumber(this.aadhaarNumber);
        kycDetails.setBankAccountNumber(this.bankAccountNumber);
        kycDetails.setIfscCode(this.ifscCode);
        kycDetails.setVerified(false);
        return kycDetails;
    }

    public KycDetailsDTO() {
        super();
    }

    public KycDetailsDTO(Long userId,String panCardNumber, String aadhaarNumber, String bankAccountNumber, String ifscCode) {
        super();
        this.userId = userId;
        this.panCardNumber = panCardNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.ifscCode = ifscCode;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
}
