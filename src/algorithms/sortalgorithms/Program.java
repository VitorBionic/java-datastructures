package algorithms.sortalgorithms;

import java.util.Arrays;

public class Program {
	public static void main(String[] args) {
		Integer[] array = Sort.generateRandomArray(10, 40, 250);
		
		Integer[] arr = array.clone();
		
		System.out.println("Insertion Sort");
		System.out.println("------------------------------------");
		System.out.println("Unsorted Array:");
		System.out.println(Arrays.toString(arr));
		
		long start = System.nanoTime();
		Sort.insertionSort(arr);
		long end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time = " + (double) (end - start) + " nanoseconds");
		
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		System.out.println();
		
        arr = array.clone();
		
		System.out.println("Shell Sort");
		System.out.println("------------------------------------");
		System.out.println("Unsorted Array:");
		System.out.println(Arrays.toString(arr));
		
		start = System.nanoTime();
		Sort.shellSort(arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time = " + (double) (end - start) + " nanoseconds");
		
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		System.out.println();
		
		arr = array.clone();
		
		System.out.println("Selection Sort");
		System.out.println("------------------------------------");
		System.out.println("Unsorted Array:");
		System.out.println(Arrays.toString(arr));
		
		start = System.nanoTime();
		Sort.selectionSort(arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time = " + (double) (end - start) + " nanoseconds");
		
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		System.out.println();
		
		arr = array.clone();
		
		System.out.println("Bubble Sort");
		System.out.println("------------------------------------");
		System.out.println("Unsorted Array:");
		System.out.println(Arrays.toString(arr));
		
		start = System.nanoTime();
		Sort.bubbleSort(arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time = " + (double) (end - start) + " nanoseconds");
		
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		System.out.println();
		
        arr = array.clone();
		
		System.out.println("Quick Sort");
		System.out.println("------------------------------------");
		System.out.println("Unsorted Array:");
		System.out.println(Arrays.toString(arr));
		
		start = System.nanoTime();
		Sort.quickSort(arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time with Hoare's Partition = " + (double) (end - start) + " nanoseconds");
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		arr = array.clone();
		start = System.nanoTime();
		Sort.lumotoQuickSort(arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time with Lumoto Partition = " + (double) (end - start) + " nanoseconds");
		
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		System.out.println();
		
        arr = array.clone();
		
		System.out.println("Merge Sort");
		System.out.println("------------------------------------");
		System.out.println("Unsorted Array:");
		System.out.println(Arrays.toString(arr));
		
		start = System.nanoTime();
		Sort.mergeSort(Integer.class, arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time = " + (double) (end - start) + " nanoseconds");
		
		System.out.println("Sorted Array:");
		System.out.println(Arrays.toString(arr));
		
		System.out.println();
		System.out.println();
		
		arr = array.clone();
        
        System.out.println("Heap Sort");
        System.out.println("------------------------------------");
        System.out.println("Unsorted Array:");
        System.out.println(Arrays.toString(arr));
        
        start = System.nanoTime();
        Sort.heapSort(arr);
        end = System.nanoTime();
        System.out.println();
        System.out.println("Execution time = " + (double) (end - start) + " nanoseconds");
        
        System.out.println("Sorted Sort:");
        System.out.println(Arrays.toString(arr));
        
        System.out.println();
        System.out.println();
		
	}

}
