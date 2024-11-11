package boardgame;

import linkedlist.circulardoublylinkedlist.LinkedList;
import node.doublynode.Node;
import boardgame.entities.Square;

public class BoardGame {

	private static final int SIZE = 26;

	private Integer[] locations;
	private int turnsCount;
	private boolean[] canPlay;
	private Node<Square> start;
	private Integer turn;
	private LinkedList<Square> board;
	

	public BoardGame(int players) {
		board = new LinkedList<Square>();
		for (int i = 1; i <= SIZE; i++) {
			board.addAfter(new Square(i, players));
			board.advanceForward();
		}
		board.advanceForward();

		start = board.getCursorNode();
		locations = new Integer[players];
		canPlay = new boolean[players];
		for (int i = 1; i <= locations.length; i++) {
			start.getElement().addPlayer(i);
			locations[i - 1] = 1;
			canPlay[i - 1] = true;
		}
		turn = 1;
	}
	
	public int getTurnsCount() {
		return turnsCount;
	}
	
	public void resetTurnCount() {
		turnsCount = 0;
	}
	
	public int playersCount() {
		return locations.length;
	}
	
	public Integer getTurn() {
		return turn;
	}
	
	public void nextTurn() {
		if (turn == playersCount()) {
			turn = 0;
		}		
		Integer current = turn + 1;
		while (!canPlay[current - 1]) {
			canPlay[current - 1] = true;
			turnsCount++;
			current++;
		}
		turn = current;
	}
	
	public void move(Integer player, int moves, char direction) {
		int location = locations[player - 1];
		if (location <= SIZE / 2) {
			for (int i = 1; i < location; i++)
				board.advanceForward();
		} else {
			for (int i = 1; i < location; i++)
				board.advanceForward();
		}
		board.getCursor().removePlayer(player);
		locations[player - 1] = null;
		
		if (direction == 'f') {
		    for (int i = 0; i < moves; i++)
			    board.advanceForward();
		} else {
			for (int i = 0; i < moves; i++)
			    board.advanceBack();
		}
		
		Square square = board.getCursor();
		square.addPlayer(player);
		locations[player - 1] = square.getId();
		
		int statusId = square.getStatus().getId();
		Square prev = board.getCursorNode().getPrev().getElement();
		Square next = board.getCursorNode().getPrev().getElement();
		switch (statusId) {
		case 0:;
			if (prev.getOwner() == player || next.getOwner() == player) {
				square.setStatus(2);
				square.setOwner(player);
			} else {
			square.setStatus(1);
			square.setMarker(player);
			}
			break;
		case 1:
			if (square.getMarker() == player) {
				square.setMarker(null);
				square.setStatus(2);
				square.setOwner(player);
				if (prev.getOwner() != null && prev.getOwner() != player)  {
					Square prevOfPrev = board.getCursorNode().getPrev().getPrev().getElement();
					if (prevOfPrev.getOwner() == player) {
						prev.setMarker(prev.getOwner());
						prev.setOwner(null);
						prev.setStatus(1);
					}
				}
				if (next.getOwner() != null && next.getOwner() != player)  {
					Square nextOfNext = board.getCursorNode().getNext().getNext().getElement();
					if (nextOfNext.getOwner() == player) {
						next.setMarker(next.getOwner());
						next.setOwner(null);
						next.setStatus(1);
					}
				}
			} else {
				square.setMarker(null);
				square.setStatus(0);
			}
			break;
		case 2:
			if (square.getMarker() != player) {
				canPlay[player - 1] = false;
			}
			break;
		}
			
		board.setCursorNode(start);
		turnsCount++;
		
	}
	
	public boolean checkIfItIsOver() {
		int[] properties = new int[playersCount()];
		int freeSquaresCount = 0;
		for (int i = 0; i < SIZE; i++) {
			Square square = board.getCursor();
			int statusId = square.getStatus().getId();
			if (statusId == 2) {
				Integer owner = square.getOwner();
				properties[owner - 1]++;
			} else if(statusId == 0)
				freeSquaresCount++;
			board.advanceForward();
		}
		Integer[] maxPropertiesPlayers = max(properties);
		if (maxPropertiesPlayers.length == 1) {
			int max = properties[maxPropertiesPlayers[0] - 1];
			properties[maxPropertiesPlayers[0] - 1] = -1;
			Integer[] newMaxPropertiesPlayers = max(properties);
			int secondMax = properties[newMaxPropertiesPlayers[0] - 1];
			if (max > secondMax + freeSquaresCount)
				return true;
		}
		return false;
	}
	
