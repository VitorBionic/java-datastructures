package stack.stack;

public class Program {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>(Integer.class, 6);
		 
		System.out.println(stack);
		stack.push(1);
		System.out.println(stack);
		stack.push(9);
		System.out.println(stack);
		stack.push(7);
		System.out.println(stack);
		stack.push(3);
		System.out.println(stack);
		stack.push(4);
		System.out.println(stack);
		System.out.println(stack.checkMemory());
		stack.pop();
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
		System.out.println(stack.checkMemory());
		

	}

}
