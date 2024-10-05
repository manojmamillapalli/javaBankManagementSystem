package com.bank.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.bank.model.Customer;
import com.bank.model.Account;
import com.bank.model.Transaction;

public class FileManager {
    private static final String CUSTOMER_FILE = "C:\\Users\\Manoj Naidu\\Downloads\\BankFiles\\bankcustomerdata.txt";
    private static final String ACCOUNT_FILE = "C:\\Users\\Manoj Naidu\\Downloads\\BankFiles\\Accountdata.txt";
    private static final String TRANSACTION_FILE = "C:\\Users\\Manoj Naidu\\Downloads\\BankFiles\\Transactiondata.txt";

    public void saveCustomers(List<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    public List<Customer> loadCustomers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE))) {
            return (List<Customer>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading customers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveAccounts(List<Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public List<Account> loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE))) {
            return (List<Account>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveTransactions(List<Transaction> transactions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTION_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public List<Transaction> loadTransactions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTION_FILE))) {
            return (List<Transaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
