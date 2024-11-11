package linkedlist.doublylinkedlist;

import node.doublynode.Node;

// Creating class
public class LinkedList<T> {

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

	public boolean isThereJustOne() {
		return head == tail && !isEmpty();
	}
	
	public int getSize() {
		return size;
	}

	// Method addFirst
	// O(1)
	public void addFirst(T element) {
		Node<T> newNode = new Node<>(element);
		newNode.setNext(head);
		if (isEmpty()) {
			head = newNode;
			tail = head;
		} else {
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}

	// Method addFirst
	// O(1)
	public void addLast(T element) {
		Node<T> newNode = new Node<>(element);
		if (isEmpty()) {
			tail = newNode;
			head = tail;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
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
			tail = head;
			size--;
			return temp.getElement();
		}

		head = head.getNext();
		head.setPrev(null);
		temp.setNext(null);
		size--;
		return temp.getElement();
	}

	// Method removeLast
	// O(1)
	public T removeLast() {
		if (isEmpty())
			return null;

		Node<T> temp = tail;
		if (isThereJustOne()) {
			tail = null;
			head = tail;
			size--;
			return temp.getElement();
		}

		Node<T> prev = tail.getPrev();
		prev.setNext(null);
		tail = prev;
		temp.setPrev(null);
		size--;
		return temp.getElement();
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
	// O(n/2)
	public T get(int index) {
		if (isEmpty())
			return null;
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds between 0 and " + (size - 1));
		if (index <= size / 2) {
			Node<T> current = head;
			while (index > 0) {
				current = current.getNext();
				index--;
			}
			return current.getElement();
		} else {
			Node<T> current = tail;
			while (index < size - 1) {
				current = current.getPrev();
				index++;
			}
			return current.getElement();
			
		}
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
	
	// Method lasIndexOf
	// O(n)
	public int lastIndexOf(T element) {
		Node<T> current = tail;
		int index = size - 1;
		while (current != null && !current.getElement().equals(element)) {
			current = current.getPrev();
			index--;
		}
		
		if (current == null)
			return -1;
		return index;	
	}
	
	// Method add
	// O(n/2)
	public void add(int index, T element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds between 0 and " + size);
		
		if (index == 0)
			addFirst(element);
		else if (index == size)
			addLast(element);
		else if (index <= size / 2) {
			Node<T> current = head;
			while (index > 0) {
				current = current.getNext();
				index--;
			}
			Node<T> prev = current.getPrev();
			Node<T> newNode = new Node<>(element);
			newNode.setNext(current);
			current.setPrev(newNode);
			prev.setNext(newNode);
			newNode.setPrev(prev);
			size++;
		} else {
			Node<T> current = tail;
			while (index < size - 1) {
				current = current.getPrev();
				index++;
			}
			Node<T> prev = current.getPrev();
			Node<T> newNode = new Node<>(element);
			newNode.setNext(current);
			current.setPrev(newNode);
			prev.setNext(newNode);
			newNode.setPrev(prev);
			size++;
		}
	}
	
	// Method remove
	// O(n/2)
	public T remove(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds between 0 and " + (size - 1));
		
		if (isEmpty())
			return null;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else if (index <= size / 2) {
			Node<T> current = head;
			while (index > 0) {
				current = current.getNext();
				index--;
			}
			Node<T> prev = current.getPrev();
			Node<T> next = current.getNext();
			prev.setNext(next);
			next.setPrev(prev);
			current.setNext(null);
			current.setPrev(null);
			size--;
			return current.getElement();
		} else {
			Node<T> current = tail;
			while (index < size - 1) {
				current = current.getPrev();
				index++;
			}
			Node<T> prev = current.getPrev();
			Node<T> next = current.getNext();
			prev.setNext(next);
			next.setPrev(prev);
			current.setNext(null);
			current.setPrev(null);
			size--;
			return current.getElement();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty()) {
			sb.append(head.getElement());
			Node<T> current = head;
			while (current.getNext() != null) {
				current = current.getNext();
				sb.append(", " + current.getElement());
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	// Method to implement sort
	private void remove(Node<T> node) {
		if (isEmpty())
			return;
		if (isThereJustOne()) {
			head = null;
			tail = head;
		} else {
			Node<T> current = head;
			while (current != node && current != null)
				current = current.getNext();
			if (current != null) {
				if (current.getPrev() == null) {
					head = head.getNext();
					head.setPrev(null);
					current.setNext(null);
				} else if (current.getNext() == null) {
					Node<T> prev = tail.getPrev();
					prev.setNext(null);
					current.setPrev(null);
					tail = prev;
				}  else {
					Node<T> prev = current.getPrev();
					Node<T> next = current.getNext();
					prev.setNext(next);
					next.setPrev(prev);
					current.setPrev(null);
					current.setNext(null);
				}
			} else
				return;
		}
		size--;
	}
	
	// Method to implement sort
	private void addAfter(Node<T> current, Node<T> after)  {
		Node<T> next = current.getNext();
		after.setPrev(current);
		after.setNext(next);
		next.setPrev(after);
		current.setNext(after);
		size++;
	}
	
	// Method to implement sort
	private void addFirst(Node<T> newNode) {
		newNode.setNext(head);
		if (isEmpty()) {
			head = newNode;
			tail = head;
		} else {
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}
	
	// Method sort (Insertion Sort)
	// O(n^2)
	public static <T extends Comparable<T>> void sort(LinkedList<T> ll) {
		if (ll.getSize() <= 1)
			return;
		Node<T> current;
		Node<T> ins;
		Node<T> end = ll.head;
		while (end != ll.tail) {
			current = end.getNext();
			ll.remove(current);
			ins = end;
			boolean beforeHead = false;
			while (((ins.getPrev() != null || ins == ll.head) && !beforeHead) &&
					ins.getElement().compareTo(current.getElement()) > 0) {
				if (ins == ll.head)
					beforeHead = true;
				else
					ins = ins.getPrev();
			}
			if (beforeHead)
				ll.addFirst(current);
			else
				ll.addAfter(ins, current);
			if (ins == end)
				end = end.getNext();
		}
	}
}
