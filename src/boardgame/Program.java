package boardgame;

import java.util.Scanner;

import boardgame.entities.Dice;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("====================================================");
		System.out.println("                      BOARD GAME                    ");
		System.out.println("====================================================");
		System.out.println();
		System.out.println("SYMBOLS IN SQUARES: ");
		System.out.println("Each square will be filled with two chars, the first will means the STATUS of the square and the second means IF THERE ARE ANY PLAYER IN IT.");
		System.out.println("Possibles FIRST chars:\n\" \" -> FREE\n\"X\" -> MARKED\n\"H\" -> PROPERTY");
		System.out.println("Possibles SECOND chars:\n\" \" -> NO ONE IS HERE\n\"p\" -> ONE OR MORE PLAYERS IS HERE");
		System.out.println("Example: \"Mp\" -> It is MARKED and THERE IS ONE OR MORE PLAYERS IN THERE");
		System.out.println();
		
		System.out.print("How many rounds will the game have: ");
		int rounds = sc.nextInt();
		System.out.print("Number of Players(2-4): ");
		int numPlayers = sc.nextInt();
		System.out.println();
		System.out.println();
		
		BoardGame board = new BoardGame(numPlayers);
		
		for (int i = 1; i <= rounds; i++) {
			System.out.println("Round " + i);
			System.out.println(board);
			
			while (board.getTurnsCount() != numPlayers) {
				Integer player = board.getTurn();
				System.out.println("TURN: Player " + player);
				System.out.println();
				System.out.println("ROLLING DICE...");
				int moves = Dice.roll();
				System.out.println("Player " + player + " will move " + moves + " squares");
				System.out.println();
				System.out.print("Choose the direction forward or back(f/b): ");
				char direction = sc.next().toLowerCase().charAt(0);
				board.move(player, moves, direction);
				System.out.println();
				if (board.getTurnsCount() != numPlayers)
				    System.out.println(board);	
				board.nextTurn();
			}
			board.resetTurnCount();
			System.out.println();
			System.out.println();
			if (board.checkIfItIsOver())
				break;
			
		}
		
		System.out.println(board);
		System.out.println();
		System.out.println();
		
		Integer winner = board.whoWins();
		System.out.println("====================================================");
		System.out.println("                     GAME ENDED                     ");
		System.out.println("====================================================");
		System.out.println();
		System.out.println("====================================================");
		if (winner != null)
		System.out.println("                      Player " + winner + "                      ");
		else
	    System.out.println("                        TIE                         ");	
		System.out.println("====================================================");
	}
	

}
