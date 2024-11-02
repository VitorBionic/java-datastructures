package stack;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

// Creating class
public class Stack <T> {
	
	// Instance Variables
	private int capacity;
	private int top;
	private T[] elements;
	
	// Instance block(or block)
	{
		top = -1; // Executed when a object is instantiated in scope inside it, accessing its own instance variables
	}
	
	// Constructor of class
	@SuppressWarnings("unchecked")
	public Stack(Class<T> c, int capacity) {
		this.capacity = capacity;
		elements = (T[]) Array.newInstance(c, capacity);
	}
	
	public boolean isFull() {
		if (top == capacity - 1)
			return true;
		else
			return false;
	}
	
	public boolean isEmpty() {
		if (top == -1)
			return true;
		else
			return false;
	}
	
    // Method push
    // O(1)
	public void push(T value) {
		if (isFull())
			throw new IllegalStateException("Stack is full");
		else {
			top++;
			elements[top] = value;
		}
	}
	
	// Method peek
    // O(1)
	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException("Stack is empty");
		else
			return elements[top];			
	}
	
	// Method pop
    // O(1)
	public T pop() {
		T aux = peek();
		elements[top] = null;
		top--;
		return aux;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty()) {
			sb.append(elements[0]);
		    for (int i = 1; i <= top; i++)
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
