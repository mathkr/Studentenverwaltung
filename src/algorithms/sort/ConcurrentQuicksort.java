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

import java.util.concurrent.*;

import algorithms.structures.SimpleCollection;
import algorithms.util.IncrementLatch;

public class ConcurrentQuicksort<T extends Comparable<T>> implements Runnable {
	private SimpleCollection<T> col;
	private int left;
	private int right;
	private int minArraySize;

	private IncrementLatch threadCountDown;

	private static final int MAX_THREADS =
	    Runtime.getRuntime().availableProcessors();

	private static final ExecutorService THREADPOOL =
	    Executors.newFixedThreadPool(MAX_THREADS);

	private ConcurrentQuicksort(SimpleCollection<T> col, int left,
	    int right, int minArraySize, IncrementLatch threadCountDown)
	{
		this.col = col;
		this.left = left;
		this.right = right;
		this.minArraySize = minArraySize;
		this.threadCountDown = threadCountDown;
	}

	public static <T extends Comparable<T>> void sort(
	    SimpleCollection<T> col)
	{
		int max = maxIndex(col);
		T tmp = col.get(max);
		col.set(max, col.get(col.size() - 1));
		col.set(col.size() - 1, tmp);

		beginSort(col);
	}

	private static <T extends Comparable<T>> int maxIndex(
	    SimpleCollection<T> col)
	{
		int max = 0;

		for (int i = 1; i < col.size(); ++i){
			if (col.get(i).compareTo(col.get(max)) > 0) max = i;
		}

		return max;
	}

	private static <T extends Comparable<T>> void beginSort(
	    SimpleCollection<T> col)
	{
		IncrementLatch allDone = new IncrementLatch(1);

		new ConcurrentQuicksort<T>(col, 0, col.size() - 1,
		    col.size() / MAX_THREADS, allDone)
		    .quicksort(0, col.size() - 1, true);

		try {
			allDone.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		quicksort(left, right, true);
	}

	private void quicksort(int left, int right, boolean initialCall) {
		/* check for break condition */
		if (left >= right) {
			if (initialCall) threadCountDown.decrement();
			return;
		}

		/* find pivot and position it on the left */
		int mid = left + (right - left) / 2;
		int pivot = median_of_three(col, left, mid, right);
		T tmp = col.get(left);
		col.set(left, col.get(pivot));
		col.set(pivot, tmp);

		/* partition */
		mid = partition(col, left, left, right + 1);

		/* repeat recursively */
		if ((mid - 1) - left >= minArraySize) {
			threadCountDown.increment();
			THREADPOOL.submit(new ConcurrentQuicksort<T>(col, left,
			    mid - 1, minArraySize, threadCountDown));

			quicksort(mid + 1, right, false);
		} else {
			quicksort(left, mid - 1, false);
			quicksort(mid + 1, right, false);
		}

		if (initialCall) threadCountDown.decrement();
	}

	private static <T extends Comparable<T>> int partition(
	    SimpleCollection<T> col, int pivot, int left, int right)
	{
		T piv = col.get(pivot);
		T tmp = null;

		while(true){
			while(col.get(++left ).compareTo(piv) < 0);
			while(col.get(--right).compareTo(piv) > 0);

			if (left >= right) break;

			tmp = col.get(left);
			col.set(left, col.get(right));
			col.set(right, tmp);
		}

		tmp = col.get(pivot);
		col.set(pivot, col.get(right));
		col.set(right, tmp);

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


