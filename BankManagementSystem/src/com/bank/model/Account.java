package com.bank.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String accountId;
    private double balance;
    private String accountType;

    public Account(String accountId, double balance, String accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
