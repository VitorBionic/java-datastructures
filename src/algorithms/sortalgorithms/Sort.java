package algorithms.sortalgorithms;

import java.lang.reflect.Array;
import java.util.Random;

public class Sort {
	
	private static final Random rand = new Random(System.currentTimeMillis());
	
	public static Integer[] generateRandomArray(int minLength, int maxLength, int maxValue) {
		int leng = rand.nextInt(minLength, maxLength);
		Integer[] array = new Integer[leng];
		for (int i = 0; i < leng; i++) {
			array[i] = rand.nextInt(maxValue);
		}
		return array;
	}
	
	public static Integer[] generateRandomArray(int maxLength, int maxValue) {
		int leng = rand.nextInt(1, maxLength);
		Integer[] array = new Integer[leng];
		for (int i = 0; i < leng; i++) {
			array[i] = rand.nextInt(maxValue);
		}
		return array;
	}
	
	public static Integer[] generateRandomArray(int maxLength) {
		int leng = rand.nextInt(1, maxLength);
		Integer[] array = new Integer[leng];
		for (int i = 0; i < leng; i++) {
			array[i] = rand.nextInt(2500);
		}
		return array;
	}
	
	private static <T> void swap(T[] arr, int x, int y) {
		T temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	// Insertion Sort
	// O(n²)
	public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
		int leng =  arr.length;
		for (int i = 1; i < leng; i++)  {
			T cur = arr[i];
			int j = i - 1;
			while (j >= 0 && cur.compareTo(arr[j]) < 0)
				arr[j + 1] = arr[j--];
			arr[j + 1] = cur;
		}
	}
	
	// Shell Sort
	// O(n(log(n))²)
	public static <T extends Comparable<? super T>> void shellSort(T[] arr) {
		int leng = arr.length;
		for (int gap = leng/2; gap > 0; gap /= 2) {
			for (int i = gap; i < leng; i++) {
				T cur = arr[i];
				int j = i - gap;
				while (j >= 0  && cur.compareTo(arr[j]) < 0) {
					arr[j + gap] = arr[j];
					j -= gap;
				}
				arr[j + gap] = cur;
			}
		}
	}
	
	// Selection Sort
	// O(n²)
	public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int lowest = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[lowest]) < 0)
					lowest = j;
			}
			swap(arr, i, lowest);
		}
	}
	
	// Bubble Sort
	// O(n²)
	public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			boolean swapped = false;
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					swap(arr, j, j + 1);
					swapped = true;
				}
			}
			if (!swapped)
				break;
		}
	}
	
	// Quick Sort
	// O(n²)
	public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	public static <T extends Comparable<? super T>> void lumotoQuickSort(T[] arr) {
		lumotoQuickSort(arr, 0, arr.length - 1);
	}
	
    private static <T extends Comparable<? super T>> int lumotoPartition(T[] arr, int low, int high) {
		
		T pivot = arr[high];
		
		int i = low - 1;
		
		for (int j = low; j <= high - 1; j++) {
			if (arr[j].compareTo(pivot) < 0) {
				i++;
				swap(arr, i, j);
			}
		}
		
		swap(arr, i + 1, high);
		return i + 1;
		
	}
    
    private static <T extends Comparable<? super T>> int hoaresPartition(T[] arr, int low, int high) {
    	
    	T pivot = arr[high];
    	int li = low, ri = high;
    	
    	 while (li < ri) {
             
    		 while (arr[li].compareTo(pivot) <= 0 && li < ri)
                 li++;

    		 while (arr[ri].compareTo(pivot) >= 0 && li < ri)
                 ri--;

    	    swap(arr, li, ri);
    	}
    	swap(arr, li, high);
    	return li;
    	
    }
	
	private static <T extends Comparable<? super T>> void quickSort(T[] arr, int low, int high) {
		if (low < high) {
			
			int pivotIndex = rand.nextInt(high - low) + low;
			swap(arr, pivotIndex, high);
			
			int newPivotIndex = hoaresPartition(arr, low, high);
			
			quickSort(arr, low, newPivotIndex - 1);
			quickSort(arr, newPivotIndex + 1, high);
		}
	}
	
	private static <T extends Comparable<? super T>> void lumotoQuickSort(T[] arr, int low, int high) {
		if (low < high) {
			
			int pivotIndex = rand.nextInt(high - low) + low;
			swap(arr, pivotIndex, high);
			
			int newPivotIndex = lumotoPartition(arr, low, high);
			
			lumotoQuickSort(arr, low, newPivotIndex - 1);
			lumotoQuickSort(arr, newPivotIndex + 1, high);
		}
	}
	
	// Merge Sort
	// O(n log n)
	public static <T extends Comparable<? super T>> void mergeSort(Class<T> c, T[] arr) {
		int leftIndex = 0;
		int rightIndex = arr.length - 1;
		mergeSort(c, arr, leftIndex, rightIndex);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge(Class<T> c, T[] arr, int li, int mi, int ri) {
		
		T[] leftHalf = (T[]) Array.newInstance(c, mi - li +  1);
		for (int i = 0; i < leftHalf.length; i++)
			leftHalf[i] = arr[i + li];
		T[] rightHalf =  (T[]) Array.newInstance(c, ri - mi);
		for (int i = 0; i < rightHalf.length; i++)
			rightHalf[i] = arr[i + mi + 1];
		
		int ai = li;
		int lhi = 0;
		int rhi = 0;
		
		while (lhi < leftHalf.length && rhi < rightHalf.length) {
			if (leftHalf[lhi].compareTo(rightHalf[rhi]) <= 0) {
				arr[ai] = leftHalf[lhi];
				lhi++;
			} else {
				arr[ai] = rightHalf[rhi];
				rhi++;
			}
			ai++;
		}
		
		while (lhi < leftHalf.length) {
			arr[ai] = leftHalf[lhi];
			lhi++;
			ai++;
		}
		
		while (rhi < rightHalf.length) {
			arr[ai] = rightHalf[rhi];
			rhi++;
			ai++;
		}
		
	}
	
	private static <T extends Comparable<? super T>> void mergeSort(Class<T> c, T[] arr, int li, int ri) {
		if (li <  ri) {
			
			int mid = li + (ri - li) / 2;
			
			mergeSort(c, arr, li, mid);
			mergeSort(c, arr, mid + 1, ri);
			
			merge(c, arr, li, mid, ri);
			
		}
	}
	
	// Heap Sort
    // O(n log n)
	public static <T extends Comparable<? super T>> void heapSort(T[] arr) {
	    int heapSize = arr.length;
	    buildMaxHeap(arr);
	    while (heapSize > 1) {
	        swap(arr, 0, --heapSize);
	        heapify(arr, 0, heapSize);
	    }
	}

    private static <T extends Comparable<? super T>> void buildMaxHeap(T[] arr) {
        for (int i = arr.length / 2; i >= 0; i--)
            heapify(arr, i, arr.length);
    }

    private static <T extends Comparable<? super T>> void heapify(T[] arr, int i, int length) {
        while (true) {
            int elementIndex = i;
            int left = getLeftIndex(i);
            int right = getRightIndex(i);
            if (left < length && arr[left].compareTo(arr[elementIndex]) > 0)
                elementIndex = left;
            if (right < length && arr[right].compareTo(arr[elementIndex]) > 0)
                elementIndex = right;
            
            if (elementIndex == i)
                break;
            swap(arr, elementIndex, i);
            i = elementIndex;
        }
    }
    
    private static int getLeftIndex(int index) {
        return 2 * index + 1;
    }
    
    private static int getRightIndex(int index) {
        return 2 * index + 2;
    }
	
	
	
	
}
