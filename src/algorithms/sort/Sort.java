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
import algorithms.structures.Vector;

public class Sort {
	public static <T extends Comparable<T>> boolean isSorted(
	    SimpleCollection<T> col)
	{
		for (int i = 0; i < col.size() - 1; ++i) {
			if (col.get(i).compareTo(col.get(i + 1)) > 0)
				return false;
		}

		return true;
	}

	public static <T> SimpleCollection<T> copyCollection(
	    SimpleCollection<T> toCopy)
	{
		SimpleCollection<T> res = new Vector<T>(toCopy.size());
		for (int i = 0; i < toCopy.size(); ++i) {
			res.push_back(toCopy.get(i));
		}

		return res;
	}
}
