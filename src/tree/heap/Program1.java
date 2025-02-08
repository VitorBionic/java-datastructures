package tree.heap;

import java.util.Random;

public class Program1 {

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<Integer>(Integer.class);
        
        Random rand = new Random(System.currentTimeMillis());
        
        for (int i = 0; i < 15; i++) {
            heap.insert(rand.nextInt(1000));
        }
        
        System.out.println(heap);
    }

}
