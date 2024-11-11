package linkedlist.doublylinkedlist;

public class Program2 {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<>();
		
		System.out.println("Linked List");
		System.out.println();
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addFirst(1);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addFirst(9);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addFirst(7);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addLast(3);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addLast(4);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());

		System.out.println();
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addFirst(1);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addFirst(9);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addFirst(7);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addLast(3);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		System.out.println();
		ll.addLast(4);
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
		
		System.out.println();
		System.out.println();
		LinkedList.sort(ll);
		System.out.println("Linked List sorted:");
		System.out.println(ll);
		System.out.println("First element in List: " + ll.peekFirst());
		System.out.println("Last element in List: " + ll.peekLast());
		System.out.println("Size: " + ll.getSize());
	}

}
