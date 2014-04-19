package studiverwaltung.util.structures;

/**
 * A standard binary search tree.
 */
public class BinarySearchTree<K extends Comparable<K>, T> {
	private Node<K, T> root;
	private int size;

	private class Node<P extends Comparable<P>, Q> {
		public P key;
		public Q value;

		public Node left;
		public Node right;

		public Node(P key, Q value) {
			this.key = key;
			this.value = value;
		}
	}

	public BinarySearchTree() {
		this.root = null;
	}

	public int size(){
		return this.size;
	}

	public T get(K key){
		return get(root, key);
	}

	private T get(Node<K, T> node, K key) {
		if (node == null) return null;

		int compare = key.compareTo(node.key);

		if (compare < 0) return get(node.left, key);
		if (compare > 0) return get(node.right, key);
		return node.value;
	}

	public void put(K key, T value) {
		root = put(root, key, value);
	}

	private Node<K, T> put(Node<K, T> node, K key, T value) {
		if (node == null) {
			++size;
			return new Node(key, value);
		}

		int compare = key.compareTo(node.key);

		if (compare < 0) node.left = put(node.left, key, value);
		else if (compare > 0) node.right = put(node.right, key, value);
		else node.value = value;

		return node;
	}

	public T getMax() {
		return root == null ? null : getMax(root).value;
	}

	public T getMin() {
		return root == null ? null : getMin(root).value;
	}

	private Node<K, T> getMax(Node<K, T> node) {
		if (node == null) return null;
		if (node.right == null) return node;
		return getMax(node.right);
	}

	private Node<K, T> getMin(Node<K, T> node) {
		if (node == null) return null;
		if (node.left == null) return node;
		return getMin(node.left);
	}

	public void deleteMax() {
		if (root != null) deleteMax(root);
	}

	public void deleteMin() {
		if (root != null) deleteMin(root);
	}

	private Node<K, T> deleteMax(Node<K, T> node) {
		if (node == null) return null;

		node.right = deleteMax(node.right);
		if (node.right == null) return node.left;
		return node;
	}

	private Node<K, T> deleteMin(Node<K, T> node) {
		if (node == null) return null;

		node.left = deleteMin(node.left);
		if (node.left == null) return node.right;
		return node;
	}

	/* public T delete(K key) { */
	/* 	return delete(root, key); */
	/* } */

	/* private T delete(Node<K, T> node, K key) { */
	/* 	if (node == null) return null; */
        /*  */
	/* 	int compare = key.compareTo(node.key); */
        /*  */
	/* 	if (compare < 0) return delete(node.left, key); */
	/* 	if (compare > 0) return delete(node.right, key); */
        /*  */
	/* 	return node.value; */
	/* } */
}
