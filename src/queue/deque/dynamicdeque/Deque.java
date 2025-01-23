package queue.deque.dynamicdeque;

import node.doublynode.Node;
import java.util.NoSuchElementException;

// Double-ended queue (Deque)

// Creating class
public class Deque <T> {
	// Instance Variables
	private Node<T> head;
	private Node<T> tail;
	private int size;

	// Constructor of class
	public Deque() {
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
	public void addFirst(T value) {
		Node<T> newNode = new Node<>(value);
		if (isEmpty())
		    tail = newNode;
		else
		    newNode.setNext(head);
		head = newNode;
		size++;
	}

	// Method addLast
	// O(1)
	public void addLast(T value) {
	    Node<T> newNode = new Node<>(value);
        if (isEmpty())
            head = newNode;
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
        size++;
	}

	// Method removeFirst
	// O(1)
	public T removeFirst() {
		if (isEmpty())
			return null;
		else {
			Node<T> temp = head;
			if (isThereJustOne()) {
			    head = null;
			    tail = null;
			} else {
			    head = head.getNext();
			    head.setPrev(null);
			    temp.setNext(null);
			}
			size--;
			return temp.getElement();
		}
	}
	
	// Method removeLast
	// O(1)
	public T removeLast() {
	    if (isEmpty())
            return null;
        Node<T> temp = tail;
        if (isThereJustOne()) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
            temp.setPrev(null);
        }
        size--;
        return temp.getElement();
	}

	// Method getFirst
	// O(1)
	public T getFirst() {
		if (isEmpty())
		    return null;
		else
			return head.getElement();
	}
	
	// Method getLast
	// O(1)
	public T getLast() {
		if (isEmpty())
		    return null;
		else
			return tail.getElement();
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
