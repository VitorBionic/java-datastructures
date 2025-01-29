package set.unorderedset;

public class Program2 {

    public static void main(String[] args) {

        Set<Integer> set1 = new Set<Integer>();
        
        set1.add(13);
        set1.add(24);
        set1.add(68);
        set1.add(777);
        set1.add(52);
        set1.add(111);
        set1.add(-9);
        set1.add(1024);
        
        System.out.println("Set 1:");
        System.out.println(set1);
        System.out.println();
        
        Set<Integer> set2 = new Set<Integer>();
        
        set2.add(13);
        set2.add(24);
        set2.add(68);
        set2.add(146);
        set2.add(12);
        set2.add(90);
        set2.add(-9);
        set2.add(290);
        
        System.out.println("Set 2:");
        System.out.println(set2);
        System.out.println();
        
        System.out.println("set1.union(set2):");
        System.out.println(set1.union(set2));
        System.out.println();
        
        System.out.println("set1.intersection(set2):");
        System.out.println(set1.intersection(set2));
        System.out.println();
        
        System.out.println("set1.difference(set2):");
        Set<Integer> diff = set1.difference(set2);
        System.out.println(diff);
        System.out.println();
        
        System.out.println("set2.difference(set1):");
        System.out.println(set2.difference(set1));
        System.out.println();
        
        System.out.println("set1.isSubsetOf(set2):");
        System.out.println(set1.isSubsetOf(set2));
        System.out.println();
        
        System.out.println("Removing elements in set 1");
        for (Integer num : diff) {
            set1.remove(num);
        }
        
        System.out.println("Set 1:");
        System.out.println(set1);
        System.out.println();
        
        System.out.println("Set 2:");
        System.out.println(set2);
        System.out.println();
        
        System.out.println("set1.isSubsetOf(set2):");
        System.out.println(set1.isSubsetOf(set2));
        System.out.println();
    }
}
