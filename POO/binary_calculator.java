/**
 * Binary calculator:
 * Add binary numbers
 * Substract binary numbers
 * Parse number to binary( the number may be a float)
 * Get the hex and octal representation of any float and binary number
 * HINT: Use non primitive types
 */
//
import java.util.Scanner;

public class binary_calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = 1;
        while (x == 1) {
            // Request the operation to do to the user
            System.out.println("Binary Calculator loaded");
            System.out.println("Menu:");
            System.out.println("1. Add binary numbers");
            System.out.println("2. Substract binary numbers");
            System.out.println("3. Convert number to binary");
            System.out.println("4. Get hexadecimall representation");
            System.out.println("5. Get octal representation");
            System.out.println("Select an option:");
            int option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    binary_add(scanner);
                    break;
                case 2:
                    binary_substract(scanner);
                    break;
                case 3:
                    convert_to_binary(scanner);
                    break;
                case 4:
                    get_hex_representation(scanner);
                    break;
                case 5:
                    get_oct_representation(scanner);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            System.out.println("\nAnother operation?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            x = scanner.nextInt();
        }
        scanner.close();
    }
    
    private static void binary_add(Scanner scanner) {
        System.out.println("Enter the first binary number:");
        String binary1 = scanner.next();
        System.out.println("Enter the second binary number:");
        String binary2 = scanner.next();
        
        int number1 = Integer.parseInt(binary1, 2);
        int number2 = Integer.parseInt(binary2, 2);
        
        int result = number1 + number2;
        
        String binaryresult = Integer.toBinaryString(result);
        
        System.out.println("Add result: " + binaryresult);
    }
    
    private static void binary_substract(Scanner scanner) {
        System.out.println("Enter the minuend binary:");
        String binary1 = scanner.next();
        System.out.println("Enter the subtracting binary");
        String binary2 = scanner.next();
        
        int number1 = Integer.parseInt(binary1, 2);
        int number2 = Integer.parseInt(binary2, 2);
        
        int result = number1 - number2;
        
        String binaryresult = Integer.toBinaryString(result);
        
        System.out.println("Substract result: " + binaryresult);
    }
    
    private static void get_hex_representation(Scanner scanner) {
        System.out.println("Enter a binary number:");
        String binary = scanner.next();
        int number = Integer.parseInt(binary, 2);
        String hex = Integer.toHexString(number);        
        System.out.println("Hexadecimal number: " + hex.toUpperCase());
    }

    private static void get_oct_representation(Scanner scanner){
        System.out.println("Enter a binary number:");
        String binary = scanner.next();

        int number = Integer.parseInt(binary, 2);
        String octal = Integer.toOctalString(number);

        System.out.println("Octal number: " + octal);
    }
    
    private static void convert_to_binary(Scanner scanner) {
        System.out.println("Enter a number:");
        long number = scanner.nextLong();
        
        String binary = Long.toBinaryString(number);
        
        System.out.println("Binary: " + binary);
    }
}
