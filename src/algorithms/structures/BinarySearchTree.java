/* Programming assignments for 'Programmieren I + II' at the
 * Hochschule Bremerhaven, GERMANY.
 *
 * Copyright (C) 2014 Matthis Krause
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package algorithms.structures;

import java.util.ArrayList;

/**
 * A standard binary search tree.
 */
public class BinarySearchTree<K extends Comparable<K>, T> {
	private Node<K, T> root;
	private int size;

	private class Node<P extends Comparable<P>, Q> {
		public P key;
		public Q value;

		public Node<P, Q> left;
		public Node<P, Q> right;

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
			return new Node<K, T>(key, value);
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

	private Node<K, T> deleteMax(Node<K, T> node) {
		if (node.right == null) return node.left;
		node.right = deleteMax(node.right);
		return node;
	}

	private Node<K, T> deleteMin(Node<K, T> node) {
		if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		return node;
	}

	public ArrayList<T> getValues() {
		ArrayList<T> values = new ArrayList<T>(size);
		getValues(root, values);
		return values;
	}

	private void getValues(Node<K, T> node, ArrayList<T> values) {
		if (node == null) return;

		values.add(node.value);
		getValues(node.left, values);
		getValues(node.right, values);
	}

	public T delete(K key) {
		return root == null ? null : delete(root, key).value;
	}

	private Node<K, T> delete(Node<K, T> node, K key) {
		if (node == null) return null;

		int compare = key.compareTo(node.key);
		if 	(compare < 0) node.left  = delete(node.left, key);
		else if (compare > 0) node.right = delete(node.right, key);
		else {
			--size;
			if (node.right == null) return node.left;
			if (node.left == null) return node.right;

			Node<K, T> old = node;
			node = getMin(old.right);
			node.right = deleteMin(old.right);
			node.left = old.left;
			return node;
		}
		return node;
	}
}
