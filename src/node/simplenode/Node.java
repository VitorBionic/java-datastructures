package node.simplenode;

// Creating class
public class Node <T> {
	
	// Instance Variables
	private T element;
	private Node<T> next;
	
	// Constructor of class
	public Node() {
		this.element = null;
		this.next = null;
	}
		
	// Constructor of class
	public Node(T element, Node<T> next) {
		this.element = element;
		this.next = next;
	}
	
	// Constructor of class
	public Node(T element) {
		this.element = element;
		this.next = null;
	}
	
	public T getElement() {
		return element;
	}
	
	public void setElement(T element) {
		this.element = element;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
}
