package set.unorderedset;

import java.util.Iterator;

import hashtable.hashtablewithlinkedlist.HashTable;

public class Set <T> implements Iterable<T> {
	
	private HashTable<T, T> hashSet;
	
	public Set() {
		hashSet = new HashTable<T, T>();
	}
	
	public void clear() {
		hashSet.clear();
	}
	
	public boolean contains(T element) {
		return hashSet.containsKey(element);
	}
	
	public boolean isEmpty() {
		return hashSet.isEmpty();
	}
	
	public int size() {
		return hashSet.size();
	}
	
	public boolean add(T element) {
		if (contains(element))
		    return false;
		hashSet.put(element, element);
	    return true;
	}
	
	public boolean remove(T element) {
		if (!contains(element))
			return false;
		hashSet.remove(element);
		return true;
	}
	
	public Set<T> union(Set<T> otherSet) {
	    Set<T> union = new Set<>();
	    for (T element : this)
	        union.add(element);
	    for (T element : otherSet)
	        union.add(element);
	    return union;	    
	}
	
	public Set<T> intersection(Set<T> otherSet) {
	    Set<T> intersection = new Set<>();
	    
	    if (this.size() <= otherSet.size()) {
	        for (T element : this) {
	            if (otherSet.contains(element))
	                intersection.add(element);
	        }        
	    } else {
	        for (T element : otherSet) {
                if (this.contains(element))
                    intersection.add(element);
	        }
	    }
	    return intersection;
	}
	
	public Set<T> difference(Set<T> otherSet) {
	    Set<T> difference = new Set<>();
	    
	    for (T element : this) {
	        if (!otherSet.contains(element))
	            difference.add(element);
	    }
	    return difference;
	}
	
	public boolean isSubsetOf(Set<T> otherSet) {
	    if (this.size() > otherSet.size())
	        return false;
	    
	    for (T element : this) {
	        if (!otherSet.contains(element))
	            return false;
	    }
	    return true;
	}
	
	@Override
	public String toString() {
		return hashSet.toStringLinear();
	}
	
	@Override
    public Iterator<T> iterator() {
        return hashSet.iterator();
    }
    
}
