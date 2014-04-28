package studiverwaltung;

import studiverwaltung.util.structures.SimpleCollection;

public class Main {
 	public static void main(String[] args) {
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
