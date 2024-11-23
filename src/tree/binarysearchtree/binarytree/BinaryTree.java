package tree.binarysearchtree.binarytree;

import java.util.function.Consumer;

import node.btnode.Node;

public class BinaryTree <T extends Comparable<T>> {
	
	private Node<T> root;
	
	public BinaryTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	// Method add
	// O(log n)
	public void add(T value) {
		Node<T> newNode = new Node<>(value);
		if (isEmpty())
			root = newNode;
		else {
			Node<T> current = root;
			Node<T> parent = root;
			while (current != null) {
				parent = current;
				if (current.getElement().compareTo(value) > 0)
					current = current.getLeft();
				else
					current = current.getRight();
			}
			
			if (parent.getElement().compareTo(value) > 0)
				parent.setLeft(newNode);
			else
				parent.setRight(newNode);
		}
	}
	
	// Method remove
	// O(log n)
	public T remove(T value) {
		if (isEmpty())
			throw new IllegalStateException("The binary tree is empty");
		Node<T> current = root;
		Node<T> parent = root;
		while (current != null) {
			if (current.getElement().compareTo(value) > 0) {
				parent = current;
				current = current.getLeft();
			} else if (current.getElement().compareTo(value) < 0) {
				parent = current;
				current = current.getRight();
			} else {
				if (current.getLeft() == null && current.getRight() == null) {
					if (current == root)
						root = null;
					else if (parent.getLeft() == current)
						parent.setLeft(null);
					else
						parent.setRight(null);
				} else if (current.getLeft() == null) {
					if (current == root)
						root = current.getRight();
					else if (parent.getLeft() == current)
					    parent.setLeft(current.getRight());
					else
						parent.setRight(current.getRight());
					
				} else if (current.getRight() == null) {
					if (current == root)
						root = current.getLeft();
					else if (parent.getLeft() == current)
						parent.setLeft(current.getLeft());
					else
						parent.setRight(current.getLeft());
				} else {
					Node<T> newCurrent = current.getLeft();
					Node<T> newCurrentParent = current.getLeft();
					while (newCurrent.getRight() != null) {
						newCurrentParent = newCurrent;
						newCurrent = newCurrent.getRight();
					}
					
					if (newCurrent == newCurrentParent) {
						newCurrent.setRight(current.getRight());
					} else {
						newCurrentParent.setRight(newCurrent.getLeft());
						newCurrent.setLeft(current.getLeft());
						newCurrent.setRight(current.getRight());
					}
					
					if (current == root)
						root = newCurrent;
					else if (parent.getLeft() == current)
						parent.setLeft(newCurrent);
					else
						parent.setRight(newCurrent);
				}
				return current.getElement();	
			}
		}
		return null;
	}
	
	// Method search
	// O(log n)
	public boolean search(T value) {
		if (isEmpty())
			return false;
		Node<T> current = root;
		while (current != null) {
			if (value.equals(current.getElement()))
				return true;
			else if (value.compareTo(current.getElement()) < 0)
				current = current.getLeft();
			else if (value.compareTo(current.getElement()) > 0)
				current = current.getRight();
		}
		return false;
	}
	
	public void inOrderTraversal(Consumer<Node<T>> consumer) {
		inOrderTraversal(root, consumer);
	}
	
	private void inOrderTraversal(Node<T> node, Consumer<Node<T>> consumer) {
		if (node != null) {
			inOrderTraversal(node.getLeft(), consumer);
			consumer.accept(node);
			inOrderTraversal(node.getRight(), consumer);
		}
	}
	
	public void preOrderTraversal(Consumer<Node<T>> consumer) {
		preOrderTraversal(root, consumer);
	}
	
	private void preOrderTraversal(Node<T> node, Consumer<Node<T>> consumer) {
		if (node != null) {
			consumer.accept(node);
			preOrderTraversal(node.getLeft(), consumer);
			preOrderTraversal(node.getRight(), consumer);
		}
	}
	
	public void posOrderTraversal(Consumer<Node<T>> consumer) {
		posOrderTraversal(root, consumer);
	}
	
	private void posOrderTraversal(Node<T> node, Consumer<Node<T>> consumer) {
		if (node != null) {
			posOrderTraversal(node.getLeft(), consumer);
			posOrderTraversal(node.getRight(), consumer);
			consumer.accept(node);
		}
	}
	
