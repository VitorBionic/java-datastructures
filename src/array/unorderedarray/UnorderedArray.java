package array.unorderedarray;

// Creating class
public class UnorderedArray <T> {

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
	public UnorderedArray(int capacity) {
		this.capacity = capacity;
		this.elements = (T[]) new Object[capacity];
	}
	
	// Method get - Just to recover the values of the private instance variable elements
	public T get(int index) {
		if (index > lastPosition)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds for length " + (lastPosition + 1));
		return elements[index];
	}
	
	// Method insert
	// O(1)
	public void insert(T element) {
		if (lastPosition == capacity - 1)
			throw new IllegalStateException("Array is full");
		else {
			lastPosition++;
			elements[lastPosition] = element;
		}
	}
	
	// Method search
	// O(n)
	public int search(T value) {
		for (int i = 0; i <= lastPosition; i++) {
			if (value.equals(elements[i]))
				return i;
		}
		return -1;
	}
	
	// Method remove
	// O(n)
	public boolean remove(T value) {
		int index = search(value);
		if (index == -1)
			return false;
		for (int i = index; i < lastPosition; i++)
			elements[i] = elements[i + 1];
		elements[lastPosition] = null;
		lastPosition--;
		return true;
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
