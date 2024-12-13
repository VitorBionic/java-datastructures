package node.avlnode;

public class Node <T> {
	
	private T element;
	private Node<T> left;
	private Node<T> right;
	private int height;
	
	public Node(T element) {
		this.element = element;
		this.left = null;
		this.right = null;
		this.height = 1;
	}
	
	public T getElement() {
		return element;
	}
	
	public void setElement(T element) {
		this.element = element;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	
	public Node<T> getRight() {
		return right;
	}
	
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return "" + element;
	}
}
