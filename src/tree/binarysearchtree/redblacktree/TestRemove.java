package tree.binarysearchtree.redblacktree;

import java.util.function.Consumer;

import node.redblacknode.Node;

public class TestRemove {
	public static void main(String[] args) {
	    RedBlackTree<Integer> tree1 = new RedBlackTree<>();
        tree1.add(10);
        tree1.add(4);
        tree1.add(2);
        tree1.add(11);
        tree1.add(9);
        tree1.add(5);
        
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
        
        System.out.println("Removing: " + tree1.remove(2));
        
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
        
        
	    RedBlackTree<Integer> tree2 = new RedBlackTree<>();
		tree2.add(10);
		tree2.add(5);
		tree2.add(15);
		tree2.add(3);
		tree2.add(8);
		
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
		
		System.out.println("Removing: " + tree2.remove(10));
		
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
		
		
		RedBlackTree<Integer> tree3 = new RedBlackTree<>();
		tree3.add(10);
		tree3.add(5);
		tree3.add(15);
		tree3.add(13);
		tree3.add(20);
		tree3.add(22);
		tree3.add(2);
		tree3.add(8);
		tree3.add(9);
		tree3.add(1);
		tree3.add(3);
		tree3.add(4);
		
		System.out.println("----------------------------------");
		System.out.println("TREE 3:");
		
		System.out.print("inOrderTraversal: ");
		tree3.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree3.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree3.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree3.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree3.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree3.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree3.posOrderTraversalIT(print);
		System.out.println();
		
		System.out.println("Removing: " + tree3.remove(5));
		
		System.out.print("inOrderTraversal: ");
		tree3.inOrderTraversal(print);
		System.out.println();
		System.out.print("preOrderTraversal: ");
		tree3.preOrderTraversal(print);
		System.out.println();
		System.out.print("posOrderTraversal: ");
		tree3.posOrderTraversal(print);
		System.out.println();
		System.out.print("levelOrderTraversal: ");
		tree3.levelOrderTraversal(print);
		System.out.println();
		System.out.print("inOrderTraversalIT: ");
		tree3.inOrderTraversalIT(print);
		System.out.println();
		System.out.print("preOrderTraversalIT: ");
		tree3.preOrderTraversalIT(print);
		System.out.println();
		System.out.print("posOrderTraversalIT: ");
		tree3.posOrderTraversalIT(print);
		System.out.println();
		
	}
}
