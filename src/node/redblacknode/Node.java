package node.redblacknode;

public class Node <T> {

    private T element;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private Color color;
    
    public Node(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = Color.RED;
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

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Color getColor() {
        return color;
    }
    
    public boolean isRed() {
        return this.color == Color.RED;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public String toString() {
        return "" + element;
    }
}
