package com.trade.one.dtos;

/**
 * @author prabhakar, @Date 25-02-2025
 */
public class AccountActivationDTO {
    private Long accountId;
    private boolean activate;

    public AccountActivationDTO(){
        super();
    }
    public AccountActivationDTO(Long accountId, boolean activate) {
        super();
        this.accountId = accountId;
        this.activate = activate;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public boolean isActivate() {
        return activate;
    }
    public void setActivate(boolean activate) {
        this.activate = activate;
    }

}
