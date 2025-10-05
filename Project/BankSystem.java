import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;
    private String pin;

    public BankAccount(String accountHolder, double initialBalance, String pin) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ ₹" + amount + " deposited successfully.");
            checkBalance();
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
            checkBalance();
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create a sample account
        BankAccount account = new BankAccount("Anubhav Mishra", 5000.0, "1234");

        System.out.println("🏦 Welcome to Simple ATM System");
        System.out.print("🔐 Enter your PIN: ");
        String enteredPin = sc.nextLine();

        if (!account.authenticate(enteredPin)) {
            System.out.println("❌ Incorrect PIN. Access Denied.");
            return;
        }

        int choice;
        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1️⃣ Check Balance");
            System.out.println("2️⃣ Deposit Money");
            System.out.println("3️⃣ Withdraw Money");
            System.out.println("4️⃣ Exit");
            System.out.print("👉 Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("💵 Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("💸 Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("👋 Thank you for using our ATM. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
