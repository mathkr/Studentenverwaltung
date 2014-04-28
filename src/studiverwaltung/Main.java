package studiverwaltung;

import studiverwaltung.util.structures.HashTable;
import studiverwaltung.util.structures.BinarySearchTree;
import studiverwaltung.util.structures.SimpleCollection;

public class Main {
 	public static void main(String[] args) {
		int[] keys = new int[5];
		HashTable ht = new HashTable();
		int k = 31;
		for (int i = 0; i < keys.length; ++i) {
			keys[i] = k;
			k += 31;
			ht.put(keys[i], i);
			System.out.printf("key=%d, value=%d%n", keys[i], i);
			System.out.println(ht + "\n");
		}

		ht.put(5, 10);

		System.out.println("------------\n");

		for (int i = 0; i < keys.length; ++i) {
			System.out.printf("key: %d, value: %d%n",
			    keys[i], ht.get(keys[i]));
			System.out.println(ht + "\n");
			ht.remove(keys[i]);
		}

		System.out.println(ht);
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
