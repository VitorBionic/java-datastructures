package algorithms.example.isunique;

import java.util.Arrays;

public class Program {
	public static void main(String[] args) {
		Integer[] arr = {5, 89, 11, 3, 4, 2};
		System.out.println(isUniqueLoop(arr));
		System.out.println(isUniqueSort(arr));
	}
	
	// O(n²)
	public static <T> boolean isUniqueLoop(T[] arr) {
		int start = 0;
		int end = arr.length - 1;
		if (start >= end)
			return true;
		for (int i = start; i < end; i++) {
			for (int j = i + 1; j <= end; j++) {
				if (arr[i].equals(arr[j]))
					return false;
			}
		}
		return true;
	}
	
	
	// O(n log n)
	public static <T> boolean isUniqueSort(T[] arr) {
		int start = 0;
		int end = arr.length - 1;
		if (start >= end)
			return true;
		Arrays.sort(arr);
		for (int i = start; i < end; i++) {
			if (arr[i].equals(arr[i + 1]))
					return false;
		}
		return true;
	}
}
