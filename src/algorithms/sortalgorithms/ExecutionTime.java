package algorithms.sortalgorithms;


public class ExecutionTime {

	public static void main(String[] args) {
        Integer[] array = Sort.generateRandomArray(9999, 10000, 100000);
		
		Integer[] arr = array.clone();
		
		System.out.println("Insertion Sort");
		System.out.println("------------------------------------");
		
		long start = System.nanoTime();
		Sort.insertionSort(arr);
		long end = System.nanoTime();
		System.out.println("Execution time = " + (double) (end - start) / 1000000 + " miliseconds");
		
		System.out.println();
		System.out.println();
		
        arr = array.clone();
		
		System.out.println("Shell Sort");
		System.out.println("------------------------------------");
		
		start = System.nanoTime();
		Sort.shellSort(arr);
		end = System.nanoTime();
		System.out.println("Execution time = " + (double) (end - start) / 1000000 + " miliseconds");
		
		System.out.println();
		System.out.println();
		
		arr = array.clone();
		
		System.out.println("Selection Sort");
		System.out.println("------------------------------------");
		
		start = System.nanoTime();
		Sort.selectionSort(arr);
		end = System.nanoTime();
		System.out.println("Execution time = " + (double) (end - start) / 1000000 + " miliseconds");
		
		System.out.println();
		System.out.println();
		
		arr = array.clone();
		
		System.out.println("Bubble Sort");
		System.out.println("------------------------------------");
		
		start = System.nanoTime();
		Sort.bubbleSort(arr);
		end = System.nanoTime();
		System.out.println("Execution time = " + (double) (end - start) / 1000000 + " miliseconds");
		
		System.out.println();
		System.out.println();
		
        arr = array.clone();
		
		System.out.println("Quick Sort");
		System.out.println("------------------------------------");
		
		start = System.nanoTime();
		Sort.quickSort(arr);
		end = System.nanoTime();
		System.out.println("Execution time with Hoare's Partition = " + (double) (end - start) / 1000000 + " miliseconds");
		
		arr = array.clone();
		start = System.nanoTime();
		Sort.lumotoQuickSort(arr);
		end = System.nanoTime();
		System.out.println();
		System.out.println("Execution time with Lumoto Partition = " + (double) (end - start) / 1000000 + " miliseconds");
		
		System.out.println();
		System.out.println();
		
        arr = array.clone();
		
		System.out.println("Merge Sort");
		System.out.println("------------------------------------");
		
		start = System.nanoTime();
		Sort.mergeSort(Integer.class, arr);
		end = System.nanoTime();
		System.out.println("Execution time = " + (double) (end - start) / 1000000 + " miliseconds");
		
		System.out.println();
		System.out.println();
		
		arr = array.clone();
		
		System.out.println("Heap Sort");
        System.out.println("------------------------------------");
        
        start = System.nanoTime();
        Sort.heapSort(arr);
        end = System.nanoTime();
        System.out.println("Execution time = " + (double) (end - start) / 1000000 + " miliseconds");
        
        System.out.println();
        System.out.println();
        
        arr = array.clone();
	}
}
