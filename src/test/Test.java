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

package test;

import algorithms.structures.Vector;

class Test {
	public static void main (String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		
		for (int i = 9; i >= 0; --i) {
			v.push_front(i);
		}

		int size = v.size();
		for (int i = size; i < size * 2; ++i) {
			v.push_back(i);
		}

		for (int i = 0; i < v.size(); ++i) {
			System.out.println(v.get(i));
		}
	}
}
