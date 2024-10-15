package tictactoe;

public class TicTacToe {
	
	protected static final int X = 1, O = -1;
	protected static final int EMPTY = 0;
	protected int[][] board;
	protected int player;
	
	public TicTacToe() {
		clearBoard();
	}
	
	public void clearBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = EMPTY;
			}
		}
		player = X;
	}
	
	public void putMark(int i, int j) throws IllegalArgumentException {
		if ((i < 0) || (i > 2) || (j < 0) || (j > 2))
			throw new IllegalArgumentException("Invalid board position");
		if (board[i][j] != EMPTY)
			throw new IllegalArgumentException("Board position occupied");
		board[i][j] = player;
		player = -player;
	}
	
	public boolean isWin(int mark) {
		return (board[0][0] + board[0][1] + board[0][2] == mark * 3 ||
			board[1][0] + board[1][1] + board[1][2] == mark * 3 ||
			board[2][0] + board[2][1] + board[2][2] == mark * 3 ||
			board[0][0] + board[1][0] + board[2][0] == mark * 3 ||
			board[0][1] + board[1][1] + board[2][1] == mark * 3 ||
			board[0][2] + board[1][2] + board[2][2] == mark * 3 ||
			board[0][0] + board[1][1] + board[2][2] == mark * 3 ||
			board[2][0] + board[1][1] + board[0][2] == mark * 3);
	}
	
	public int winner() {
		if (isWin(X))
			return X;
		else if (isWin(O))
			return O;
		else
			return(0);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				switch (board[i][j]) {
				    case X:
				    	s += "X";
				    	break;
				    case O:
				    	s += "O";
				    case EMPTY:
				    	s += " ";
				    	break;
				}
				if (j < 2)
					s += "|";
			}
			if (i < 2)
				s += "\n-----\n";
		}
		return s;
	}
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.putMark(1, 1);
		game.putMark(2, 2);
		game.putMark(0, 1);
		game.putMark(1, 2);
		game.putMark(2, 0);
		System.out.println(game.toString());
		int winningPlayer = game.winner();
		if (winningPlayer != 0)
			System.out.println(winningPlayer + " wins");
		else
			System.out.println("Tie");
	}
}
