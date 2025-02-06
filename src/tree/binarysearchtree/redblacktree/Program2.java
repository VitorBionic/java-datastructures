package tree.binarysearchtree.redblacktree;

import java.util.function.Consumer;

import node.redblacknode.Node;

public class Program2 {
	public static void main(String[] args) {
	    RedBlackTree<Integer> tree = new RedBlackTree<>();
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
