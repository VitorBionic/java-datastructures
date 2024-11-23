package tree.binarysearchtree.avltree;

import java.util.function.Consumer;

import node.avlnode.Node;
import stack.dynamicstack.Stack;

public class AVLTree<T extends Comparable<T>> {

	private Node<T> root;

	public AVLTree() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	private void leftRotation(Node<T> current, Node<T> parent) {
		Node<T> child = current.getRight();
		if (parent == null)
			root = child;
		else if (parent.getLeft() == current)
			parent.setLeft(child);
		else
			parent.setRight(child);
		current.setRight(child.getLeft());
		child.setLeft(current);
		
		int hl = current.getLeft() != null ? current.getLeft().getHeight() : 0;
		int hr = current.getRight() != null ? current.getRight().getHeight() : 0;
		if (hl > hr)
			current.setHeight(hl + 1);
		else
			current.setHeight(hr + 1);
	}

	private void rightRotation(Node<T> current, Node<T> parent) {
		Node<T> child = current.getLeft();
		if (parent == null)
			root = child;
		else if (parent.getLeft() == current)
			parent.setLeft(child);
		else
			parent.setRight(child);
		current.setLeft(child.getRight());
		child.setRight(current);
		
		int hl = current.getLeft() != null ? current.getLeft().getHeight() : 0;
		int hr = current.getRight() != null ? current.getRight().getHeight() : 0;
		if (hl > hr)
			current.setHeight(hl + 1);
		else
			current.setHeight(hr + 1);
	}

	private void leftRightRotation(Node<T> current, Node<T> parent) {
		Node<T> child = current.getLeft();
		Node<T> newChild = child.getRight();
		current.setLeft(newChild);
		child.setRight(newChild.getLeft());
		newChild.setLeft(child);
		
		int temp = child.getHeight();
		child.setHeight(newChild.getHeight());
		newChild.setHeight(temp);

		if (parent == null)
			root = newChild;
		else if (parent.getLeft() == current)
			parent.setLeft(newChild);
		else
			parent.setRight(newChild);
		current.setLeft(newChild.getRight());
		newChild.setRight(current);
		
		int hl = current.getLeft() != null ? current.getLeft().getHeight() : 0;
		int hr = current.getRight() != null ? current.getRight().getHeight() : 0;
		if (hl > hr)
			current.setHeight(hl + 1);
		else
			current.setHeight(hr + 1);

	}

	private void rightLeftRotation(Node<T> current, Node<T> parent) {
		Node<T> child = current.getRight();
		Node<T> newChild = child.getLeft();
		current.setRight(newChild);
		child.setLeft(newChild.getRight());
		newChild.setRight(child);
		
		int temp = child.getHeight();
		child.setHeight(newChild.getHeight());
		newChild.setHeight(temp);
		
		if (parent == null)
			root = newChild;
		else if (parent.getLeft() == current)
			parent.setLeft(newChild);
		else
			parent.setRight(newChild);
		current.setRight(newChild.getLeft());
		newChild.setLeft(current);
		
		int hl = current.getLeft() != null ? current.getLeft().getHeight() : 0;
		int hr = current.getRight() != null ? current.getRight().getHeight() : 0;
		if (hl > hr)
			current.setHeight(hl + 1);
		else
			current.setHeight(hr + 1);
	}

