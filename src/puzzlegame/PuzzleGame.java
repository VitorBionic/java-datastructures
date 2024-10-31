package puzzlegame;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Arrays;

public class PuzzleGame {
	
	private static final int CAPACITY = 10;
	private static final boolean REPEAT = true;
	
	private TadPilha stack1;
	private TadPilha stack2;
	private TadPilha stack3;
	private TadPilha[] stacks = new TadPilha[3];
	
	public PuzzleGame() {
		stack1 = new TadPilha(CAPACITY, REPEAT);
		stack2 = new TadPilha(CAPACITY, REPEAT);
		stack3 = new TadPilha(CAPACITY, REPEAT);
		intStackRandomizer(stack1);
		intStackRandomizer2(stack2);
		stacks[0] = stack1;
		stacks[1] = stack2;
		stacks[2] = stack3;
	}
	
	private void intStackRandomizer(TadPilha tp) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i = 0; i < CAPACITY; i++)
			tp.push(rand.nextInt(200));
	}
	
	private void intStackRandomizer2(TadPilha tp) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis() * 2);
		for (int i = 0; i < CAPACITY; i++)
			tp.push(rand.nextInt(200));
	}
	
	
	private String formatInt(int n) {
		String str = "";
		int gaps = 2;
		int num = n;
		while (n / 10 > 0) {
			n = n / 10;
			gaps--;
		}
		
		for (int i = 0; i < gaps; i++)
			str += "0";
		str += num;
		return str;
	}
	
	@Override
	public String toString() {
		String game = "  Stack 1 | Stack 2 | Stack 3\n";
		int[] stc1 = stack1.getElements();
		int[] stc2 = stack2.getElements();
		int[] stc3 = stack3.getElements();
		for (int i = CAPACITY - 1; i >= 0; i--) {
			if (i < stack1.qtd())
				game += "    " + formatInt(stc1[i]) + "   ";
		    else
				game += "    " + "---" + "   ";
			if (i < stack2.qtd())
				game += "    " + formatInt(stc2[i]) + "   ";
		    else
				game += "    " + "---" + "   ";
			if (i < stack3.qtd())
				game += "    " + formatInt(stc3[i]) + "   ";
		    else
				game += "    " + "---" + "   ";
			
			game += "\n";
		}
		return game;		
	}
	
	public void move(int pPop, int pPush) {
		TadPilha stcPop = stacks[pPop];
		int aux;
		if (stcPop.pop())
		    aux = stcPop.getRetorno();
		else {
			throw new InputMismatchException("Tentativa de remover um elemento de uma pilha vazia");
		}
		TadPilha stcPush = stacks[pPush];
		if (stcPush.push(aux))
			return;
		else
			undoPop(stcPop);
			throw new InputMismatchException("Tentativa de adicionar um elemento de uma pilha cheia");
	}
	
	private void undoPop(TadPilha stck) {
		stck.push(stck.getRetorno());
	}
	
	private Integer[] intArrayBoxing(int[] intArray) {
		int leng = intArray.length;
		Integer[] integerArray = new Integer[leng];
		for (int i = 0; i < leng; i++)
			integerArray[i] = intArray[i];
		return integerArray;
	}
	
	private boolean checkAllArrayValuesInStack(int[] array, TadPilha stack) {
		Integer[] stcElements = intArrayBoxing(stack.getElements());
		
		for (int i = 0; i < array.length; i++) {
			boolean finded = false;
			for(int j = 0; j < stcElements.length; j++) {
				if (stcElements[j] != null && array[i] == stcElements[j]) {
					finded = true;
					stcElements[j] = null;
					break;
				}
			}
			if (!finded)
				return false;
		}
		return true;
	}
	
	public boolean checkWin() {
		if (stack3.empty()) {
			int[] arr = new int[20];
			for (int i = 0; i < 10; i++)
				arr[i] = stack1.getElements()[i];
			for (int i = 10; i < 20; i++)
				arr[i] = stack2.getElements()[i - 10];
			Arrays.sort(arr);
			int[] arr1 = Arrays.copyOf(arr, 10);
			int[] arr2 = Arrays.copyOfRange(arr, 10, 20);
			if (checkAllArrayValuesInStack(arr1, stack1) && checkAllArrayValuesInStack(arr2, stack2))
				return true;
			return false;
		} else
			return false;
	}

}

