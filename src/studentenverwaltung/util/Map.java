package studentenverwaltung.util;

public class Map<K, T> {
	private static final int DEFAULT_CAPACITY = 100;
	private ArrayList<K> keys;
	private ArrayList<T> elements;

	public Map (int initialCapacity){
		keys = new ArrayList<K>(initialCapacity);
		elements = new ArrayList<T>(initialCapacity);
	}

	public Map (){
		this(DEFAULT_CAPACITY);
	}

	public void put(K key, T element){
		for(int i = 0; i < keys.getSize(); ++i){
			if(keys.get(i).equals(key)){
				elements.set(i, element);
				return;
			}
		}

		keys.add(key);
		elements.add(element);
	}

	public int getSize(){
		return keys.getSize();
	}

	public boolean containsKey(K key){
		for(int i = 0; i < keys.getSize(); ++i){
			if(keys.get(i).equals(key)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<T> getValues(){
		ArrayList<T> res = new ArrayList();
		for(int i = 0; i < getSize(); ++i){
			res.add(elements.get(i));
		}
		return res;
	}

	public T get(K key){
		for(int i = 0; i < keys.getSize(); ++i){
			if(keys.get(i).equals(key)){
				return elements.get(i);
			}
		}
		return null;
	}

	public T get(int index){
		if(index >= getSize() || index < 0){
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + getSize());
		} else {
			return elements.get(index);
		}
	}

	public void remove(K key){
		for(int i = 0; i < keys.getSize(); ++i){
			if(keys.get(i).equals(key)){
				elements.remove(i);
				keys.remove(i);
				return;
			}
		}
	}
}
