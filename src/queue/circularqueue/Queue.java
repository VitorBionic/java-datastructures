package queue.circularqueue;

import java.lang.reflect.Array;

// Creating class
public class Queue <T> {

	// Instance Variables
	private int capacity;
	private int start;
	private int end;
	private int elementsQty;
	private T[] elements;
	
	// Instance block(or block)
	{
		start = 0; // Executed when a object is instantiated in scope inside it, accessing its own instance variables
		end = -1;
		elementsQty = 0;
	}
	
	// Constructor of class
	@SuppressWarnings("unchecked")
	public Queue(Class<T> c, int capacity) {
		this.capacity = capacity;
		elements = (T[]) Array.newInstance(c, capacity);
	}
	
	public boolean isFull() {
		if (elementsQty == capacity)
			return true;
		else
			return false;
					
	}
	
	public boolean isEmpty() {
		if (elementsQty == 0)
			return true;
		else
			return false;
	}
	
	// Method enqueue
	// O(1)
	public void enqueue(T value) {
		if (isFull())
			throw new ArrayIndexOutOfBoundsException("Stack is full");
		else {
			if (end == capacity - 1)
				end = -1;
			end++;
			elements[end] = value;
			elementsQty++;
		}
	}
	
	// Method dequeue
    // O(1)
	public T dequeue() {
		if (isEmpty())
			throw new NullPointerException("Stack is empty");
		else {
			T first = elements[start];
			
			elements[start] = null;
			start++;
			if (start == capacity)
				start = 0;
			
			elementsQty--;
			return first;
		}
	}
	
	// Method peek
	// O(1)
	public T peek() {
		if (isEmpty())
			throw new NullPointerException("Stack is empty");
		else
			return elements[start];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty()) {
			sb.append(elements[start]);
			if (start <= end) {
				for (int i = start + 1; i <= end; i++)
					sb.append(", " + elements[i]);
			} else {
				for (int i = start + 1; i < capacity; i++)
					sb.append(", " + elements[i]);
				for (int i = 0; i <= end; i++) {
					sb.append(", " + elements[i]);
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	// Learning purpose method to see all values in array elements, including the elements not controlled by the lastPosition variable
	public String checkMemory() {
		StringBuilder sb = new StringBuilder("[");
		sb.append(elements[0]);
		for (int i = 1; i < capacity; i++)
			sb.append(", " + elements[i]);
		sb.append("]");
		return sb.toString();
	}
}
