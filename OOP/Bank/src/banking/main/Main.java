package banking.main;

import banking.exceptions.AccountException;
import banking.implementations.PersonalAccount;
import banking.implementations.SavingsAccount;
import banking.implementations.BusinessAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        System.out.println("Choose account type:");
        System.out.println("1. Personal Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Business Account");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        try {
            switch (choice) {
                case 1:
                    System.out.println("\nCreating a Personal Account...");
                    PersonalAccount personalAccount = new PersonalAccount();
                    personalAccount.createAccount(name, email, phoneNumber, initialBalance);
                    System.out.println("Personal Account created successfully!");
                    System.out.println(personalAccount);
                    break;
                case 2:
                    System.out.println("\nCreating a Savings Account...");
                    SavingsAccount savingsAccount = new SavingsAccount();
                    savingsAccount.createAccount(name, email, phoneNumber, initialBalance);
                    System.out.println("Savings Account created successfully!");
                    System.out.println(savingsAccount);
                    break;
                case 3:
                    System.out.println("\nCreating a Business Account...");
                    BusinessAccount businessAccount = new BusinessAccount();
                    businessAccount.createAccount(name, email, phoneNumber, initialBalance);
                    System.out.println("Business Account created successfully!");
                    System.out.println(businessAccount);
                    break;
                default:
                    System.out.println("Invalid choice please run the program again and select a valid option");
            }
        } catch (AccountException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
