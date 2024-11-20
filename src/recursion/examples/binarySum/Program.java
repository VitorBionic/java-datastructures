package recursion.examples.binarySum;

public class Program {
    public static void main(String[] args) {
        int[] array = {3, 2, 7, 9, 1};
        System.out.println("Sum: " + binarySum(array));
    }

    public static int binarySum(int[] arr) {
        int leng = arr.length;
        return binarySum(arr, 0, leng);
    }

    private static int binarySum(int[] arr, int i, int n) {
        if (n == 1)
            return arr[i];
        return binarySum(arr, i, n/2) + binarySum(arr, i + n/2, (n + 1) / 2);
    }
}
