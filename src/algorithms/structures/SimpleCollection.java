package algorithms.structures;

public interface SimpleCollection<T> {
	public int size();
	public void push_back(T arg);
	public void push_front(T arg);
	public T get(int i);
	public void set(int i, T arg);
	public void delete(int i);
}
