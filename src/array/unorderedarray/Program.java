package array.unorderedarray;

public class Program {
	public static void main(String[] args) {
		UnorderedArray<Integer> ua = new UnorderedArray<Integer>(5);
		
		ua.insert(15);
		ua.insert(2);
		ua.insert(70);
		ua.insert(69);
		ua.insert(3);
		
		System.out.println(ua);
		System.out.println(ua.search(69));
		System.out.println(ua.get(3));
		
		ua.remove(3);
		
		System.out.println(ua);
		System.out.println(ua.checkMemory());
	}

}
