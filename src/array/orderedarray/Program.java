package array.orderedarray;

public class Program {

	public static void main(String[] args) {
		OrderedArray<Integer> oa = new OrderedArray<>(Integer.class, 6);

		oa.insert(22);
		oa.insert(276);
		oa.insert(3);
		oa.insert(14);
		oa.insert(69);
		oa.insert(1);
		
		System.out.println(oa);
		System.out.println(oa.linearSearch(69));
		System.out.println(oa.binarySearch(69));
		System.out.println(oa.get(3));
		
		oa.remove(3);
		
		System.out.println(oa);
		System.out.println(oa.checkMemory());
		
	}
}
