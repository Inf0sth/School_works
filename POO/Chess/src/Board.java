/**
 *  Class to declare the board for the game.
 *  methods for the board
 *  and validations as methods
 */
public class Board {
	private char[][] board_chess; 
	
	public Board() {
		// Initialize the board
		this.board_chess = new char[][]{{'*','a','b','c','d','e','f','g','h'},
				{'1','T','H','B','K','Q','B','H','T'},
				{'2','P','P','P','P','P','P','P','P'},
				{'3','█','_','█','_','█','_','█','_'},
				{'4','_','█','_','█','_','█','_','█'},
				{'5','█','_','█','_','█','_','█','_'},
				{'6','_','█','_','█','_','█','_','█'},
				{'7','p','p','p','p','p','p','p','p'},
				{'8','t','h','b','k','q','b','h','t'}};
		
	}
	// Set the board to the main
	public char[][] get_board(){
		return this.board_chess;
	}
	// Get the board form the main
	public void set_board(char[][] boardChess) {
		this.board_chess = boardChess;
	}
	// Print the board as a matrix
	public void print_board() {
		System.out.println("");
	    for (int i = 0; i < this.board_chess.length; i++) {
	        System.out.print("[");
	        for (int k = 0; k < this.board_chess[i].length; k++) {
	            System.out.print(this.board_chess[i][k]);
	            if (k < this.board_chess[i].length - 1) {
	                System.out.print("|");
	            }
	        }
	        System.out.println("]");
	    }
	    System.out.println("");
	}

	// Check the board limits
	public boolean boardLimits(int row, int column) {
	    int numRows = this.board_chess.length;
	    int numColumns = this.board_chess[1].length;

	    return row > 0 && row < numRows && column > 0 && column < numColumns;
	}
	// Valid an empty position (whithout enemies)
	public boolean isValidSquare(int row, int column) {
	    if (!boardLimits(row, column)) {
	        return false;
	    }
	    return this.board_chess[row][column] == '_' || this.board_chess[row][column] == '█';
	}
	// Check if the position has a piece
	public boolean has_piece(int row, int column) {
		if(!boardLimits(row, column)) {
			return false;
		}
		return this.board_chess[row][column] != '_' && this.board_chess[row][column] != '█';
	}
	// Return the piece in a position in from the board
	public char getPiece(int row, int column) {
	    return this.board_chess[row][column];
	}
	
	// Replace an enemie piece for us piece 
	public void attack(int row, int column, char piece) {
		this.board_chess[row][column] = piece;
	}
	// The diference to the method attack, is i want to declare a counter later to killed enemies
	// Move the piece to an empty position
	public void move(int row, int column, char piece) {
		this.board_chess[row][column] = piece;
	}
	// Set a color to the past position of the piece
	public void reloadSquare(int row, int column) {
		if(column+1<this.board_chess[0].length) {
			if(this.board_chess[row][column+1] == '_') {
				this.board_chess[row][column] = '█';
			}else {this.board_chess[row][column] = '_';}
		}
		else {
			if(this.board_chess[row][column-1] == '_') {
				this.board_chess[row][column] = '█';
			}else {this.board_chess[row][column] = '_';}
		}
	}
}
