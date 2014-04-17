public class List<T> implements SimpleCollection<T> {
	private ListElement head;
	private ListElement tail;

	private int size;
	
	public List(){
		head = null;
		tail = null;
		size = 0;
	}

	public int size(){
		return this.size;
	}

	public void push_back(T arg){
		tail = new ListElement<T>(arg, tail, null);
		if(size == 0) head = tail;
		++size;
	}

	public void push_front(T arg){
		head = new ListElement<T>(arg, null, head);
		if(size == 0) tail = head;
		++size;
	}

	public T get(int i){
		checkBounds(i);

		return (T) getListElement(i).getObject();
	}

	public T getHead(){
		if(tail != null){
			return (T) head.getObject();
		} else {
			return null;
		}
	}

	public T getTail(){
		if(tail != null){
			return (T) tail.getObject();
		} else {
			return null;
		}
	}

	public void set(int i, T arg){
		checkBounds(i);

		ListElement e = getListElement(i);

		e.setObject(arg);
	}

	public void delete(int i){
		checkBounds(i);

		ListElement e = getListElement(i);

		if (e == head){
			if(e.getNext() != null){
				head = e.getNext();
				head.setPrev(null);
				--size;
			} else {
				head = null;
				tail = null;
				size = 0;
			}
		} else if (e == tail){
			if(e.getPrev() != null){
				tail = e.getPrev();
				tail.setNext(null);
				--size;
			}
		} else {
			e.getPrev().setNext(e.getNext());
			e.getNext().setPrev(e.getPrev());
			--size;
		}

		e = null;
	}

	public void removeTail(){
		if(tail != null){
			if(tail == head){
				tail = null;
				head = null;
				--size;
			} else {
				tail.getPrev().setNext(null);
				tail = tail.getPrev();
				--size;
			}
		}
	}

	public void removeHead(){
		if(head != null){
			if(head == tail){
				head = null;
				tail = null;
				--size;
			} else {
				head.getNext().setPrev(null);
				head = head.getNext();
				--size;
			}
		}
	}

	private ListElement getListElement(int i){
		ListElement res = null;

		if(i < size / 2){
			res = head;
			for(int j = 0; j < i; ++j){
				res = res.getNext();
			}
		} else {
			res = tail;
			for(int j = size - 1; j > i; --j){
				res = res.getPrev();
			}
		}

		return res;
	}

	private void checkBounds(int index){
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	class ListElement<T> {
		private Object obj;
		private ListElement prev;
		private ListElement next;

		public ListElement(T obj, ListElement prev, ListElement next){
			this.obj = obj;
			this.prev = prev;
			this.next = next;

			if(prev != null) prev.setNext(this);
			if(next != null) next.setPrev(this);
		}

		public ListElement getNext(){ return next; }
		public ListElement getPrev(){ return prev; }

		public void setNext(ListElement newNext){ next = newNext; }
		public void setPrev(ListElement newPrev){ prev = newPrev; }

		public T getObject(){ return (T) obj; }
		public void setObject(T obj){ this.obj = obj; }
	}
}
