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
