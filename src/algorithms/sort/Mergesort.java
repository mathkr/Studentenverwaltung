package algorithms.sort;

import algorithms.structures.SimpleCollection;

public class Mergesort {
	public static <T extends Comparable<T>>  void sort(SimpleCollection<T> col){
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[col.size()];

		mergesort(col, aux, 0, col.size() - 1);
	}

	private static <T extends Comparable<T>> void mergesort(SimpleCollection<T> col, T[] aux, int left, int right){
		if(left >= right) return;

		int mid = left + (right - left) / 2;

		// sort both halves
		mergesort(col, aux, left, mid);
		mergesort(col, aux, mid + 1, right);

		// merge the two if they are not already in order
		if(col.get(mid).compareTo(col.get(mid + 1)) > 0){
			merge(col, aux, left, mid, right);
		}
	}

	private static <T extends Comparable<T>> void merge(SimpleCollection<T> col, T[] aux, int left, int mid, int right){
		// copy the left half
		for(int k = left; k <= mid; ++k){
			aux[k] = col.get(k);
		}

		// copy the right half in reverse
		for(int k = 0; k < right - mid; ++k){
			aux[mid + 1 + k] = col.get(right - k);
		}

		// merge both back into col
		for(int k = left; left <= right; ++k){
			col.set(k, (aux[left].compareTo(aux[right]) < 0) ? aux[left++] : aux[right--]);
		}
	}
}
