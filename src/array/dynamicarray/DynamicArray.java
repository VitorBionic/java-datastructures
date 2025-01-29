package array.dynamicarray;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray <T> implements Iterable <T> {
	
	private T[] elements;
	private int capacity;
	private int count;
	private final Class<T> c;
	
	@SuppressWarnings("unchecked")
	public DynamicArray(Class<T> c, int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity can't be lower than 1");
		this.capacity = capacity;
		this.c = c;
		this.elements = (T[]) Array.newInstance(c, capacity);
		this.count = 0;
	}
	
	@SuppressWarnings("unchecked")
	public DynamicArray(Class<T> c) {
		this.capacity = 11;
		this.c = c;
		this.elements = (T[]) Array.newInstance(c, capacity);
		this.count = 0;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	@SuppressWarnings("unchecked")
	private boolean resize() {
		float factor = (float) count / capacity;
		if (factor > 0.75) {
			T[] temp = elements;
			this.capacity *= 2;
			this.elements = (T[]) Array.newInstance(c, capacity);
			for (int i = 0; i < temp.length; i++) {
				if (i >= count)
					break;
				elements[i] = temp[i];
			}
			return true;
		}
		return false;
	}
	
	public T get(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds for length " + (count));
		return elements[index];
	}
	
	public int indexOf(T element) {
		for (int i = 0; i < count; i++) {
			if (elements[i] == null) {
				if (element == null)
					return i;
			}
			if (elements[i].equals(element))
				return i;
		}
		return -1;
	}
	
	public int lastIndexOf(T element) {
		for (int i = count - 1; i >= 0; i--) {
			if (elements[i].equals(element))
				return i;
		}
		return -1;
	}
	
	public void add(T element) {
		elements[count] = element;
		count++;
		resize();
	}
	
	public void add(int index, T element) {
		if (index < 0 || index > count)
			throw new IllegalArgumentException("Index " + index + " is out of range from 0 to length " + (count));
		int i = count;
		while (i > index) {
			elements[i] = elements[i - 1];
			i--;
		}
		elements[i] = element;
		
		count++;
		resize();
	}
	
	public T remove(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds for length " + (count));
		T temp = elements[index];
		while (index < count - 1) {
			elements[index] = elements[index + 1];
			index++;
		}
		elements[index] = null;
		count--;
		return temp;
	}
	
	public boolean remove(T element) {
		int index = indexOf(element);
		if (index > -1) {
			remove(index);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty())
			sb.append(elements[0]);
		for (int i = 1; i < count; i++)
			sb.append(", " + elements[i]);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}
	
	private class Itr implements Iterator<T> {
		int cursor;
		
		Itr() {}
		
		@Override
		public boolean hasNext() {
			return cursor < count;
		}
		
		@Override
		public T next() {
			int i = cursor;
            if (!hasNext())
                throw new NoSuchElementException();
            cursor = i + 1;
            return elements[i];
		}
	}
}
