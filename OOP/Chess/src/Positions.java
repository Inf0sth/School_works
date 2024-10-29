/**
 * Class to manage correctly the positions for moves  
 */
import java.util.Scanner;
public class Positions {
	// Attributes of team piece and enemy piece
	private Scanner enter = new Scanner(System.in);
	char x;
	int y;
	private char x2;
	private int y2;
	
	// Constructor method
	public Positions() {
		// Positions of the player piece (by the user interaction)
		System.out.println("First enter the position letter of your piece: ");
		this.x = enter.next().charAt(0);
		System.out.println("Now enter the position number of your piece: ");
		this.y = enter.nextInt();
	}
	// Method to convert a char into int
	public static int convertToNumber(char character) {
        int asciiValue = (int) character;
        // ? Evaluates a boolean expression
        int offset = Character.isUpperCase(character) ? 'A' - 1 : 'a' - 1;
        int number = asciiValue - offset; // Set the first value for a,b,c,... whit 1,2,3
        return number;
    }
	// Method to return the correct coordinates for use it in Game an another classes
	public int[] coordinates() { // This apply for the team piece
        int[] coordinates = new int[2];
        coordinates[0] = this.y;
        coordinates[1] = convertToNumber(this.x);
        return coordinates;
    }
	// Positions of the enemy piece (by the user interaction)
	public void destiny() {
		System.out.println("Enter your destiny letter: ");
		this.x2 = enter.next().charAt(0);
		System.out.println("Enter your destiny number: ");
		this.y2 = enter.nextInt();
	}
	
	public int[] destiny_coordinates() { // This apply for the enemy piece
        int[] coordinates = new int[2];
        coordinates[0] = this.y2;
        coordinates[1] = convertToNumber(this.x2);
        return coordinates;
    }
	
}
