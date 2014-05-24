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
