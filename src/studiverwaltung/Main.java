package studiverwaltung;

import studiverwaltung.util.structures.Vector;
import studiverwaltung.util.structures.BinarySearchTree;
import studiverwaltung.util.structures.SimpleCollection;
import studiverwaltung.util.sort.Heapsort;

public class Main {
 	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 0; i < 10; ++i) {
			v.push_back((int)(Math.random() * 20));
		}
		printCollection(v);
		Heapsort.sort(v);
		printCollection(v);
	}

	private static <T extends Comparable<T>> boolean isValid(
	    BinarySearchTree<T, T> bst, T[] values)
	{
		T max = values[0], min = values[0];
		for (int i = 0; i < values.length; ++i) {
			max = max.compareTo(values[i]) > 0 ? max : values[i];
			min = min.compareTo(values[i]) < 0 ? min : values[i];
			bst.put(values[i], values[i]);
		}

		for (int i = 0; i < values.length; ++i) {
			if (!bst.get(values[i]).equals(values[i])) return false;
		}

		return bst.getMax().equals(max) && bst.getMin().equals(min);
	}

	private static void printCollection(SimpleCollection col) {
		for (int i = 0; i < col.size(); ++i) {
			System.out.print(col.get(i) + "\t");
		}
		System.out.println();
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}
}
