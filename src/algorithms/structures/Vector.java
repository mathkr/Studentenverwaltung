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

	protected Object[] elements;
	private int capacityIncrement;
	private int size;
	private int nextFree;
	private int head;

	public Vector(int initCapacity, int capacityIncrement) {
		this.capacityIncrement = capacityIncrement > 0 ?
		    capacityIncrement : 0;

		elements = new Object[initCapacity];
		size = head = nextFree = 0;
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

	public void push_front(T arg) {
		if ((head - 1) < 0) {
			resize_front();
		}

		elements[--head] = arg;
		++size;
	}

	public void push_back(T arg) {
		if (nextFree >= elements.length) {
			resize();
		}

		elements[nextFree++] = arg;
		++size;
	}

	public void delete(int i) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Index: " + i +
			    ", Size: " + size);
		} else {
			for (;i < size - 1; ++i) {
				elements[head + i] = elements[head + i + 1];
			}
			elements[--nextFree] = null;
			--size;
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int i) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Index: " + i +
			    ", Size: " + size);
		} else {
			return (T) elements[head + i];
		}
	}

	public void set(int i, T arg) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Index: " + i +
			    ", Size: " + size);
		} else {
			elements[head + i] = arg;
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

	private void resize_front() {
		int oldSize = elements.length;
		int newSize = (capacityIncrement > 0) ?
		    oldSize + capacityIncrement : 2 * oldSize;
		Object[] newElements = new Object[newSize];

		for (int i = 0; i < oldSize; ++i) {
			newElements[(newSize - oldSize) + i] = elements[i];
		}
		elements = newElements;
		head = newSize - oldSize;
		nextFree = head + size;
	}
}
