package node.btnode;

public class Node <T> {
	
	private T element;
	private Node<T> right;
	private Node<T> left;
	
	public Node(T element) {
		this.element = element;
		this.right = null;
		this.left = null;
	}
	
	public T getElement() {
		return element;
	}
	
	public void setElement(T element) {
		this.element = element;
	}
	
	public Node<T> getRight() {
		return right;
	}
	
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	
	public void setLeft(Node<T> left) {
		this.left = left;
	}
}
