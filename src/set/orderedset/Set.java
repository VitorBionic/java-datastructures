package set.orderedset;

import node.avlnode.Node;
import stack.dynamicstack.Stack;

public class Set<T extends Comparable<T>> {

	private Node<T> root;
	private int count;

	public Set() {
		root = null;
	}
	
	public void clear() {
		root = null;
		count = 0;
	}
	
	public boolean contains(T element) {
		Node<T> current = root;
		while (current != null) {
			if (current.getElement().compareTo(element) > 0)
				current = current.getLeft();
			else if (current.getElement().compareTo(element) < 0)
				current = current.getRight();
			else
				return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return root == null;
	}
	
	public int size() {
		return count;
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
	public boolean add(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			root = newNode;
			count++;
			return true;
		} else {
			Stack<Node<T>> stack = new Stack<>();
			Node<T> current = root;
			while (current != null) {
				stack.push(current);
				if (current.getElement().compareTo(element) > 0)
					current = current.getLeft();
				else if (current.getElement().compareTo(element) < 0)
					current = current.getRight();
				else
					return false;
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
			count++;
			return true;
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

					if (newCurrent == newCurrentParent) {
						newCurrent.setRight(current.getRight());
						if (current.getRight() != null && current.getRight().getHeight() + 1 >= current.getLeft().getHeight() + 1) {
						    if (newCurrent.getHeight() < current.getHeight())
							    newCurrent.setHeight(current.getHeight());
						} else {
							if (newCurrent.getHeight() < current.getHeight())
						        newCurrent.setHeight(current.getHeight() - 1);
						}
					} else {
						newCurrentParent.setRight(newCurrent.getLeft());
						newCurrent.setLeft(current.getLeft());
						newCurrent.setRight(current.getRight());
						if (current.getRight() != null && current.getRight().getHeight() + 1 >= current.getLeft().getHeight() + 1) {
							 if (newCurrent.getHeight() < current.getHeight())
							        newCurrent.setHeight(current.getHeight());
						} else {
							if (newCurrent.getHeight() < current.getHeight())
						        newCurrent.setHeight(current.getHeight() - 1);
						}
					}

					if (current == root)
						root = newCurrent;
					else if (current == parent.getLeft())
						parent.setLeft(newCurrent);
					else
						parent.setRight(newCurrent);
					temp = current;

				}
				
				parent = stack.pop();
				while (parent != null) {
					current = parent;
					if (newCurrent != null && newCurrent.getLeft() == parent)
						stack.push(newCurrent);
					
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
							if (balanceFactor <= 0)
								leftRotation(current, parent);
							else
								rightLeftRotation(current, parent);
						} else {
							child = current.getLeft();
							hl = child.getLeft() != null ? child.getLeft().getHeight() : 0;
							hr = child.getRight() != null ? child.getRight().getHeight() : 0;
							balanceFactor = hl - hr;
							if (balanceFactor >= 0)
								rightRotation(current, parent);
							else
								leftRightRotation(current, parent);
						}
					}
				}
				count--;
				return temp.getElement();
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (isEmpty()) {
			sb.append("]");
			return sb.toString();
		}
		if (root.getLeft() == null && root.getRight() == null) {
			sb.append(root.getElement() + "]");	
			return sb.toString();
		}
		stack.dynamicstack.Stack<Node<T>> stack = new stack.dynamicstack.Stack<>();
		stack.push(root);

		boolean first = true;
		
		Node<T> current = root.getLeft();
		if (current == null) {
			sb.append(root.getElement());
			first = false;
			current = root.getRight();
		}
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
				if (!first)
				    sb.append(", " + current.getElement());
				else {
					sb.append(current.getElement());
					first = false;
				}
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
		if (root.getRight() == null)
			sb.append(root.getElement());
		sb.append("]");
		return sb.toString();
	}
}
