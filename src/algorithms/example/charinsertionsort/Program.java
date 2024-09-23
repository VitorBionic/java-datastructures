package algorithms.example.charinsertionsort;
public class Program {
	public static void main(String[] args) {
		char[] arr = {'j', 'b', 'v', 'd', 'r', 'a'};
		insertionSort(arr);
		System.out.println(arr);
	}
	
	public static void insertionSort(char[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			char cur = a[i];
			int j = i - 1;
			while ((j >= 0) && (a[j] > cur))
				a[j + 1] = a[j--];
			 a[j + 1] = cur;
		}
	}
}
