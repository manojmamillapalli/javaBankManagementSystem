package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public class Bank {
    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(String customerId) {
        customers.removeIf(c -> c.getCustomerId().equals(customerId));
    }

    public Customer findCustomerById(String customerId) {
        return customers.stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst().orElse(null);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(String accountId) {
        accounts.removeIf(a -> a.getAccountId().equals(accountId));
    }

    public Account findAccountById(String accountId) {
        return accounts.stream().filter(a -> a.getAccountId().equals(accountId)).findFirst().orElse(null);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deposit(String customerId, String accountId, double amount) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            Account account = findAccountById(accountId);
            if (account != null) {
                account.setBalance(account.getBalance() + amount);
                System.out.println("Deposit successful. New balance: " + account.getBalance());
            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void withdraw(String customerId, String accountId, double amount) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            Account account = findAccountById(accountId);
            if (account != null) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.println("Withdrawal successful. New balance: " + account.getBalance());
                } else {
                    System.out.println("Insufficient balance. Available balance: " + account.getBalance());
                }
            } else {
                System.out.println("Account not found.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    public void displayCustomerAccounts(String customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            List<Account> customerAccounts = new ArrayList<>();
            for (Account account : accounts) {
                if (account.getAccountId().startsWith(customerId)) {  
                    customerAccounts.add(account);
                }
            }
            if (customerAccounts.isEmpty()) {
                System.out.println("No accounts found for customer ID: " + customerId);
            } else {
                customerAccounts.forEach(System.out::println);
            }
        } else {
            System.out.println("Customer not found.");
        }
    }
}
