package studiverwaltung.util.sort;

import studiverwaltung.util.structures.SimpleCollection;

public class Heapsort {
	public static <T extends Comparable<T>> void sort(
	    SimpleCollection col)
	{
		for (int i = parent(col.size() - 1); i >= 0; --i) {
			sink(col, i, col.size() - 1);
		}

		for (int i = col.size() - 1; i > 0; --i) {
			swap(col, 0, i);
			sink(col, 0, i - 1);
		}
	}

	private static <T extends Comparable<T>> void sink(
	    SimpleCollection<T> col, int x, int to)
	{
		int c = 0;

		if (lChild(x) > to) return;
		if (rChild(x) > to) c = lChild(x);
		else {
			T lc = col.get(lChild(x));
			T rc = col.get(rChild(x));

			c = lc.compareTo(rc) > 0 ? lChild(x) : rChild(x);
		}
		
		if (col.get(c).compareTo(col.get(x)) > 0) swap(col, x, c);
		sink(col, c, to);
	}

	private static <T> void swap(SimpleCollection<T> col, int a, int b) {
		T tmp = col.get(a);
		col.set(a, col.get(b));
		col.set(b, tmp);
	}

	private static int parent(int i) {
		return (i - 1) / 2;
	}

	private static int lChild(int i) {
		return i * 2 + 1;
	}

	private static int rChild(int i) {
		return i * 2 + 2;
	}
}
