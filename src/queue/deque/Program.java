package queue.deque;

public class Program {
	public static void main(String[] args) {
		Deque <Integer> deque = new Deque<Integer>(Integer.class, 6);
		 
		System.out.println(deque);
		deque.addFirst(1);
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.addLast(9);
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.addFirst(7);
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.addLast(3);
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.addFirst(4);
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		System.out.println("Memory: " + deque.checkMemory());
		deque.removeLast();
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.removeFirst();
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.removeLast();
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.removeFirst();
		System.out.println(deque);
		System.out.println("First in Queue: " + deque.getFirst());
		System.out.println("Last in Queue: " + deque.getLast());
		deque.removeLast();
		System.out.println(deque);
		System.out.println("Memory: " + deque.checkMemory());
	}
}
