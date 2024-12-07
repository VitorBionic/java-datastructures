package hashtable;

public class Entry <K, V> {
	
	private K key;
	private V value;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		Entry<K, V> otherEntry = (Entry<K, V>) o;
		return otherEntry != null && (key.equals(otherEntry.getKey()) && value.equals(otherEntry.getValue()));
	}
	
	
	@Override
	public String toString() {
		return "" + value;
	}

}
