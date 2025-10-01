package com.nkm.bank.controller;

import com.nkm.bank.model.Account;
import com.nkm.bank.repository.AccountRepository;
import com.nkm.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@RequestMapping("/accounts")
//public class AccountController {
//
//    @Autowired
//    private AccountRepository repository;
//
//    @PostMapping
//    public Account createAccount(@RequestBody Account account) {
//        return repository.save(account);
//    }
//
//    @GetMapping
//    public List<Account> getAllAccounts() {
//        return repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Account getAccount(@PathVariable String id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteAccount(@PathVariable String id) {
//        repository.deleteById(id);
//        return "Deleted successfully";
//    }
//}


@RestController
@RequestMapping("/account")
public class AccountController{
    @Autowired
    AccountService service;
    //    create the Account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account createAccount = service.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber){
        Account account =service.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }
    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails() {
        List<Account> allAccountDetails = service.getAllAccountDetails();
        return allAccountDetails;

    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAccount(@PathVariable Long accountNumber, @PathVariable Double amount){
        Account account = service.withdrawAmount(accountNumber,amount);
        return account;
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account account = service.depositAmount(accountNumber, amount);
        return account;
    }
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber){
        service.closeAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed");
    }


}