package studiverwaltung.util.sort;

import java.util.concurrent.*;

import studiverwaltung.util.IncrementLatch;
import studiverwaltung.util.structures.SimpleCollection;

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
		shuffle(col);
		swap(col, maxIndex(col), col.size() - 1);
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

	private static <T> void shuffle(SimpleCollection<T> col) {
		/* fisher & yates shuffling algorithm */
		for (int i = col.size() - 1; i > 0; --i) {
			int j = (int)(Math.random() * (i + 1));
			swap(col, j, i);
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
		swap(col, left, median_of_three(col, left, mid, right));

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

		while(true){
			while(col.get(++left ).compareTo(piv) < 0);
			while(col.get(--right).compareTo(piv) > 0);

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

	private static <T> void swap(SimpleCollection<T> col, int a, int b) {
		T tmp = col.get(a);
		col.set(a, col.get(b));
		col.set(b, tmp);
	}
}


