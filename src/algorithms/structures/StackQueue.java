package algorithms.structures;

public class StackQueue<T> extends List<T> {
	private boolean queue;

	public StackQueue(boolean queue){
		super();
		this.queue = queue;
	}

	public void push(T object){
		push_back(object);
	}

	public T pop(){
		T res = null;
		if(queue){
			res = getHead();
			removeHead();
		} else {
			res = getTail();
			removeTail();
		}

		return res;
	}

	public T peek(){
		T res = null;
		if(queue){
			res = getHead();
		} else {
			res = getTail();
		}
		return res;
	}
}
