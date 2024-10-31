package queue.queue;

import queue.queue.Queue;

public class Program {
	public static void main(String[] args) {
		Queue <Integer> queue = new Queue<Integer>(Integer.class, 6);
		 
		System.out.println(queue);
		queue.enqueue(1);
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.enqueue(9);
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.enqueue(7);
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.enqueue(3);
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.enqueue(4);
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		System.out.println("Memory: " + queue.checkMemory());
		queue.dequeue();
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.dequeue();
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.dequeue();
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.dequeue();
		System.out.println(queue);
		System.out.println("First in Queue: " + queue.peek());
		queue.dequeue();
		System.out.println(queue);
		System.out.println("Memory: " + queue.checkMemory());
	}
}
