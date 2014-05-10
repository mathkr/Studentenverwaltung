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

public class Vector<T> implements SimpleCollection<T> {
	private static final int DEFAULT_CAPACITY = 10;

	private Object[] elements;
	private int capacityIncrement;
	private int size;

	public Vector(int initCapacity, int capacityIncrement) {
		if (capacityIncrement > 0) {
			this.capacityIncrement = capacityIncrement;
		} else {
			this.capacityIncrement = 0;
		}

		elements = new Object[initCapacity];
		size = 0;
	}

	public Vector(int initCapacity) {
		this(initCapacity, 0);
	}

	public Vector() {
		this(DEFAULT_CAPACITY);
	}

	public int size() {
		return size;
	}

	public void push_back(T arg) {
		add(arg);
	}

	public void push_front(T arg) {
		if (size >= elements.length) {
			resize();
		}

		for (int i = size; i > 0; --i) {
			elements[i] = elements[i - 1];
		}

		elements[0] = arg;
		++size;
	}

	public void delete(int i) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Index: " + i +
			    ", Size: " + size);
		} else {
			for (;i < size - 1; ++i) {
				elements[i] = elements[i + 1];
			}
			elements[--size] = null;
		}
	}

	public void add(T arg) {
		if (size >= elements.length) {
			resize();
		}

		elements[size++] = arg;
	}

	@SuppressWarnings("unchecked")
	public T get(int i) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Index: " + i +
			    ", Size: " + size);
		} else {
			return (T) elements[i];
		}
	}

	public void set(int i, T arg) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Index: " + i +
			    ", Size: " + size);
		} else {
			elements[i] = arg;
		}
	}

	private void resize() {
		int newSize = (capacityIncrement > 0) ?
		    elements.length + capacityIncrement : 2 * elements.length;
		Object[] newElements = new Object[newSize];

		for (int i = 0; i < elements.length; ++i) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
}
