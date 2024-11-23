package tree.binarysearchtree.avltree;

import java.util.function.Consumer;

import node.avlnode.Node;

public class Program2 {
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>();
		for (int i = 0; i < 10000; i++)
			tree.add(i);
		
		Consumer<Node<Integer>> print = (node) -> System.out.print(node.getElement() + " ");
		
		System.out.print("inOrderTraversalIT: ");
		tree.inOrderTraversalIT(print);
	    System.out.println();
	    System.out.print("preOrderTraversalIT: ");
		tree.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree.posOrderTraversalIT(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree.levelOrderTraversal(print);
		System.out.println();
		
	}
}
