package set.unorderedset;

import hashtable.hashtablewithlinkedlist.HashTable;

public class Set <T> {
	
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
	
	@Override
	public String toString() {
		return hashSet.toStringLinear();
	}
}
