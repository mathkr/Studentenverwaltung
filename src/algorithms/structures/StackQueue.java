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

public class StackQueue<T> extends List<T> {
	private boolean queue;

	public StackQueue(boolean queue) {
		super();
		this.queue = queue;
	}

	public void push(T object) {
		push_back(object);
	}

	public T pop() {
		T res = null;
		if (queue) {
			res = getHead();
			removeHead();
		} else {
			res = getTail();
			removeTail();
		}

		return res;
	}

	public T peek() {
		T res = null;
		if (queue) {
			res = getHead();
		} else {
			res = getTail();
		}
		return res;
	}
}
