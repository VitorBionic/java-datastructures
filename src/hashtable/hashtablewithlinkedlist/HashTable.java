package hashtable.hashtablewithlinkedlist;

import linkedlist.linkedlist.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hashtable.Entry;

//Creating class
public class HashTable <K, V> implements Iterable<Entry<K, V>> {
	
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
		    Iterator<Entry<K, V>> itr = table[hashIndex].iterator();
		    while (itr.hasNext()) {
		        Entry<K, V> current = itr.next();
		        if (current.getKey().equals(key))
                    return true;
		    }	    
		}
		return false;
	}
	
	public boolean containsValue(V value)  {
		for (LinkedList<Entry<K, V>> ll : table) {
			for (Entry<K, V> entry : ll) {
				if (entry.getValue().equals(value))
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
		    for (Entry<K, V> entry : ll) {
				Entry<K, V> current = entry; 
				put(current.getKey(), current.getValue());
			}
		}
	}
	
	// Method put
	// O(1)
	public void put(K key, V value) {
		if (key == null)
			throw new IllegalArgumentException("Key can't be null");
		int hashIndex = hashFunction(key);
		Entry<K, V> newEntry = new Entry<K, V>(key, value);
		//
		boolean overrided = false;
		for (Entry<K, V> entry : table[hashIndex]) {
		    if (entry.getKey().equals(newEntry.getKey())) {
		        entry.setValue(newEntry.getValue());
		        overrided = true;
		    }
		}
		if (!overrided) {
		    table[hashIndex].addLast(newEntry);
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
		int i = 0;
		for (Entry<K, V> current : table[hashIndex]) {
			if (current.getKey().equals(key)) {
				removed = current.getValue();
				table[hashIndex].remove(i);
				count--;
			    break;
			}
			i++;
		}
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
		for (Entry<K, V> current : table[hashIndex]) {
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
	
	public Iterator<K> keys() {
	    return new KeyItr();
	}
	
	public Iterator<V> values() {
        return new ValueItr();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Itr();
    }
    
    private class Itr implements Iterator<Entry<K, V>> {
        int currentBucket;
        Iterator<Entry<K, V>> currentIterator;
        
        Itr() {
            currentIterator = table[0].iterator();
        }
        
        @Override
        public boolean hasNext() {
            while (currentBucket < buckets) {
                if (!currentIterator.hasNext()) {
                    currentBucket++;
                    if (currentBucket >= buckets)
                        break;
                    currentIterator = table[currentBucket].iterator();
                } else
                    return true;
            }
            return false;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return currentIterator.next();
        }
        
    }
    
    private class KeyItr implements Iterator<K> {

        Iterator<Entry<K, V>> entriesIterator;
        
        public KeyItr() {
            entriesIterator = iterator();
        }
        
        @Override
        public boolean hasNext() {
            return entriesIterator.hasNext();
        }

        @Override
        public K next() {
            if (!this.hasNext())
                throw new NoSuchElementException();
            return entriesIterator.next().getKey();
        }
        
    }
    
    private class ValueItr implements Iterator<V> {

        Iterator<Entry<K, V>> entriesIterator;
        
        public ValueItr() {
            entriesIterator = iterator();
        }
        
        @Override
        public boolean hasNext() {
            return entriesIterator.hasNext();
        }

        @Override
        public V next() {
            if (!this.hasNext())
                throw new NoSuchElementException();
            return entriesIterator.next().getValue();
        }
        
    }
	
}
