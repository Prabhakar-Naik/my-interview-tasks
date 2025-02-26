package com.spring.boot.beetextingtask.account.service;

import com.spring.boot.beetextingtask.account.dto.AccountDto;
import com.spring.boot.beetextingtask.account.entity.Account;
import com.spring.boot.beetextingtask.account.entity.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // setting the data and save in the db
    // space and time complexity is O(1)
    public Account saveAccount(AccountDto account) {
        Optional<Account> account1 = this.accountRepository.findByAccountNumber(account.getAccountNumber());
        if (account1.isPresent()) {
            //log.info("Account already exists");
            return null;
        }
        Account newAccount = new Account();
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setBalance(BigDecimal.valueOf(account.getBalance()));
        //log.info("Saving account {}", newAccount);
        return accountRepository.save(newAccount);
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = this.accountRepository.findAll();
        Set<Account> accountStream = accounts.stream().filter(x -> x.getAccountNumber().contains("123")).collect(Collectors.toSet());
        return new ArrayList<>(accountStream);
    }

    // get the record on id
    // space and time complexity is O(log n)
    public Account getAccountById(Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        return account.orElse(null);
    }

    // retrieving data as simple as that
    // space and time complexity is O(log n)
    public List<Account> getAccounts() {
        return this.accountRepository.findAll();
    }

    // in realtime the transactional basis functionality perform
    //every event should be recorded

    // deposit an amount to the account with respective parameters
    // space and time complexity is O(log n)
    public String depositBalanceInAccount(Long id, AccountDto data) {
        // first i have to check with db the record present or not in the present of id
        if (this.accountRepository.existsById(id)) {
            // if present accessing the record
            Account account = this.accountRepository.findById(id).get();
            // compareTo method from functional implementation
            // the input data balance is must be positive
            if (account.getBalance().compareTo(BigDecimal.valueOf(data.getBalance())) > 0) {
                // if validate the condition then store it
                account.setBalance(account.getBalance().add(BigDecimal.valueOf(data.getBalance())));
                this.accountRepository.save(account);
                return "Deposit successful";
            }
            return "you entered negative value";
        }
        return "Deposit failed";
    }

    // withdraw an amount from the account with respective parameters
    // space and time complexity is O(log n)
    public String withdrawBalanceInAccount(Long id, AccountDto data) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isPresent()) {
            BigDecimal subtract = account.get().getBalance().subtract(BigDecimal.valueOf(data.getBalance()));
            if (subtract.compareTo(BigDecimal.valueOf(data.getBalance())) > 0) {
                account.get().setBalance(account.get().getBalance().subtract(BigDecimal.valueOf(data.getBalance())));
                this.accountRepository.save(account.get());
                return "Withdraw successful";
            }
            return "you entered Insufficient value";
        }
        return "You entered Wrong Id";
    }

}
