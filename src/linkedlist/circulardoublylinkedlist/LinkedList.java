package linkedlist.circulardoublylinkedlist;

import node.doublynode.Node;

public class LinkedList<T> {

	private Node<T> cursor;
	private int size;

	public LinkedList() {
		cursor = null;
		size = 0;
	}

	public T getCursor() {
		return cursor.getElement();
	}
	
	public Node<T> getCursorNode() {
		return cursor;
	}
	
	public void setCursorNode(Node<T> cursor) {
		this.cursor = cursor;
	}

	public int getSize() {
		return size;
	}

	public void advanceForward() {
		cursor = cursor.getNext();
	}

	public void advanceBack() {
		cursor = cursor.getPrev();
	}

	public boolean isEmpty() {
		return cursor == null || size == 0;
	}

	// Method addAfter
	// O(1)
	public void addAfter(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			cursor = newNode;
			cursor.setPrev(cursor);
			cursor.setNext(cursor);
		} else {
			Node<T> oldNext = cursor.getNext();
			cursor.setNext(newNode);
			newNode.setPrev(cursor);
			newNode.setNext(oldNext);
			oldNext.setPrev(newNode);
		}
		size++;
	}

	// Method addBefore
	// O(1)
	public void addBefore(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			cursor = newNode;
			cursor.setPrev(cursor);
			cursor.setNext(cursor);
		} else {
			Node<T> oldPrev = cursor.getPrev();
			cursor.setPrev(newNode);
			newNode.setNext(cursor);
			newNode.setPrev(oldPrev);
			oldPrev.setNext(newNode);
		}
		size++;
	}

	// Method removeAfter
	// O(1)
	public T removeAfter() {
		if (isEmpty())
			throw new IllegalStateException("List is Empty");
		Node<T> removed;
		if (size == 1) {
			removed = cursor;
			cursor = null;
		} else {
			removed = cursor.getNext();
			cursor.setNext(removed.getNext());
			removed.getNext().setPrev(cursor);
		}
		removed.setPrev(null);
		removed.setNext(null);
		size--;
		return removed.getElement();
	}

	// Method removeBefore
	// O(1)
	public T removeBefore() {
		if (isEmpty())
			throw new IllegalStateException("List is Empty");
		Node<T> removed;
		if (size == 1) {
			removed = cursor;
			cursor = null;
		} else {
			removed = cursor.getPrev();
			cursor.setPrev(removed.getPrev());
			removed.getPrev().setNext(cursor);
		}
		removed.setPrev(null);
		removed.setNext(null);
		size--;
		return removed.getElement();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[...");
		if (!isEmpty()) {
			Node<T> temp = cursor;
			sb.append(cursor.getElement());
			for (advanceForward(); cursor != temp; advanceForward())
				sb.append(", " + cursor.getElement());
		}
		sb.append("...]");
		return sb.toString();
	}
}
