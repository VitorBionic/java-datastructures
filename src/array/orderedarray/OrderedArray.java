package array.orderedarray;

import java.lang.reflect.Array;

// Creating class
public class OrderedArray <T extends Comparable<T>> {

	// Instance Variables
	private int capacity;
	private int lastPosition;
	private T[] elements;
	
	// Instance block(or block)
	{
		lastPosition = -1; // Executed when a object is instantiated in scope inside it, accessing its own instance variables
	}
	
	// Constructor of class
	@SuppressWarnings("unchecked")
	public OrderedArray(Class<T> c, int capacity) {
		this.capacity = capacity;
		elements = (T[]) Array.newInstance(c, capacity);
	}
	
	// Method get - Just to recover the values of the private instance variable elements
	public T get(int index) {
		if (index > lastPosition)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds for length " + (lastPosition + 1));
		return elements[index];
	}
	
	// Method insert
	// O(n)
	public void insert(T value) {
		if (lastPosition == capacity - 1)
			throw new ArrayIndexOutOfBoundsException("Array is Full");
		int position = 0;
		for (int i = 0; i < lastPosition + 1; i++) {
			position = i;
			if (elements[position].compareTo(value) > 0)
				break;
			if (position == lastPosition)
				position++;
		}
		int x = lastPosition;
		while (x >= position) {
			elements[x + 1] = elements[x];
			x--;
		}
		
		elements[position] = value;
		lastPosition++;
	}
	
	// Method Linear Search
	// O(n)
	public int linearSearch(T value) {
		for (int i = 0; i <= lastPosition; i++) {
			if (value.compareTo(elements[i]) < 0)
				return -1;
			if (value.equals(elements[i]))
			    return i;
		}
		return -1;
	}
	
	// Method Binary Search
	// O(log n)
	public int binarySearch(T value) {
		int start = 0;
		int end = lastPosition;
		while (true) {
			int mid = ((start + end) / 2);
			if (value.compareTo(elements[mid]) > 0)
				start = mid + 1;
			else if (value.compareTo(elements[mid]) < 0)
				end = mid - 1;
			else
				return mid;
			if (start > end)
				return -1;
		}
	}
	
	// Method remove
	// O(n)
	public void remove(T value) {
		int index = binarySearch(value);
		if (index == -1)
			throw new ArrayIndexOutOfBoundsException("Value doesn't exist in the array");
		for (int i = index; i < lastPosition; i++)
			elements[i] = elements[i + 1];
		elements[lastPosition] = null;
		lastPosition--;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (lastPosition > -1)
		    sb.append(elements[0]);
		for (int i = 1; i <= lastPosition; i++)
			sb.append(", " + elements[i]);
		sb.append("]");
		return sb.toString();			
	}
	
	// Learning purpose method to see all values in array elements, including the elements not controlled by the lastPosition variable
	public String checkMemory() {
		StringBuilder sb = new StringBuilder("[");
		if (lastPosition > -1)
		    sb.append(elements[0]);
		for (int i = 1; i < capacity; i++)
			sb.append(", " + elements[i]);
		sb.append("]");
		return sb.toString();
	}
}
