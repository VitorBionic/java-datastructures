package tree.binarysearchtree.redblacktree;

import java.util.function.Consumer;

import node.redblacknode.*;

public class RedBlackTree <T extends Comparable<? super T>> {
    
    private Node<T> root;
    
    public RedBlackTree() {
        this.root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    // Method add
    // O(log n)
    public void add(T element) {
        Node<T> newNode = new Node<T>(element);
        if (isEmpty()) {
            newNode.setColor(Color.BLACK);
            this.root = newNode;
        } else {
            Node<T> current = root;
            while (true) {
                if (current.getElement().compareTo(element) > 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(newNode);
                        newNode.setParent(current);
                        break;
                    }
                    current = current.getLeft();
                } else {
                    if (current.getRight() == null) {
                        current.setRight(newNode);
                        newNode.setParent(current);
                        break;
                    }
                    current = current.getRight();
                }
            }
            
            this.fixPropertiesAdd(newNode);
        }
        
    }
    
    // Method remove
    // O(log n)
    public T remove(T value) {
        if (isEmpty())
            throw new IllegalStateException("The red-black tree is empty");
        Node<T> current = root;
        while (current != null) {
            if (current.getElement().compareTo(value) > 0)
                current = current.getLeft();
            else if (current.getElement().compareTo(value) < 0)
                current = current.getRight();
            else {
                Node<T> parent = current.getParent();
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
                    current.getRight().setParent(parent);
                } else if (current.getRight() == null) {
                    if (current == root)
                        root = current.getLeft();
                    else if (parent.getLeft() == current)
                        parent.setLeft(current.getLeft());
                    else
                        parent.setRight(current.getLeft());
                    current.getLeft().setParent(parent);;
                } else {
                    Node<T> newCurrent = current.getLeft();
                    while (newCurrent.getRight() != null) {
                        newCurrent = newCurrent.getRight();
                    }
                    
                    if (newCurrent == current.getLeft()) {
                        newCurrent.setRight(current.getRight());
                        current.getRight().setParent(newCurrent);
                    } else {
                        newCurrent.getParent().setRight(newCurrent.getLeft());
                        if (newCurrent.getLeft() != null)
                            newCurrent.getLeft().setParent(newCurrent.getParent());
                        newCurrent.setLeft(current.getLeft());
                        current.getLeft().setParent(newCurrent);
                        newCurrent.setRight(current.getRight());
                        current.getRight().setParent(newCurrent);
                    }
                    
                    newCurrent.setParent(current.getParent());
                    if (current == root)
                        root = newCurrent;
                    else if (parent.getLeft() == current)
                        parent.setLeft(newCurrent);
                    else
                        parent.setRight(newCurrent);
                }
                fixPropertiesRemove(parent);
                return current.getElement();    
            }
        }
        return null;
    }

    private void fixPropertiesAdd(Node<T> node) {
        while (node.getParent() != null && node.getParent().isRed() && node.isRed()) {
            Node<T> parent = node.getParent();
            Node<T> grandParent = parent.getParent();
            if (grandParent.getLeft() == parent) {
                Node<T> uncle = grandParent.getRight();
                if (uncle != null && uncle.isRed()) {
                    grandParent.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node = grandParent;
                } else {
                    if (parent.getRight() == node) {
                        leftRotate(parent);
                        node = parent;
                        parent = node.getParent();
                    }
                    
                    rightRotate(grandParent);
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    node = parent;               
                }
            } else {
                Node<T> uncle = grandParent.getLeft();
                if (uncle != null && uncle.isRed()) {
                    grandParent.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node = grandParent;
                } else {
                    if (parent.getLeft() == node) {
                        rightRotate(parent);
                        node = parent;
                        parent = node.getParent();
                    }
                    
                    leftRotate(grandParent);
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    node = parent;               
                }
            }
        }       
        this.root.setColor(Color.BLACK);
    }
    
    private void fixPropertiesRemove(Node<T> node) {
        while (node != null) {
            if (node.getRight() != null && node.getRight().isRed()) {
                leftRotate(node);
                node.setColor(Color.RED);
                node.getParent().setColor(Color.BLACK);
            } else if ((node.getRight() != null && !node.getRight().isRed())
                    && (node.getRight().getLeft() == null || !node.getRight().getLeft().isRed())
                    && (node.getRight().getRight() == null || !node.getRight().getRight().isRed())) {
                node.setColor(Color.BLACK);
                node.getRight().setColor(Color.RED);
            } else if ((node.getRight() != null && !node.getRight().isRed())
                    && (node.getRight().getLeft() != null && node.getRight().getLeft().isRed())
                    && (node.getRight().getRight() == null || !node.getRight().getRight().isRed())) {
                rightRotate(node.getRight());
                node.getRight().setColor(Color.BLACK);
                node.getRight().getRight().setColor(Color.RED);
            } else if ((node.getRight() != null && !node.getRight().isRed())
                    && (node.getRight().getRight() != null && node.getRight().getRight().isRed())) {
                leftRotate(node);
                node.getParent().setColor(node.getColor());
                node.setColor(Color.BLACK);
                node.getParent().getRight().setColor(Color.BLACK);
            } else {
                break;
            }
        }
        
    }

    private void rightRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        Node<T> leftChild = node.getLeft();
        if (parent == null)
            this.root = leftChild;
        else if (parent.getLeft() == node)
            parent.setLeft(leftChild);
        else
            parent.setRight(leftChild);
        leftChild.setParent(parent);
        node.setParent(leftChild);
        node.setLeft(leftChild.getRight());
        if (node.getLeft() != null)
            node.getLeft().setParent(node);
        leftChild.setRight(node);     
    }

    private void leftRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        Node<T> rightChild = node.getRight();
        if (parent == null)
            this.root = rightChild;
        else if (parent.getLeft() == node)
            parent.setLeft(rightChild);
        else
            parent.setRight(rightChild);
        rightChild.setParent(parent);
        node.setParent(rightChild);
        node.setRight(rightChild.getLeft());
        if (node.getRight() != null)
            node.getRight().setParent(node);
        rightChild.setLeft(node);   
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
        if (current == null) {
            consumer.accept(root);
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
        if (root.getRight() == null)
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
