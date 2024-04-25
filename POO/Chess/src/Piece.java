/**
 * Class to declare somethings of the pieces
 * 
 */
// Class piece
public class Piece {
	// Attributes (only managed internally) 
    private int team;
    private char type;
    // Constructor method
    public Piece(int team, char type) {
    	this.team = team;
    	this.type = type;
    }
    // Set team to main
    public int getTeam() {
        return team;
    }
    // Set type piece to main
    public char getType() {
        return type;
    }
    // Verify if the objective piece is an enemy or not
    public boolean sameTeam(char objective) {
        if (Character.isUpperCase(this.type) && Character.isUpperCase(objective)) {
            return true;
        } else if (Character.isLowerCase(this.type) && Character.isLowerCase(objective)) {
            return true;
        } else {
            return false;
        }
    }
    
}

