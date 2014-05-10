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

package algorithms.sort;

import algorithms.structures.SimpleCollection;

public class Shellsort {
	public static <T extends Comparable<T>> void sort(SimpleCollection<T> col){
		int dist = 1;
		while(dist <= col.size() / 9){
			dist = dist * 3 + 1;
		}

		while(dist != 0){
			for(int i = dist; i < col.size(); ++i){
				T iValue = col.get(i);
				int j = i;

				while(j >= dist && col.get(j - dist).compareTo(iValue) > 0){
					col.set(j, col.get(j - dist));
					j -= dist;
				}
				col.set(j, iValue);
			}

			dist /= 3;
		}
	}
}
