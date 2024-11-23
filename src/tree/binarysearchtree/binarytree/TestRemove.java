package tree.binarysearchtree.binarytree;

import java.util.function.Consumer;

import node.btnode.Node;

public class TestRemove {
	public static void main(String[] args) {
		BinaryTree<Integer> tree1 = new BinaryTree<>();
		tree1.add(10);
		tree1.add(5);
		tree1.add(15);
		tree1.add(3);
		tree1.add(8);
		
        Consumer<Node<Integer>> print = (node) -> System.out.print(node.getElement() + " ");
		System.out.println("TREE 1:");
		System.out.print("inOrderTraversal: ");
		tree1.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree1.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree1.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree1.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree1.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree1.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree1.posOrderTraversalIT(print);
		System.out.println();
		
		System.out.println("Removing: " + tree1.remove(10));
		
		System.out.print("inOrderTraversal: ");
		tree1.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree1.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree1.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree1.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree1.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree1.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree1.posOrderTraversalIT(print);
		System.out.println();
		
		
		BinaryTree<Integer> tree2 = new BinaryTree<>();
		tree2.add(10);
		tree2.add(5);
		tree2.add(15);
		tree2.add(13);
		tree2.add(20);
		tree2.add(22);
		tree2.add(2);
		tree2.add(8);
		tree2.add(9);
		tree2.add(1);
		tree2.add(3);
		tree2.add(4);
		
		System.out.println("----------------------------------");
		System.out.println("TREE 2:");
		
		System.out.print("inOrderTraversal: ");
		tree2.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree2.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree2.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree2.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree2.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree2.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree2.posOrderTraversalIT(print);
		System.out.println();
		
		System.out.println("Removing: " + tree2.remove(5));
		
		System.out.print("inOrderTraversal: ");
		tree2.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree2.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree2.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree2.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree2.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree2.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree2.posOrderTraversalIT(print);
		System.out.println();
		
	}
}
