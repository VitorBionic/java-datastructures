package queue.queue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

// Creating class
public class Queue<T> {

	// Instance Variables
	private int capacity;
	private int end;
	private T[] elements;

	// Instance block(or block)
	{
		end = -1; // Executed when a object is instantiated in scope inside it, accessing its own instance variables
	}

	// Constructor of class
	@SuppressWarnings("unchecked")
	public Queue(Class<T> c, int capacity) {
		this.capacity = capacity;
		elements = (T[]) Array.newInstance(c, capacity);
	}

	public boolean isFull() {
		if (end == capacity - 1)
			return true;
		else
			return false;
	}

	public boolean isEmpty() {
		if (end == -1)
			return true;
		else
			return false;
	}

	// Method enqueue
	// O(1)
	public void enqueue(T value) {
		if (isFull())
			throw new IllegalStateException("Stack is full");
		else {
			end++;
			elements[end] = value;
		}
	}

	// Method dequeue
	// O(n)
	public T dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Stack is empty");
		else {
			T first = elements[0];
			for (int i = 0; i < end; i++)
				elements[i] = elements[i + 1];
			elements[end] = null;
			end--;
			return first;
		}
	}

	// Method peek
	// O(1)
	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException("Stack is empty");
		else
			return elements[0];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty()) {
			sb.append(elements[0]);
			for (int i = 1; i <= end; i++)
				sb.append(", " + elements[i]);
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