	// Method add
	// O(log n)
	public void add(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty())
			root = newNode;
		else {
			Stack<Node<T>> stack = new Stack<>();
			Node<T> current = root;
			while (current != null) {
				stack.push(current);
				if (current.getElement().compareTo(element) > 0)
					current = current.getLeft();
				else
					current = current.getRight();
			}

			Node<T> parent = stack.pop();
			if (parent.getElement().compareTo(element) > 0) {
				parent.setLeft(newNode);
				current = parent.getLeft();
			} else {
				parent.setRight(newNode);
				current = parent.getRight();
			}

			while (current != root) {
				current = parent;
				if (!stack.isEmpty())
					parent = stack.pop();
				else
					parent = null;

				int hl = current.getLeft() != null ? current.getLeft().getHeight() : 0;
				int hr = current.getRight() != null ? current.getRight().getHeight() : 0;
				
				if (hl > hr)
					current.setHeight(hl + 1);
				else
					current.setHeight(hr + 1);
				
				int balanceFactor = hl - hr;
				if (balanceFactor < -1 || balanceFactor > 1) {
					Node<T> child;
					if (balanceFactor < -1) {
						child = current.getRight();
						hl = child.getLeft() != null ? child.getLeft().getHeight() : 0;
						hr = child.getRight() != null ? child.getRight().getHeight() : 0;
						balanceFactor = hl - hr;
						if (balanceFactor < 0)
							leftRotation(current, parent);
						else
							rightLeftRotation(current, parent);
					} else {
						child = current.getLeft();
						hl = child.getLeft() != null ? child.getLeft().getHeight() : 0;
						hr = child.getRight() != null ? child.getRight().getHeight() : 0;
						balanceFactor = hl - hr;
						if (balanceFactor > 0)
							rightRotation(current, parent);
						else
							leftRightRotation(current, parent);
					}
					break;
				}
			}
		}
	}

	// Method remove
	// O(log n)
	public T remove(T value) {
		if (isEmpty())
			throw new IllegalStateException("The AVL tree is empty");

		Node<T> parent = root;
		Node<T> current = root;
		Stack<Node<T>> stack = new Stack<Node<T>>();
		while (current != null) {
			if (current.getElement().compareTo(value) > 0) {
				stack.push(current);
				parent = current;
				current = current.getLeft();
			} else if (current.getElement().compareTo(value) < 0) {
				stack.push(current);
				parent = current;
				current = current.getRight();
			} else {
				Node<T> temp;
				Node<T> newCurrent = null;

				if (current.getLeft() == null && current.getRight() == null) {
					if (current == root)
						root = null;
					else if (current == parent.getLeft())
						parent.setLeft(null);
					else
						parent.setRight(null);
					temp = current;
				} else if (current.getRight() == null) {
					if (current == root)
						root = current.getLeft();
					else if (current == parent.getLeft())
						parent.setLeft(current.getLeft());
					else
						parent.setRight(current.getLeft());
					temp = current;
				} else if (current.getLeft() == null) {
					if (current == root)
						root = current.getRight();
					if (current == parent.getLeft())
						parent.setLeft(current.getRight());
					else
						parent.setRight(current.getRight());
					temp = current;
				} else {
					newCurrent = current.getLeft();
					Node<T> newCurrentParent = current.getLeft();
					while (newCurrent.getRight() != null) {
						stack.push(newCurrent);
						newCurrentParent = newCurrent;
						newCurrent = newCurrent.getRight();
					}

					if (newCurrent == newCurrentParent)
						newCurrent.setRight(current.getRight());
					else {
						newCurrentParent.setRight(newCurrent.getLeft());
						newCurrent.setLeft(current.getLeft());
						newCurrent.setRight(current.getRight());
						newCurrent.setHeight(current.getHeight());
					}

					if (current == root)
						root = newCurrent;
					else if (current == parent.getLeft())
						parent.setLeft(newCurrent);
					else
						parent.setRight(newCurrent);
					temp = current;

				}
				while (current != root) {
					current = parent;
					if (!stack.isEmpty())
						parent = stack.pop();
					else
						parent = null;

					int hl = current.getLeft() != null ? current.getLeft().getHeight() : 0;
					int hr = current.getRight() != null ? current.getRight().getHeight() : 0;
					
					if (hl > hr)
						current.setHeight(hl + 1);
					else
						current.setHeight(hr + 1);
					
					int balanceFactor = hl - hr;
					if (balanceFactor < -1 || balanceFactor > 1) {
						Node<T> child;
						if (balanceFactor < -1) {
							child = current.getRight();
							hl = child.getLeft() != null ? child.getLeft().getHeight() : 0;
							hr = child.getRight() != null ? child.getRight().getHeight() : 0;
							balanceFactor = hl - hr;
							if (balanceFactor < 0)
								leftRotation(current, parent);
							else
								rightLeftRotation(current, parent);
						} else {
							child = current.getLeft();
							hl = child.getLeft() != null ? child.getLeft().getHeight() : 0;
							hr = child.getRight() != null ? child.getRight().getHeight() : 0;
							balanceFactor = hl - hr;
							if (balanceFactor > 0)
								rightRotation(current, parent);
							else
								leftRightRotation(current, parent);
						}
						break;
					}
					if (newCurrent != null && newCurrent.getLeft() == parent) {
						stack.push(newCurrent);
					}
				}
				return temp.getElement();
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
			current = root.getRight();
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
			current = root.getRight();
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
			current = root.getRight();
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
