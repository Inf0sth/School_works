/**
 * Class: Object oriented programing
 * Poroject: Chess game
 * Languaje: Java
 * This file is the main code
 */
// Libraries to use:
import java.util.Scanner;
// Class game/ the main
public class Game {
	// attributes
	int player1;
	int player2;
	int init_team;
	// Main function
	public static void main(String[] args) {
		// Initialize the scanner for values
		Scanner enter = new Scanner(System.in);
		// We instantiate the class game
		Game game = new Game();
		// Declare the values for the attributes
		game.player1 = 1;
		game.player2 = 2;
		game.init_team = 1;
		// We instantiate the class Board (see file "Board")
		Board board_game = new Board();
		board_game.print_board(); // Get the game board
		// Cycle to play without interruptions
		while(true) {
			if(game.init_team == 1) { // If it's the first turn (white team)
				System.out.println("White's turn (Big)"); 
				Positions game_pos = new Positions(); // We instantiate the class Positions (see Positions file)
				Piece piece = new Piece(1,board_game.getPiece(game_pos.coordinates()[0],game_pos.coordinates()[1])); // We instantiate the class Piece (see Piece file)
				game_pos.destiny(); // Request the next move 
				if(board_game.has_piece(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1])) {
					char objective = board_game.getPiece(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1]);
					if(!piece.sameTeam(objective)) {
						board_game.reloadSquare(game_pos.coordinates()[0], game_pos.coordinates()[1]);
						board_game.attack(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1], piece.getType());
						char[][] updatedBoard = board_game.get_board();
						board_game.set_board(updatedBoard);
						board_game.print_board();
						// Verify if the player win
						System.out.println("it's checkmate? Y | N: ");
						char end = enter.next().charAt(0);
						if(end == 'Y' || end == 'y') {
							System.out.println("You win!!");
							break;
						}else {
							// If the player not win, next turn
							game.init_team = 2;
						}
					}else {System.out.println("Space already used by your team");}
				}
				else {
					if(board_game.isValidSquare(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1])) {
						board_game.reloadSquare(game_pos.coordinates()[0], game_pos.coordinates()[1]);
						board_game.move(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1], piece.getType());
						char[][] updatedBoard = board_game.get_board();
						board_game.set_board(updatedBoard);
						board_game.print_board();
						// Verify if the player win
						System.out.println("it's checkmate? Y | N: ");
						char end = enter.next().charAt(0);
						if(end == 'Y'|| end == 'y') {
							System.out.println("You win!!");
							break;
						}else {
							game.init_team = 2;
						}
					}else{System.out.println("Invalid position");}
				}
			}
			else { // If it's the blakc'steam
				System.out.println("Black's turn (small)");
				Positions game_pos = new Positions();
				Piece piece = new Piece(2,board_game.getPiece(game_pos.coordinates()[0],game_pos.coordinates()[1]));
				game_pos.destiny();
				if(board_game.has_piece(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1])) {
					char objective = board_game.getPiece(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1]);
					if(!piece.sameTeam(objective)) {
						board_game.reloadSquare(game_pos.coordinates()[0], game_pos.coordinates()[1]);
						board_game.attack(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1], piece.getType());
						char[][] updatedBoard = board_game.get_board();
						board_game.set_board(updatedBoard);
						board_game.print_board();
						// Verify if the player win
						System.out.println("it's checkmate? Y | N: ");
						char end = enter.next().charAt(0);
						if(end == 'Y' || end == 'y') {
							System.out.println("You win!!");
							break;
						}else {
							game.init_team = 1;
						}
					}else {System.out.println("Space already used by your team");}
				}
				else {
					if(board_game.isValidSquare(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1])) {
						board_game.reloadSquare(game_pos.coordinates()[0], game_pos.coordinates()[1]);
						board_game.move(game_pos.destiny_coordinates()[0],game_pos.destiny_coordinates()[1], piece.getType());
						char[][] updatedBoard = board_game.get_board();
						board_game.set_board(updatedBoard);
						board_game.print_board();
						// Verify if the player win
						System.out.println("it's checkmate? Y | N: ");
						char end = enter.next().charAt(0);
						if(end == 'Y' || end == 'y') {
							System.out.println("You win!!");
							break;
						}else {
							game.init_team = 1;
						}
					}else{System.out.println("Invalid position");}
				}
			}
		}
		enter.close();
	}

}
