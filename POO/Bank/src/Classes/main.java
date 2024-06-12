import java.util.Scanner;

import Exceptions.AccountCreationException;
import AExceptions.AccountException;
import BusinessAccount;
import PersonalAccount;
import SavingsAccount;

public class Main {

    public static void main(String[] args) throws AccountException {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their details
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter your initial balance:");
        double initialBalance = scanner.nextDouble();

        // Demonstrate the creation of different types of accounts
        try {
            // Creating a Personal Account
            System.out.println("\nCreating Personal Account...");
            PersonalAccount personalAccount = new PersonalAccount(name, email, phoneNumber, initialBalance);
            System.out.println("Personal Account created successfully!");
            System.out.println(personalAccount);

            // Creating a Savings Account
            System.out.println("\nCreating Savings Account...");
            SavingsAccount savingsAccount = new SavingsAccount(name, email, phoneNumber, initialBalance);
            System.out.println("Savings Account created successfully!");
            System.out.println(savingsAccount);

            // Creating a Business Account
            System.out.println("\nCreating Business Account...");
            BusinessAccount businessAccount = new BusinessAccount(name, email, phoneNumber, initialBalance);
            System.out.println("Business Account created successfully!");
            System.out.println(businessAccount);
        } catch (AccountCreationException e) {
            System.err.println("Error creating account: " + e.getMessage());
        }
    }
}