	private Integer[] max(int[] arr) {
		int max = 0;
		for (int value : arr) {
			if (value > max)
				max = value;
		}
		
		int leng = 0;
		for (int value : arr) {
			if (value == max)
				leng++;
		}
		
		Integer[] maxPlayers = new Integer[leng];
		for (int i = 0; i < maxPlayers.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == max) {
					maxPlayers[i] = j + 1;
					arr[j]  = -1;
					break;
				}
			}
		}
		return maxPlayers;			
	}
	
	private Integer[] maxOfMax(Integer[] max1, int[] max2) {
		int[] filter = new int[max1.length];
		for (int i = 0; i < max1.length; i++) {
			filter[i] = max2[max1[i] - 1];
		}
		
		int max = 0;
		for (int i = 0; i < filter.length; i++) {
			if (filter[i] > max)
				max = filter[i];
		}
		
		int leng = 0;
		for (int value : filter) {
			if (value == max)
				leng++;
		}
		
		Integer[] maxOfMaxPlayers = new Integer[leng];
		for (int i = 0; i < maxOfMaxPlayers.length; i++) {
			for (int j = 0; j < filter.length; j++) {
				if (filter[j] == max) {
					maxOfMaxPlayers[i] = max1[j];
					filter[j] = -1;
					break;
				}
			}
		}
		
		return maxOfMaxPlayers;
		
	}
	
	public Integer whoWins() {
		int[] properties = new int[playersCount()];
		int[] marks = new int[playersCount()];
		int[] connections = new int[playersCount()];
		for (int i = 0; i < SIZE; i++) {
			Square square = board.getCursor();
			int statusId = square.getStatus().getId();
			switch (statusId) {
			case 1:
				Integer marker = square.getMarker();
				marks[marker - 1]++;
				break;
			case 2:
				Integer owner = square.getOwner();
				properties[owner - 1]++;
				Square next = board.getCursorNode().getNext().getElement();
				Square prev = board.getCursorNode().getPrev().getElement();
				if (next.getStatus().getId() == 2)
					connections[owner - 1]++;
				if (prev.getStatus().getId() == 2)
					connections[owner - 1]++;
				break;
			}
			board.advanceForward();
		}
		Integer[] maxPropertiesPlayers = max(properties);
		if (maxPropertiesPlayers.length == 1)
			return maxPropertiesPlayers[0];
		else {
			Integer[] maxOfMaxMarksPlayers = maxOfMax(maxPropertiesPlayers, marks);
			if (maxOfMaxMarksPlayers.length == 1)
				return maxOfMaxMarksPlayers[0];
			else {
				Integer[] maxOfMaxOfMaxConnectionsPlayers = maxOfMax(maxOfMaxMarksPlayers, connections);
				if (maxOfMaxOfMaxConnectionsPlayers.length == 1)
					return maxOfMaxOfMaxConnectionsPlayers[0];
				else
					return null;
			}
		}
		
	}

	private void printSquare(StringBuilder sb) {
		switch (board.getCursor().getStatus().getId()) {
		case 0:
			sb.append(" ");
			break;
		case 1:
			sb.append("X");
			break;
		case 2:
			sb.append("H");
			break;
		}
		if (board.getCursor().getPlayers().isEmpty())
			sb.append(" |");
		else
			sb.append("p|");
	}
	
	private void printLocations(StringBuilder sb) {
		sb.append("LOCATIONS\n");
		for (int i = 1; i <= playersCount(); i++)
			sb.append("Player " + i + " is in Square: " + locations[i - 1] + "\n");
	}
	
	private void printSquaresStatus(StringBuilder sb) {
		sb.append("SQUARES STATUS\n");
		for (int i = 0; i < SIZE; i++) {
			board.advanceForward();
			Square current = board.getCursor();
			if (current.getStatus().getId() != 0) {
				if (current.getStatus().getId() == 1) {
					sb.append("The Square " +  current.getId() + " is marked by Player " + current.getMarker());
				} else {
					sb.append("The Square " +  current.getId() + " is property of Player " + current.getOwner());
				}
				sb.append("\n");
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("  |");
		int width = (int) ((int) SIZE / 2.6);
		int side = (SIZE - 2 * width) / 2;

		for (int i = 0; i < (width + side); i++)
			board.advanceForward();

		int mid = width + side + 1;
		for (int i = mid; i < mid + width; i++) {
			if (i < 10)
				sb.append("0");
			sb.append(i + "|");
		}
		sb.append("\n");

		for (int i = 0; i < width * 3.5; i++)
			sb.append("-");
		sb.append("\n");

		sb.append("  |");
		for (int i = 0; i < width; i++) {
			printSquare(sb);
			board.advanceForward();
		}
		sb.append("\n");

		for (int i = 0; i < width * 3.5; i++)
			sb.append("-");
		sb.append("\n");

		board.advanceBack();
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < width + i * 2; j++)
				board.advanceBack();
			int id = board.getCursor().getId();
			if (id < 10)
				sb.append("0");
			sb.append(id);
			sb.append("|");
			printSquare(sb);

			for (int j = 0; j < width * 3.5 - 12; j++)
				sb.append(" ");

			for (int j = 0; j <= width + i * 2; j++)
				board.advanceForward();
			sb.append("|");
			printSquare(sb);
			id = board.getCursor().getId();
			if (id < 10)
				sb.append("0");
			sb.append(id);

			sb.append("\n");

			if (i != side - 1) {
				for (int j = 0; j < 6; j++)
					sb.append("-");
				for (int j = 0; j < width * 3.5 - 12; j++)
					sb.append(" ");
				for (int j = 0; j < 6; j++)
					sb.append("-");
			} else {
				for (int j = 0; j < width * 3.5; j++)
					sb.append("-");
			}
			sb.append("\n");
		}
		for (int i = 0; i < width; i++)
			board.advanceForward();

		sb.append("  |");
		for (int i = 0; i < width; i++) {
			printSquare(sb);
			board.advanceBack();
		}
		sb.append("\n");

		for (int i = 0; i < width * 3.5; i++)
			sb.append("-");
		sb.append("\n");

		sb.append("  |");
		for (int i = width; i > 0; i--) {
			if (i < 10)
				sb.append("0");
			sb.append(i + "|");
		}
		sb.append("\n");
		sb.append("\n");
		printLocations(sb);
		sb.append("\n");
		sb.append("\n");
		printSquaresStatus(sb);
		sb.append("\n");

		board.advanceForward();
		return sb.toString();
	}

}
