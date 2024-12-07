package hashtable.hashtablewithlinearprobing;

public class Program {
	public static void main(String[] args) {
		HashTable<String, Integer> ht = new HashTable<>(11);
		
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("calabresa", 13);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("rxd", 69);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("meme", 34);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("scar", 02);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("estigma", 324);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("gay", 24);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("meneses", 102);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("text", 15);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("manga", 900);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("abajur", 72);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("leilaao", 1024);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("sigma", 143);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("cama", 1);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("xines", 67);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("camlin", 999);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("reign", 2359);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("simple", 3);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("a", 44);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("rilmar", 51);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("janga", 145);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("home", 85);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		ht.put("ripster", 777);
		System.out.println(ht + "Size: " + ht.size() + "\n");
		
		System.out.println("get('rxd'): " + ht.get("rxd"));
		System.out.println("get('simple'): " + ht.get("simple"));
		System.out.println("get('a'): " + ht.get("a"));
		System.out.println("get('ripster'): " + ht.get("ripster"));
		System.out.println("get('janga'): " + ht.get("janga"));
		System.out.println();
		System.out.println("containsKey('janga'): " + ht.containsKey("janga"));
		System.out.println("containsKey('simple'): " + ht.containsKey("simple"));
		System.out.println("containsKey('avocado'): " + ht.containsKey("avocado"));
		System.out.println("containsKey('mulambu'): " + ht.containsKey("mulambu"));
		
		System.out.println();
		
		System.out.println("containsValue(777): " + ht.containsValue(777));
		System.out.println("containsValue(44): " + ht.containsValue(44));
		System.out.println("containsValue(1234): " + ht.containsValue(1234));
		System.out.println("containsValue(7): " + ht.containsValue(7));
		
		System.out.println();
		System.out.println("removing entries");
		System.out.println("remove('gay'): " + ht.remove("gay"));
		System.out.println("remove('reign'): " + ht.remove("reign"));
		System.out.println("remove('janga', 145): " + ht.remove("janga", 145));
		System.out.println("remove('ripster', 777): " + ht.remove("ripster", 777));
		System.out.println(ht + "Size: " + ht.size() + "\n");
		System.out.println();
		System.out.println("containsKey('gay'): " + ht.containsKey("gay"));
		System.out.println("containsKey('janga'): " + ht.containsKey("janga"));
		System.out.println("containsValue(777): " + ht.containsValue(24));
		System.out.println("containsValue(145): " + ht.containsValue(145));
		
		
		System.out.println("remove('scar'): " + ht.remove("scar"));
		System.out.println(ht + "Size: " + ht.size() + "\n");
		System.out.println("containsKey('text'): " + ht.containsKey("text"));
		
		
	}
}
