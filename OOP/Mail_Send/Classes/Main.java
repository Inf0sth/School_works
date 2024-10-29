package Examen_2.Classes;

import java.util.Scanner;
import Examen_2.Enums.DistributionPoints;

public class Main {

    public static void main(String[] args) throws UserException {
        Scanner scanner = new Scanner(System.in);

        // Collect sender's information
        System.out.println("Enter sender information:");
        System.out.print("Full Name: ");
        String senderFullName = scanner.nextLine();

        System.out.print("Address: ");
        String senderAddress = scanner.nextLine();

        System.out.print("Phone Number: ");
        String senderPhoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String senderEmail = scanner.nextLine();

        User sender = new User(senderFullName, senderAddress, senderPhoneNumber, senderEmail);

        System.out.println("Enter recipient information:");
        System.out.print("Full Name: ");
        String recipientFullName = scanner.nextLine();

        System.out.print("Address: ");
        String recipientAddress = scanner.nextLine();

        System.out.print("Phone Number: ");
        String recipientPhoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String recipientEmail = scanner.nextLine();

        User recipient = new User(recipientFullName, recipientAddress, recipientPhoneNumber, recipientEmail);


        // Determine if the item is a letter or a package
        System.out.println("Is the item a letter or a package? (Enter 'letter' or 'package'):");
        String itemType = scanner.nextLine().trim().toLowerCase();

        try {
            if (itemType.equals("letter")) {
                // Create a Letter object
                Letter letter = new Letter(sender, recipient);
                System.out.println("Letter created successfully!");
                displayPackageDetails(letter);
            } else if (itemType.equals("package")) {
                // Collect additional information for the package
                System.out.print("Enter the weight of the package (in kg): ");
                double weight = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter the description of the package: ");
                String description = scanner.nextLine().trim();

                // Create a MailItem object (package)
                MailItem packageItem = new MailItem(sender, recipient, weight, description);

                // Validate the destination for the package
                if (isValidDestination(packageItem)) {
                    System.out.println("Package details validated successfully!");
                    displayPackageDetails(packageItem);
                } else {
                    System.out.println("Error: Package cannot be sent to the destination.");
                }
            } else {
                System.out.println("Invalid input. Please enter 'letter' or 'package'.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Method to collect user information (name, address, phone number, email)
    private static User collectUserInformation(String userType, Scanner scanner) throws UserException {
        System.out.println("Enter " + userType + " information:");

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        // Validate and create User object
        return new User(name, address, phoneNumber, email);
    }

    // Method to validate if the destination of the package is within allowed locations
    private static boolean isValidDestination(MailItem packageItem) {
        // Extract state code from recipient's address
        String recipientAddress = packageItem.getRecipient().getAddress();
        String stateCode = recipientAddress.substring(recipientAddress.indexOf(" ") + 1, recipientAddress.indexOf("."));

        // Check if state code is valid in DistributionPoints enum
        for (DistributionPoints point : DistributionPoints.values()) {
            if (point.getCode().equals(stateCode)) {
                return true;
            }
        }
        return false;
    }

    // Method to display package details (arrival time and estimated cost)
    private static void displayPackageDetails(Package packageItem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPackage details:");
        System.out.println("Sender: " + packageItem.getSender().getFullName());
        System.out.println("Recipient: " + packageItem.getRecipient().getFullName());
        System.out.println("Estimated arrival time: " + packageItem.getEstimateArrival());
        System.out.print("Address destination: ");
        String Address_dest = scanner.nextLine();
        System.out.println("Estimated cost: $" + packageItem.estimatePrice(Address_dest));
    }
}

