package linkedlist.circularlinkedlist;

import node.simplenode.Node;

public class LinkedList <T> {
	
	private Node<T> cursor;
	private int size;
	
	public LinkedList() {
		cursor = null;
		size = 0;
	}
	
	public T getCursor() {
		return cursor.getElement();
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void advance() {
		cursor =  cursor.getNext();
	}
	
	// Method add
	// O(1)
	public void add(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			cursor = newNode;
			newNode.setNext(newNode);
		} else {
			newNode.setNext(cursor.getNext());
			cursor.setNext(newNode);
		}
		size++;
	}
	
	// Method remove
	// O(1)
	public T remove() {
		if (isEmpty())
			throw new IllegalStateException("List is empty");
		Node<T> removed = cursor.getNext();
		
		if (cursor == removed)
			cursor = null;
		else {
			cursor.setNext(removed.getNext());
			removed.setNext(null);
		}
		size--;
		return removed.getElement();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[...");
		if (!isEmpty()) {
			sb.append(cursor.getElement());
			Node<T> temp = cursor;
			for (advance(); cursor != temp ; advance())
				sb.append(", " + cursor.getElement());
		}
		sb.append("...]");
		return sb.toString();
	}
}
