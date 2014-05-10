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

public class Quicksort {
	public static <T extends Comparable<T>> void sort(
	    SimpleCollection<T> col)
	{
		shuffle(col);
		swap(col, maxIndex(col), col.size() - 1);
		quicksort(col, 0, col.size() - 1);
	}

	private static <T extends Comparable<T>> int maxIndex(
	    SimpleCollection<T> col)
	{
		int max = 0;

		for (int i = 1; i < col.size(); ++i) {
			if (col.get(i).compareTo(col.get(max)) > 0) max = i;
		}

		return max;
	}

	private static <T> void shuffle(SimpleCollection<T> col) {
		/* fisher & yates shuffling algorithm */
		for (int i = col.size() - 1; i > 0; --i) {
			int j = (int)(Math.random() * (i + 1));
			swap(col, j, i);
		}
	}

	private static <T extends Comparable<T>> void quicksort(
	    SimpleCollection<T> col, int left, int right)
	{
		/* check for break condition */
		if (left >= right) return;

		/* find pivot and position it on the left */
		int mid = left + (right - left) / 2;
		swap(col, left, median_of_three(col, left, mid, right));

		/* partition */
		mid = partition(col, left, left, right + 1);

		/* repeat recursively */
		quicksort(col, left, mid - 1 );
		quicksort(col, mid + 1, right);
	}

	private static <T> void swap(SimpleCollection<T> col, int a, int b) {
		T tmp = col.get(a);
		col.set(a, col.get(b));
		col.set(b, tmp);
	}

	private static <T extends Comparable<T>> int partition(
	    SimpleCollection<T> col, int pivot, int left, int right)
	{
		T piv = col.get(pivot);

		while (true) {
			while (col.get(++left).compareTo(piv) < 0);
			while (col.get(--right).compareTo(piv) > 0);

			if (left >= right) break;
			swap(col, left, right);
		}

		swap(col, pivot, right);
		return right;
	}

	private static <T extends Comparable<T>> int median_of_three(
	    SimpleCollection<T> col, int aIndex, int bIndex, int cIndex)
	{
		T a = col.get(aIndex);
		T b = col.get(bIndex);
		T c = col.get(cIndex);

		if (a.compareTo(b) > 0) {
			if (b.compareTo(c) > 0) {
				return bIndex;
			} else if (a.compareTo(c) > 0) {
				return cIndex;
			} else {
				return aIndex;
			}
		} else if (a.compareTo(c) > 0) {
			return aIndex;
		} else if (b.compareTo(c) > 0) {
			return cIndex;
		} else {
			return bIndex;
		}
	}
}
