package com.spring.boot.beetextingtask.account.controller;

import com.spring.boot.beetextingtask.account.dto.AccountDto;
import com.spring.boot.beetextingtask.account.entity.Account;
import com.spring.boot.beetextingtask.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }

    // retrieve all accounts in the db
    @GetMapping(value = "/getAccounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    // create a new account
    @PostMapping(value = "/saveAccount")
    public Account saveAccount(@RequestBody AccountDto account) {
        return this.accountService.saveAccount(account);
    }

    // access the record on id
    @GetMapping(value = "/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return this.accountService.getAccountById(id);
    }

    // deposit amount to the record on id with respective parameters
    @PutMapping(value = "/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestBody AccountDto account) {
        return this.accountService.depositBalanceInAccount(id,account);
    }

    // withdraw amount from the record on id with respective parameters
    @PutMapping(value = "/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestBody AccountDto account) {
        return this.accountService.withdrawBalanceInAccount(id,account);
    }

    // we can make more liberal implementation on standardization
}
