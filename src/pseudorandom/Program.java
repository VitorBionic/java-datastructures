package pseudorandom;

import java.util.Random;
import java.util.Arrays;

public class Program {

	public static void main(String[] args) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);
		int[] old = arr.clone();
		System.out.println("arrays equal before sort: " + Arrays.equals(old, arr));
		Arrays.sort(arr);
		System.out.println("arrays equal after sort: " + Arrays.equals(old, arr));
		System.out.println("old = " + Arrays.toString(old));
		System.out.println("arr = " + Arrays.toString(arr));
		
	}
}
