package vector;

public class VectorManager {
	private VectorFrame frame;
	private PrintVector<Integer> vector;

	public static void main (String[] args) {
		new VectorManager();
	}

	VectorManager() {
		vector = new PrintVector<Integer>();
		for (int i = 0; i < 10; ++i) {
			vector.push_back(i);
		}
		frame = new VectorFrame(vector, this);
	}

	public void push_back() {
		vector.push_back(1);
	}

	public void push_front() {
		vector.push_back(1);
	}

	public void double_back() {
		vector.double_back(1);
	}

	public void double_front() {
		vector.double_front(1);
	}
}
