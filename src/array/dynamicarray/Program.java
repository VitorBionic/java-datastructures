package array.dynamicarray;

public class Program {
	public static void main(String[] args) {
		DynamicArray<Integer> arr = new DynamicArray<>(Integer.class, 6);
		
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(22);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(276);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(3);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(14);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(69);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(1);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(3, 22);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(1 , 276);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(4, 3);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(0, 14);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(2, 69);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		arr.add(7, 1);
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		
		System.out.println();
		System.out.println("indexOf(276): " + arr.indexOf(276));
		System.out.println("lastIndexOf(69): " + arr.lastIndexOf(69));
		System.out.println();
		
		System.out.println("Removing: " + arr.remove((int)2));
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		System.out.println("Removing: " + arr.remove((int)8));
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		System.out.println("Removing: " + arr.remove((int)0));
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		System.out.println("Removing: " + arr.remove((Integer)69));
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		System.out.println("Removing: " + arr.remove((Integer)3));
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		System.out.println("Removing: " + arr.remove((Integer)1));
		System.out.println(arr);
		System.out.println("Size: " + arr.size());
		
	}
}
