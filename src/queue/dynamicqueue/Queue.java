package queue.dynamicqueue;

import node.simplenode.Node;

// Creating the class
public class Queue<T> {

	// Instance Variables
	private Node<T> head;
	private Node<T> tail;
	private int size;

	// Constructor of class
	public Queue() {
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

	// Method enqueue
	// O(1)
	public void enqueue(T value) {
		Node<T> newNode = new Node<>(value);
		if (isEmpty()) {
			tail = newNode;
			head = tail;
		}
		else {
		    tail.setNext(newNode);
		    tail = newNode;
		}
		size++;
	}

	// Method dequeue
	// O(1)
	public T dequeue() {
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
