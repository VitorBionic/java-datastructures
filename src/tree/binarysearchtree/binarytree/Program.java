package tree.binarysearchtree.binarytree;

import java.util.function.Consumer;

import node.btnode.Node;

public class Program {

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.add(10);
		tree.add(4);
		tree.add(2);
		tree.add(11);
		tree.add(9);
		tree.add(5);
		
		Consumer<Node<Integer>> print = (node) -> System.out.print(node.getElement() + " ");
		
		System.out.print("inOrderTraversal: ");
		tree.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree.posOrderTraversalIT(print);
		System.out.println();
		System.out.println("search(5): " + tree.search(5));
			
		System.out.println("Removing: " + tree.remove(5));
		System.out.println();
		System.out.println();
		System.out.print("inOrderTraversal: ");
		tree.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree.posOrderTraversalIT(print);
		System.out.println();
		System.out.println("search(5): " + tree.search(5));

	}

}
