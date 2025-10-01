package com.nkm.bank.model;
import javax.persistence.*;

//@Document(collection = "accounts")
//public class Account {
//
//    @Id
//    private String id;
//
//    private String name;
//    private double balance;
//
//    public Account() {}
//
//    public Account(String name, double balance) {
//        this.name = name;
//        this.balance = balance;
//    }
//
//    // Getters & Setters
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
//}
//

public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_number;
    private String account_holder_name;
    private Double account_balance;

    public Account(){
//        default constructor---
    }

    public Account(String account_holder_name, Double account_balance) {
        super();
        this.account_holder_name = account_holder_name;
        this.account_balance = account_balance;
    }

//    public Account(Double account_balance) {
//        this.account_balance = account_balance;
//    }

    //    getter and setter

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public Double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(Double account_balance) {
        this.account_balance = account_balance;
    }

//    to string


    @Override
    public String toString() {
        return "Account{" +
                "account_balance=" + account_balance +
                ", account_number=" + account_number +
                ", account_holder_name='" + account_holder_name + '\'' +
                '}';
    }
}