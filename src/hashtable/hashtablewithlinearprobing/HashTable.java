package hashtable.hashtablewithlinearprobing;

import hashtable.Entry;

//Creating class
public class HashTable<K, V> {

	// Instance Variables
	private int buckets;
	private Entry<K, V>[] table;
	private final float loadFactor;
	private int count;

	// Constructor of class
	@SuppressWarnings("unchecked")
	public HashTable() {
		this.buckets = 11;
		this.table = new Entry[buckets];
		this.loadFactor = 0.75f;
	}

	@SuppressWarnings("unchecked")
	public HashTable(int buckets) {
		if (buckets < 0)
			throw new IllegalArgumentException("The quantity of buckets can't be negative");
		this.buckets = buckets;
		this.table = new Entry[buckets];
		this.loadFactor = 0.75f;
	}

	@SuppressWarnings("unchecked")
	public HashTable(int buckets, float loadFactor) {
		if (buckets < 0)
			throw new IllegalArgumentException("The quantity of buckets can't be negative");
		if (loadFactor <= 0)
			throw new IllegalArgumentException("The load factor can't be nonpositive");
		this.buckets = buckets;
		this.table = new Entry[buckets];
		this.loadFactor = loadFactor;
	}

	private int hashFunction(K key) {
		int hashCode = key.hashCode();
		int k = hashCode >= 0 ? hashCode : hashCode * -1;
		return k % buckets;
	}

	public boolean containsKey(K key) {
		int hashIndex = hashFunction(key);
		int currentIndex = hashIndex;
		do {
			if (table[currentIndex] != null && table[currentIndex].getKey().equals(key))
				return true;
			currentIndex = (currentIndex + 1) % buckets;
		} while (table[currentIndex] != null);
		return false;
	}

	public boolean containsValue(V value) {
		for (Entry<K, V> entry : table) {
			if (entry != null && entry.getValue().equals(value))
				return true;
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
		for (int i = 0; i < table.length; i++)
			table[i] = null;
		count = 0;
	}

	private boolean needsRehashing() {
		return ((float) count / buckets) > loadFactor;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		Entry<K, V>[] oldTable = table;
		buckets *= 2;
		table = new Entry[buckets];
		count = 0;
		for (Entry<K, V> entry : oldTable) {
			if (entry != null)
				put(entry.getKey(), entry.getValue());
		}
	}

	// Method put
	// O(1)
	public void put(K key, V value) {
		if (key == null || value == null)
			throw new NullPointerException("HashTable does not support null keys/values");
		int hashIndex = hashFunction(key);
		Entry<K, V> entry = new Entry<K, V>(key, value);
		int currentIndex = hashIndex;
		while (table[currentIndex] != null) {
			if (table[currentIndex].getKey().equals(key)) {
			    table[currentIndex].setValue(value);
				return;
			}
			currentIndex = (currentIndex + 1) % buckets;
		}
		table[currentIndex] = entry;
		count++;

		if (needsRehashing())
			rehash();
	}

	// Method remove
	// O(1)
	public V remove(K key) {
		int hashIndex = hashFunction(key);
		V removed = null;
		int currentIndex = hashIndex;
		while (table[currentIndex] != null) {
			if (table[currentIndex].getKey().equals(key)) {
				removed = table[currentIndex].getValue();
				table[currentIndex] = null;
				count--;
				
				int removedPos = currentIndex;
				int pos = removedPos + 1 % buckets;
				while (table[pos] != null) {
				    int keyHashInPos = hashFunction(table[pos].getKey());
				    if (keyHashInPos <= hashIndex || keyHashInPos <= removedPos) {
				        table[removedPos] = table[pos];
				        table[pos] = null;
				        removedPos =  pos;
				    }
				    pos = (pos + 1)  % buckets;
				}
				
				break;
			}
			currentIndex = (currentIndex + 1) % buckets;
		}
		
		return removed;
	}
	
	public boolean remove(K key, V value) {
        int hashIndex = hashFunction(key);
        Entry<K, V> entry = new Entry<K, V>(key, value);
        int currentIndex = hashIndex;
        do {
            if (table[currentIndex].equals(entry)) {
                table[currentIndex] = null;
                count--;
                
                int removedPos = currentIndex;
                int pos = removedPos + 1 % buckets;
                while (table[pos] != null) {
                    int keyHashInPos = hashFunction(table[pos].getKey());
                    if (keyHashInPos <= hashIndex || keyHashInPos <= removedPos) {
                        table[removedPos] = table[pos];
                        table[pos] = null;
                        removedPos =  pos;
                    }
                    pos = (pos + 1)  % buckets;
                }
                
                return true;
            }
            currentIndex = (currentIndex + 1) % buckets;
        } while (table[currentIndex] != null);
        return false;
    }
	
	// Method get
	// O(1)
	public V get(K key) {
		int hashIndex = hashFunction(key);
		int currentIndex = hashIndex;
		do {
			if (table[currentIndex].getKey().equals(key))
				return table[currentIndex].getValue();
			currentIndex = (currentIndex + 1) % buckets;
		} while (table[currentIndex] != null);
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++)
			sb.append(i + " -> " + table[i] + "\n");
		return sb.toString();
	}

}
