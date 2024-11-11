package linkedlist.circulardoublylinkedlist;

public class Program {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		ll.addAfter(12);
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		ll.addBefore(3);
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		ll.addAfter(6);
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		ll.addBefore(1);
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		ll.addAfter(69);
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		ll.addBefore(31);
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		
		System.out.println();
		System.out.println();
		
		System.out.println("Removing element: " + ll.removeAfter());
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		System.out.println("Removing element: " + ll.removeBefore());
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		System.out.println("Removing element: " + ll.removeAfter());
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		System.out.println("Removing element: " + ll.removeBefore());
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		System.out.println("Removing element: " + ll.removeAfter());
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());
		System.out.println("Removing element: " + ll.removeBefore());
		System.out.println(ll);
		System.out.println("Size: " + ll.getSize());

	}

}
