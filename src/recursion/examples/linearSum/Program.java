package recursion.examples.linearSum;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        int[] array = {8, 9, 2, 6, 1, 0};
        System.out.println("The sum of elements of " + Arrays.toString(array) + " is " + sum(array));
    }

    public static int sum(int[] arr) {
        int leng = arr.length;
        return linearSum(arr, leng);
    }

    private static int linearSum(int[] arr, int n) {
        if (n == 1)
            return arr[0];
        return linearSum(arr, n - 1) + arr[n - 1];
    }
}
