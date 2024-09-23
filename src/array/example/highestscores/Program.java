package array.example.highestscores;

public class Program {
	
	public static void main(String[] args) {
		
		// Inicialization
		GameEntry gm1 = new GameEntry("Mike", 1105);
		GameEntry gm2 = new GameEntry("Rob", 750);
		GameEntry gm3 = new GameEntry("Paul", 720);
		GameEntry gm4 = new GameEntry("Anna", 660);
		GameEntry gm5 = new GameEntry("Rose", 590);
		GameEntry gm6 = new GameEntry("Jack", 510);
		
		Scores scr = new Scores();
		scr.add(gm1);
		scr.add(gm2);
		scr.add(gm3);
		scr.add(gm4);
		scr.add(gm5);
		scr.add(gm6);
		
		System.out.println("Dados iniciais: ");
		System.out.println(scr);
		
		// Adding
		GameEntry gm7 = new GameEntry("Jill", 740);
		System.out.println();
		System.out.println("Adicionando: " + gm7);
		scr.add(gm7);
		System.out.println("Dados com " + gm7 + " adicionado: ");
		System.out.println(scr);
		
		// Removing
		System.out.println();
		System.out.println("Removendo: " + gm3);
		scr.remove(3);
		System.out.println("Dados com " + gm3 + " removido: ");
		System.out.println(scr);
		
	}
}
