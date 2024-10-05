package com.bank.main;

import java.util.Scanner;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.service.Bank;
import com.bank.service.FileManager;

public class BankManagementSystem {
    private static Bank bank = new Bank();
    private static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        // Load data from files
        bank.setCustomers(fileManager.loadCustomers());
        bank.setAccounts(fileManager.loadAccounts());
        bank.setTransactions(fileManager.loadTransactions());

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    removeCustomer(scanner);
                    break;
                case 3:
                    addAccount(scanner);
                    break;
                case 4:
                    removeAccount(scanner);
                    break;
                case 5:
                    deposit(scanner);
                    break;
                case 6:
                    withdraw(scanner);
                    break;
                case 7:
                    displayAllCustomers();
                    break;
                case 8:
                    displayCustomerAccounts(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        // Save data to files
        fileManager.saveCustomers(bank.getCustomers());
        fileManager.saveAccounts(bank.getAccounts());
        fileManager.saveTransactions(bank.getTransactions());

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("******************************************************");
        System.out.println("\nBank Management System");
        System.out.println("1. Add Customer");
        System.out.println("2. Remove Customer");
        System.out.println("3. Add Account");
        System.out.println("4. Remove Account");
        System.out.println("5. Deposit");
        System.out.println("6. Withdraw");
        System.out.println("7. Display All Customers");
        System.out.println("8. Display Customer Accounts");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(customerId, name, email);
        bank.addCustomer(customer);
    }

    private static void removeCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID to remove: ");
        String customerId = scanner.nextLine();

        Customer customer = bank.findCustomerById(customerId);
        if (customer != null) {
            bank.removeCustomer(customerId);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void addAccount(Scanner scanner) {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter account type: ");
        String accountType = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(accountId, balance, accountType);
        bank.addAccount(account);
    }

    private static void removeAccount(Scanner scanner) {
        System.out.print("Enter Account ID to remove: ");
        String accountId = scanner.nextLine();

        Account account = bank.findAccountById(accountId);
        if (account != null) {
            bank.removeAccount(accountId);
            System.out.println("Account removed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        bank.deposit(customerId, accountId, amount);

        // Record the transaction
        Transaction transaction = new Transaction(
                "TXN" + System.currentTimeMillis(),
                amount,
                "Deposit"
        );
        bank.addTransaction(transaction);
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        bank.withdraw(customerId, accountId, amount);

        // Record the transaction,...  
        Transaction transaction = new Transaction(
                "TXN" + System.currentTimeMillis(),
                amount,
                "Withdraw"
        );
        bank.addTransaction(transaction);
    }

    private static void displayAllCustomers() {
        bank.displayAllCustomers();
    }

    private static void displayCustomerAccounts(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        bank.displayCustomerAccounts(customerId);
    }
}//Why it is not pushing