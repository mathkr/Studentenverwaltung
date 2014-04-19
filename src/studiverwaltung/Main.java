package studiverwaltung;

import studiverwaltung.util.structures.BinarySearchTree;

public class Main {
 	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bst =
		    new BinarySearchTree<Integer, Integer>();

		Integer[] values = new Integer[1000];
		for (int i = 0; i < values.length; ++i) {
			values[i] = (int)(Math.random() * 50000);
		}

		System.out.println("isValid = " + isValid(bst, values));
		System.out.println("get(5) = " + bst.get(5));

		System.out.println("max = " + bst.getMax());
		bst.deleteMax();
		System.out.println("newmax = " + bst.getMax());

		System.out.println("min = " + bst.getMin());
		bst.deleteMin();
		System.out.println("newmin = " + bst.getMin());
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
