import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main{

    // Account class
    public static class Account {
        private String accountName;
        private double balance;

        // Constructor
        public Account(String accountName, double initialBalance) {
            this.accountName = accountName;
            this.balance = initialBalance;
        }

        // Getters and Setters
        public String getAccountName() {
            return accountName;
        }

        public double getBalance() {
            return balance;
        }

        // Deposit method
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: $" + amount);
            } else {
                System.out.println("Amount must be positive.");
            }
        }

        // Withdraw method
        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: $" + amount);
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }
    }

    // Transaction class
    public static class Transaction {
        private Date date;
        private String type; // Deposit or Withdrawal
        private double amount;

        // Constructor
        public Transaction(String type, double amount) {
            this.date = new Date();
            this.type = type;
            this.amount = amount;
        }

        // Getters
        public Date getDate() {
            return date;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Transaction [Date=" + date + ", Type=" + type + ", Amount=$" + amount + "]";
        }
    }

    // Main application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Accounting Application ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner, accounts);
                    break;
                case 2:
                    deposit(scanner, accounts, transactions);
                    break;
                case 3:
                    withdraw(scanner, accounts, transactions);
                    break;
                case 4:
                    viewBalance(scanner, accounts);
                    break;
                case 5:
                    viewTransactions(transactions);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting application...");
    }

    // Create Account
    private static void createAccount(Scanner scanner, List<Account> accounts) {
        System.out.print("Enter account name: ");
        String accountName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        Account account = new Account(accountName, initialBalance);
        accounts.add(account);
        System.out.println("Account created successfully!");
    }

    // Deposit Money
    private static void deposit(Scanner scanner, List<Account> accounts, List<Transaction> transactions) {
        System.out.print("Enter account name: ");
        String accountName = scanner.nextLine();

        Account account = findAccount(accountName, accounts);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            transactions.add(new Transaction("Deposit", amount));
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraw Money
    private static void withdraw(Scanner scanner, List<Account> accounts, List<Transaction> transactions) {
        System.out.print("Enter account name: ");
        String accountName = scanner.nextLine();

        Account account = findAccount(accountName, accounts);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            transactions.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Account not found.");
        }
    }

    // View Account Balance
    private static void viewBalance(Scanner scanner, List<Account> accounts) {
        System.out.print("Enter account name: ");
        String accountName = scanner.nextLine();

        Account account = findAccount(accountName, accounts);
        if (account != null) {
            System.out.println("Account balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // View all Transactions
    private static void viewTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    // Find Account by name
    private static Account findAccount(String accountName, List<Account> accounts) {
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName)) {
                return account;
            }
        }
        return null; // Account not found
    }
}