package studiverwaltung;

import studiverwaltung.util.structures.BinarySearchTree;

public class Main {
 	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bst =
		    new BinarySearchTree<Integer, Integer>();

		Integer[] values = new Integer[1000];
		for (int i = 0; i < values.length; ++i) {
			values[i] = (int)(Math.random() * 500);
		}

		System.out.println("isValid = " + isValid(bst, values));

		System.out.println("before put: get(100) = " + bst.get(100));
		bst.put(100,666);
		System.out.println("get(100) = " + bst.get(100));
		bst.delete(100);
		System.out.println("after delete: get(100) = " + bst.get(100));

		for (int i = 0; i < values.length; ++i) {
			Integer val = bst.get(values[i]);
			if (val == null || !val.equals(values[i])) {
				System.out.println(values[i] +
				    " is set incorrectly");
			}
		}
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

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}
}
