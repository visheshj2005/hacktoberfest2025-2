package com.nkm.bank.service;

import com.nkm.bank.model.Account;
import com.nkm.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository repo;

    @Override
    public Account createAccount(Account account) {
        // Ensure a null balance is initialized to 0.0 to avoid NPE later
        if (account.getAccount_balance() == null) {
            account.setAccount_balance(0.0);
        }
        Account account_saved = repo.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account account_found = account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listofAccounts = repo.findAll();
        return listofAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        if (amount == null || amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double current = accountPresent.getAccount_balance();
        if (current == null) current = 0.0;
        Double totalBalance = current + amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);
        return accountPresent;

//        return null;
    }
    @Override
    public Account withdrawAmount(Long accountNumber, Double amount){
        if (amount == null || amount <= 0) throw new IllegalArgumentException("Withdraw amount must be positive");
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double current = accountPresent.getAccount_balance();
        if (current == null) current = 0.0;
        Double accountBalance = current - amount;
        if (accountBalance < 0) throw new RuntimeException("Insufficient balance");
        accountPresent.setAccount_balance(accountBalance);
        repo.save(accountPresent);
        return accountPresent;
//        return null;
    }

    @Override
    public void closeAccount(Long accountNumber) {
//        auto generated

        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);

    }
}
