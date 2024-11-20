package recursion.examples.reversedArray;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        int[] array = {2, 69, 32, 24, 12, 9};
        reverseArray(array);
        System.out.println("Array Invertido: " + Arrays.toString(array));
        
        int[] array2 = {11, 7, 21, 70, 2, 13};
        reverseArrayNR(array2);
        System.out.println("Array Invertido: " + Arrays.toString(array2));
    }

    public static void reverseArray(int[] arr) {
        int leng = arr.length;
        reverseArray(arr, 0, leng - 1);
    }

    public static void reverseArray(int[] arr, int i, int j) {
        if (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            reverseArray(arr, i + 1, j - 1);
        }
    }
    
    public static void reverseArrayNR(int[] arr) {
    	int i = 0;
    	int j = arr.length - 1;
    	while (i < j) {
    		int temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
    		i++;
    		j--;
    	}
    		
  
    }
} 