	public void levelOrderTraversal(Consumer<Node<T>> consumer) {
		if (!isEmpty()) {
			
			Node<T> current = root;
			queue.dynamicqueue.Queue<Node<T>> queue = new queue.dynamicqueue.Queue<Node<T>>();
			queue.enqueue(root);
			
			while (!queue.isEmpty()) {
				current = queue.dequeue();
				consumer.accept(current);
				
				if (current.getLeft() != null)
					queue.enqueue(current.getLeft());
				if (current.getRight() != null)
					queue.enqueue(current.getRight());
			}
		}
	}
	
	public void inOrderTraversalIT(Consumer<Node<T>> consumer) {
		if (isEmpty())
			return;
		if (root.getLeft() == null && root.getRight() == null) {
			consumer.accept(root);
			return;
		}
		stack.dynamicstack.Stack<Node<T>> stack = new stack.dynamicstack.Stack<>();
		stack.push(root);
		
		Node<T> current = root.getLeft();
		if (current == null)
			current =  root.getRight();
		Node<T> prev = root;
		
		Node<T> preEnd = root.getRight();
		if (root.getRight() == null)
			preEnd = root.getLeft();
		while (current != root || prev != preEnd) {
			while (current.getLeft() != null && prev != current.getLeft() && prev != current.getRight()) {
				stack.push(current);
				prev = current;
				current = current.getLeft();
			}
			if (prev.getLeft() == current || prev.getRight() == current)
				stack.push(current);
			
			if (prev != current.getRight()) {
				consumer.accept(current);
				if (current.getRight() != null) {
					prev = current;
					current = current.getRight();
				} else {
					prev = stack.pop();
					current = stack.peek();
				}
			} else {
				prev = stack.pop();
				current = stack.peek();
			}		
		}
		if (root.getLeft() == null || root.getRight() == null)
		    consumer.accept(root);
	}
	
	public void preOrderTraversalIT(Consumer<Node<T>> consumer) {
		if (root == null)
			return;
		
		consumer.accept(root);
		if (root.getLeft() == null && root.getRight() == null) {
			return;
		}
		stack.dynamicstack.Stack<Node<T>> stack = new stack.dynamicstack.Stack<>();
		stack.push(root);
		
		Node<T> current = root.getLeft();
		if (current == null)
			current =  root.getRight();
		Node<T> prev = root;
		
		Node<T> preEnd = root.getRight();
		if (root.getRight() == null)
			preEnd = root.getLeft();
		while (current != root || prev != preEnd) {
			while (current.getLeft() != null && prev != current.getLeft() && prev != current.getRight()) {
				stack.push(current);
				consumer.accept(current);
				prev = current;
				current = current.getLeft();
			}
			if (prev.getLeft() == current || prev.getRight() == current) {
				stack.push(current);
			    consumer.accept(current);
			}
			
			if (prev != current.getRight()) {
				if (current.getRight() != null) {
					prev = current;
					current = current.getRight();
				} else {
					prev = stack.pop();
					current = stack.peek();
				}
			} else {
				prev = stack.pop();
				current = stack.peek();
			}
			
		}
	}
	
	public void posOrderTraversalIT(Consumer<Node<T>> consumer) {
		if (root == null)
			return;
		if (root.getLeft() == null && root.getRight() == null) {
			consumer.accept(root);
			return;
		}
		stack.dynamicstack.Stack<Node<T>> stack = new stack.dynamicstack.Stack<>();
		stack.push(root);
		
		Node<T> current = root.getLeft();
		if (current == null)
			current =  root.getRight();
		Node<T> prev = root;
		
		Node<T> preEnd = root.getRight();
		if (root.getRight() == null)
			preEnd = root.getLeft();
		while (current != root || prev != preEnd) {
			while (current.getLeft() != null && prev != current.getLeft() && prev != current.getRight()) {
				stack.push(current);
				prev = current;
				current = current.getLeft();
			}
			if (prev.getLeft() == current || prev.getRight() == current)
				stack.push(current);
			
			if (prev != current.getRight()) {
				if (current.getRight() != null) {
					prev = current;
					current = current.getRight();
				} else {
					consumer.accept(current);
					prev = stack.pop();
					current = stack.peek();
				}
			} else {
				consumer.accept(current);
				prev = stack.pop();
				current = stack.peek();
			}		
		}
		consumer.accept(root);
	}
	
	
}
