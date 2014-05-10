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

public class HashTable<K, T> {
	private final static int[] PRIMESIZES = {31, 61, 127, 251, 509, 1021, 2039,
	    4093, 8191, 16381, 32749, 65521, 131071, 262139, 524287, 1048573,
	    2097143, 4194301, 8388593, 16777213, 33554393, 67108859, 134217689,
	    268435399, 536870909, 1073741789, 2147483647};

	private Node<K, T>[] nodes;
	private int filled;
	private int nodesSizeIndex;
	private Hasher<K> hasher;

	@SuppressWarnings("unchecked")
	public HashTable(Hasher<K> hasher) {
		nodesSizeIndex = 0;
		nodes = new Node[PRIMESIZES[nodesSizeIndex]];
		filled = 0;
		this.hasher = hasher;
	}

	public HashTable() {
		this(new Hasher<K>() {
			public int hash(K key) {
				return key.hashCode() & 0x7FFFFFFF;
			}
		});
	}

	public void put(K key, T value) {
		if (filled > nodes.length / 2) {
			enlarge();
		}

		int index = hash(key) % nodes.length;
		while (nodes[index] != null && !nodes[index].key.equals(key)) {
			++index;
			index %= nodes.length;
		}
		nodes[index] = new Node<K, T>(key, value);
		++filled;
	}

	public T get(K key) {
		int index = hash(key) % nodes.length;
		while (nodes[index] != null) {
			if (nodes[index].key.equals(key)) {
				return nodes[index].val;
			}
			++index;
			index %= nodes.length;
		}
		return null;
	}

	public T remove(K key) {
		T res = null;
		int index = hash(key) % nodes.length;
		while (nodes[index] != null) {
			if (nodes[index].key.equals(key)) {
				res = nodes[index].val;
				break;
			}
			++index;
			index %= nodes.length;
		}

		if (res != null) {
			nodes[index] = null;
			--filled;
			while (nodes[++index] != null) {
				Node<K, T> tmp = nodes[index];
				nodes[index] = null;
				--filled;
				put((K)tmp.key, (T)tmp.val);
			}
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	private void enlarge() {
		Node<K, T>[] newNodes = new Node[PRIMESIZES[++nodesSizeIndex]];
		for (int i = 0; i < nodes.length; ++i) {
			if (nodes[i] != null) {
				int index = hash(nodes[i].key) %
				    newNodes.length;

				while (newNodes[index] != null) {
					++index;
					index %= newNodes.length;
				}
				newNodes[index] = nodes[i];
			}
		}
		nodes = newNodes;
	}

	private int hash(K key) {
		return hasher.hash(key);
	}

	private class Node<Key, Value> {
		public Key key;
		public Value val;

		public Node(Key key, Value value) {
			this.key = key;
			this.val = value;
		}
	}
}
