package stack.dynamicstack;

import node.simplenode.Node;

// Creating the class
public class Stack<T> {

	// Instance Variables
	private Node<T> head;
	private Node<T> tail;
	private int size;

	// Constructor of class
	public Stack() {
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

	// Method push
	// O(1)
	public void push(T value) {
		Node<T> newNode = new Node<>(value);
		if (isEmpty()) {
			head = newNode;
			tail = head;
		} else {
		    newNode.setNext(head);
		    head = newNode;
		}
		size++;
	}

	// Method pop
	// O(1)
	public T pop() {
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

	// Method peek
	// O(1)
	public T peek() {
		if (isEmpty())
			return null;
		return head.getElement();
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
