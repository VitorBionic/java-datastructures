package puzzlegame;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PuzzleGame pg = new PuzzleGame();
		System.out.println("====================================================");
		System.out.println("          PUZZLE GAME - TORRES DE  HANOI            ");
		System.out.println("====================================================");
		System.out.println("INSTRUÇÕES:");
		System.out.println("O jogo é inicializado com o preenchimento das pilhas 1 e 2 com números aleatórios.");
		System.out.println("Ao final do jogo, a metade dos números menores deverá ficar na pilha 1.");
		System.out.println("A metade dos números maiores deverá ficar na pilha 2.");
		System.out.println("A pilha 3 será utilizada para as manobras.");
		do {
			System.out.println("");
			System.out.println(pg);
			System.out.print("Pilha cujo topo será realocado(1/2/3): ");
			int pop = sc.nextInt();
			System.out.print("Pilha onde será colocado(1/2/3): ");
			int push = sc.nextInt();
			pg.move(pop - 1, push - 1);
		} while (!pg.checkWin());
		System.out.println("");
		System.out.println(pg);
		System.out.println("");
		System.out.println("====================================================");
		System.out.println("          PARABÉNS, AMIGO. VOCÊ É O FODÃO           ");
		System.out.println("====================================================");
	}
}

