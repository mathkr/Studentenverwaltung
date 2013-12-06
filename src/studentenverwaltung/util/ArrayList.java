package studentenverwaltung.util;

public class ArrayList<T> {
	private static final int DEFAULT_CAPACITY = 100;

	private Object[] elements;
	private int size;

	public ArrayList (int initialCapacity){
		elements = new Object[initialCapacity];
		size = 0;
	}

	public ArrayList (){
		this(DEFAULT_CAPACITY);
	}

	public int getSize(){
		return size;
	}

	public void remove(int index){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		} else {
			for(int i = index; i < size - 1; ++i){
					elements[i] = elements[i + 1];
			}
			elements[--size] = null;
		}
	}

	public void add(T element){
		if(size == elements.length){
			ensureCapacity();
		}

		elements[size++] = element;
	}

	public void set(int index, T element){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		} else {
			elements[index] = element;
		}
	}

	public T get(int index){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		} else {
			return (T) elements[index];
		}
	}

	public boolean contains(T element){
		for(int i = 0; i < size; ++i){
			if(elements[i].equals(element)){
				return true;
			}
		}
		return false;
	}

	private void ensureCapacity(){
		int newSize = elements.length * 2;
		Object[] newElements = new Object[newSize];

		for(int i = 0; i < size; ++i){
			newElements[i] = elements[i];
		}

		elements = newElements;
	}
}
