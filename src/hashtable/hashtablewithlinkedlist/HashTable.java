package hashtable.hashtablewithlinkedlist;

import linkedlist.linkedlist.LinkedList;
import hashtable.Entry;

//Creating class
public class HashTable <K, V> {
	
	// Instance Variables
	private int buckets;
	private LinkedList<Entry<K, V>>[] table;
	private final float loadFactor;
	private int count;
	
	// Constructor of class
	@SuppressWarnings("unchecked")
	public HashTable() {
		this.buckets = 11;
		this.table = new LinkedList[buckets];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Entry<K, V>>();
		}
		this.loadFactor = 0.75f;
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int buckets) {
		if (buckets < 0)
			throw new IllegalArgumentException("The quantity of buckets can't be negative");
		this.buckets = buckets;
		this.table = new LinkedList[buckets];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Entry<K, V>>();
		}
		this.loadFactor = 0.75f;
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int buckets, float loadFactor) {
		if (buckets < 0)
			throw new IllegalArgumentException("The quantity of buckets can't be negative");
		if (loadFactor <= 0)
			throw new IllegalArgumentException("The load factor can't be nonpositive");
		this.buckets = buckets;
		this.table = new LinkedList[buckets];
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Entry<K, V>>();
		}
		this.loadFactor = loadFactor;
	}

	private int hashFunction(K key) {
		int hashCode = key.hashCode();
		int k = hashCode >= 0 ? hashCode : hashCode * -1; 
		return k % buckets;
	}
	
	public boolean containsKey(K key) {
		int hashIndex = hashFunction(key);
		if (!table[hashIndex].isEmpty()) {
			int size = table[hashIndex].getSize();
			for (int i = 0; i < size; i++) {
				Entry<K, V> current = table[hashIndex].get(i);
				if (current.getKey().equals(key))
					return true;
			}
		}
		return false;
	}
	
	public boolean containsValue(V value)  {
		for (LinkedList<Entry<K, V>> ll : table) {
			for (int i = 0; i < ll.getSize(); i++) {
				if (ll.get(i).getValue().equals(value))
					return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int size() {
		return count;
	}
	
	public void clear() {
		for (LinkedList<Entry<K, V>> ll : table)
			ll.clear();
		count = 0;
	}
	
	private boolean needsRehashing() {
		return ((float) count / buckets) > loadFactor;
	}
	
	@SuppressWarnings("unchecked")
	private void rehash() {
		LinkedList<Entry<K, V>>[] oldTable = table;
		buckets *= 2;
		table = new LinkedList[buckets];
		count = 0;
		for (int i = 0; i < table.length; i++)
			table[i] = new LinkedList<Entry<K,V>>();
		for (LinkedList<Entry<K, V>> ll : oldTable) {
			int size = ll.getSize();
			for (int i = 0; i < size; i++) {
				Entry<K, V> current = ll.get(i); 
				put(current.getKey(), current.getValue());
			}
		}
	}
	
	// Method put
	// O(1)
	public void put(K key, V value) {
		int hashIndex = hashFunction(key);
		Entry<K, V> entry = new Entry<K, V>(key, value);
		int index = table[hashIndex].indexOf(entry);
		if (index == -1) {
		    table[hashIndex].addLast(entry);
		    count++;
		}
		
		if (needsRehashing())
			rehash();
	}
	
	// Method remove
	// O(1)
	public V remove(K key) {
		int hashIndex = hashFunction(key);
		V removed = null;
		int size = table[hashIndex].getSize();
		for (int i = 0; i < size; i++) {
			Entry<K, V> current = table[hashIndex].get(i);
			if (current.getKey().equals(key)) {
				removed = current.getValue();
				table[hashIndex].remove(i);
			    break;
			}
		}
		if (removed != null)
		    count--;
		return removed;
	}
	
	public boolean remove(K key, V value) {
		int hashIndex = hashFunction(key);
		Entry<K, V> entry = new Entry<K, V>(key, value);
		int indexInBucket = table[hashIndex].indexOf(entry);
		if (indexInBucket == -1)
			return false;
		table[hashIndex].remove(indexInBucket);
		count--;
		return true;
	}
	
	// Method get
	// O(1)
	public V get(K key) {
		int hashIndex = hashFunction(key);
		int size = table[hashIndex].getSize();
		for (int i = 0; i < size; i++) {
			Entry<K, V> current = table[hashIndex].get(i);
			if (current.getKey().equals(key))
				return current.getValue();
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++)
			sb.append(i + " -> " + table[i] + "\n");
		return sb.toString();
	}
	
	public String toStringLinear() {
		StringBuilder sb = new StringBuilder("[");
		boolean first = true;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].getSize(); j++) {
				if (first) {
					sb.append(table[i].get(j).getValue());
					first = false;
					continue;
				}
				sb.append(", " + table[i].get(j).getValue());
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	
	
}
