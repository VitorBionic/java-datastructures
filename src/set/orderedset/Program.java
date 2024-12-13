package set.orderedset;

public class Program {
	public static void main(String[] args) {
		Set<Integer> set = new Set<Integer>();
		
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(13): " + set.add(13));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(24): " + set.add(24));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(68): " + set.add(68));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(777): " + set.add(777));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(52): " + set.add(52));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(111): " + set.add(111));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(-9): " + set.add(-9));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.add(1024): " + set.add(1024));
		System.out.println(set + "\n Size: " + set.size());
		
		System.out.println();
		System.out.println();
		
		System.out.println("set.remove(777): " + set.remove(777));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.remove(52): " + set.remove(52));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.remove(111): " + set.remove(111));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.remove(-9): " + set.remove(-9));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.remove(1024): " + set.remove(1024));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.remove(-9): " + set.remove(-9));
		System.out.println(set + "\n Size: " + set.size());
		System.out.println("set.remove(1024): " + set.remove(1024));
		System.out.println(set + "\n Size: " + set.size());
		
	}
}
