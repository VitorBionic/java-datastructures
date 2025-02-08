package tree.heap;

import java.lang.reflect.Array;

public class MaxHeap <T extends Comparable<? super T>> {
    
    private T[] heap;
    private int capacity;
    private int count;
    private final Class<T> c;
    
    @SuppressWarnings("unchecked")
    public MaxHeap(Class<T> c, int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("Capacity can't be lower than 1");
        this.capacity = capacity;
        this.c = c;
        this.heap = (T[]) Array.newInstance(c, capacity);
        this.count = 0;
    }
    
    @SuppressWarnings("unchecked")
    public MaxHeap(Class<T> c) {
        this.capacity = 11;
        this.c = c;
        this.heap = (T[]) Array.newInstance(c, capacity);
        this.count = 0;
    }
    
    public int size() {
        return count;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    @SuppressWarnings("unchecked")
    private boolean resize() {
        float factor = (float) count / capacity;
        if (factor > 0.75) {
            T[] temp = heap;
            this.capacity *= 2;
            this.heap = (T[]) Array.newInstance(c, capacity);
            for (int i = 0; i < temp.length; i++) {
                if (i >= count)
                    break;
                heap[i] = temp[i];
            }
            return true;
        }
        return false;
    }
    
    public T findMaximun() {
        return isEmpty() ? null : heap[0];
    }
    
    public boolean insert(T element) {
        if (element != null) {
            heap[count] = element;
            siftUp(count++);
            resize();
            return true;
        }
        return false;
    }
    
    public T extract() {
        if (isEmpty())
            return null;
        if (count == 1) {
            T temp = heap[0];
            heap[0] = null;
            count--;
            return temp;
        }
        T temp = heap[0];
        heap[count] = null;
        heap[0] = heap[--count];
        siftDown(0);
        return temp;
    }

    private void siftUp(int index) {
        int parent = getParentIndex(index);
        while (parent >= 0 && heap[index].compareTo(heap[parent]) > 0) {
            swap(index, parent);
            index = parent;
            parent = getParentIndex(index);
        }
        
    }
    
    private void siftDown(int index) {
        while (true) {
            int elementIndex = index;
            int left = getLeftIndex(index);
            int right = getRightIndex(index);
            if (left < count && heap[left].compareTo(heap[elementIndex]) > 0)
                elementIndex = left;
            if (right < count && heap[right].compareTo(heap[elementIndex]) > 0)
                elementIndex = right;
            
            if (elementIndex == index)
                break;
            swap(elementIndex, index);
            index = elementIndex;
        }
    }

    private int getLeftIndex(int index) {
        return 2 * index + 1;
    }
    
    private int getRightIndex(int index) {
        return 2 * index + 2;
    }
    
    private int getParentIndex(int index) {
        if (index == 0)
            return -1;
        return (index - 1) / 2;
    }
    
    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty())
            sb.append(heap[0]);
        for (int i = 1; i < count; i++)
            sb.append(", " + heap[i]);
        sb.append("]");
        return sb.toString();
    }
}
