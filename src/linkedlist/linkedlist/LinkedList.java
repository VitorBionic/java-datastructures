package linkedlist.linkedlist;

import node.simplenode.Node;

// Creating class
public class LinkedList <T> {
	
	// Instance Variables
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	// Constructor of class
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return head == null || tail == null;
	}
	
	public boolean isThereJustOne()  {
		return head == tail && !isEmpty();
	}
	
	public int getSize() {
		return size;
	}
	
	// Method addFirst
	// O(1)
	public void addFirst(T value) {
		Node<T> newNode = new Node<>(value);
		newNode.setNext(head);
		head = newNode;
		if (isEmpty())
			tail = head;
		size++;
	}
	
	// Method addLast
	// O(1)
	public void addLast(T value) {
		Node<T> newNode = new Node<>(value);
		tail.setNext(newNode);
		tail = newNode;
		if (isEmpty())
			head = tail;
		size++;
	}
	
	// Method removeFirst
	// O(1)
	public T removeFirst() {
		if (isEmpty())
			return null;
		
		Node<T> temp = head;
		if (isThereJustOne()) {
			head = null;
			tail = null;
			size--;
			return temp.getElement();
		}
		
		head = temp.getNext();
		temp.setNext(null);
		size--;
		return temp.getElement();
	}
	
	// Method removeLast
	// O(n)
	public T removeLast() {
		if (isEmpty())
			return null;
		
		if (isThereJustOne()) {
			Node<T> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.getElement();
		}
		
		Node<T> current = head;
		Node<T> prev = head;
		while (current.getNext() != null)  {
			prev = current;
			current = current.getNext();
		}
			
		prev.setNext(null);
		tail = prev;
		size--;
		return current.getElement();
		
	}
	
	// Method peekFirst
	// O(1)
	public T peekFirst() {
		if (isEmpty())
			return null;
		return head.getElement();
	}
	
	// Method peekLast
	// O(1)
	public T peekLast() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}
	
	// Method get
	// O(n)
	public T get(int index) {
		if (isEmpty())
			return null;
		if (index < 0)
			throw new IndexOutOfBoundsException("Index " + index + " is lower than 0, which is the minimun index");
		Node<T> current = head;
		while (index > 0) {
			if (current.getNext() == null)
				return null;
			current = current.getNext();
			index--;
		}
		return current.getElement();
	}
	
	// Method indexOf
	// O(n)
	public int indexOf(T element) {
		Node<T> current = head;
		int index = 0;
		while (current != null && !current.getElement().equals(element)) {
			current = current.getNext();
			index++;
		}
		
		if (current == null)
			return -1;
		
		return index;
	}
	
	// Method add
	// O(n)
	public void add(int index, T value) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds between 0 and " + size);
		
		if (index == 0) {
			addFirst(value);
			return;
		}
		
		if (index == size) {
			addLast(value);
			return;
		}
		
		Node<T> newNode = new Node<>(value);
		Node<T> current = head;
		Node<T> prev = head;
		
		while (index > 0) {
			prev = current;
			current = current.getNext();
			index--;
		}
		newNode.setNext(current);
		prev.setNext(newNode);
		if (isEmpty())
			tail = head;
		size++;
	}
	
	// Method remove
	// O(n)
	public T remove(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds between 0 and " + (size - 1));
		
		if (index == 0) {
			T removed = removeFirst();
			return removed;
		}
		
		if (index == size - 1) {
			T removed = removeLast();
			return removed;
		}
		
		Node<T> current = head;
		Node<T> prev = head;
		
		while (index > 0) {
			prev = current;
			current = current.getNext();
			index--;
		}
		prev.setNext(current.getNext());
		current.setNext(null);
		size--;
		return current.getElement();
	}
	
    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder("[");
    	if (!isEmpty()) {
    		Node<T> current = head;
    		sb.append(head.getElement());
    		
    		while (current.getNext() != null) {
    			current = current.getNext();
    			sb.append(", " + current.getElement());
    		}		
    	}
    	sb.append("]");
    	return sb.toString();
    }
	
}
