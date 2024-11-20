package recursion.examples.fibonnaci;

public class Program {
    public static void main(String[] args) {
        System.out.println(binaryFib(9));
        System.out.println();

        System.out.println(linearFib(9));
        
        System.out.println();
        System.out.println(binaryFibWithDynamicProg(9));
    }

    public static int binaryFib(int k) {
        if (k <= 1)
            return k;
        else
            return binaryFib(k - 1) + binaryFib(k - 2);
    }
    
    public static int linearFib(int k) {
    	int[] fib = linearFibonnaci(k);
    	return fib[0];
    }
    
    private static int[] linearFibonnaci(int k) {
        if (k <= 1) {
            int[] arr = new int[2];
            arr[0] = k;
            arr[1] = 0;
            return arr;
        } else {
            int[] arr = linearFibonnaci(k - 1);
            int temp = arr[0];
            arr[0] = arr[0] + arr[1];
            arr[1] = temp;
            return arr;
        }
    }
    
    
    public static int binaryFibWithDynamicProg(int k) {
    	return binaryFibWithDynamicProg(k, new Integer[k + 1]);
    }
    
    private static int binaryFibWithDynamicProg(int k, Integer[] memo) {
    	if (memo[k] != null)
    		return memo[k];
    	if (k <= 1) {
    		memo[k] = k;
    		return memo[k];
        }
    	memo[k] = binaryFibWithDynamicProg(k - 1, memo) + binaryFibWithDynamicProg(k - 2, memo);
    	return memo[k];
    }
}