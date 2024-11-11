package boardgame.entities;

import java.util.Random;

public class Dice {
	
	private static final Random rand = new Random(System.currentTimeMillis()); 
	
	public static int roll() {
		return rand.nextInt(1, 7);
	}
	
}
