package duckduckgoose;

import linkedlist.circularlinkedlist.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class DuckDuckGoose {
	public static void main(String[] args) {
		LinkedList<String> ll = new LinkedList<String>();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("How many rounds will the game have? ");
		int N = sc.nextInt();
		System.out.println();
		
		String it;
		String goose;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		String[] names = {"Bob", "Jen", "Pam", "Tom", "Ron", "Vic", "Sue", "Joe"};
		
		for (int i = 0; i < names.length; i++) {
			ll.add(names[i]);
			ll.advance();
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println("Playing Duck, Duck, Goose for " + ll.toString());
			it = ll.remove();
			System.out.println(it + " is it");
			while (rand.nextBoolean() || rand.nextBoolean()) {
				ll.advance();
				System.out.println(ll.getCursor() + " is a duck.");
			}
			goose = ll.remove();
			System.out.println(goose + " is the goose!");
			if (rand.nextBoolean()) {
				System.out.println("The goose won!");
				ll.add(goose);
				ll.advance();
				ll.add(it);
			}
			else {
				System.out.println("The goose lost!");
				ll.add(it);
				ll.advance();
				ll.add(goose);
			}
		}
		System.out.println("Final circle is " + ll.toString());
	}
}
