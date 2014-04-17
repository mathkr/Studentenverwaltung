public class Sort {
	public static <T extends Comparable<T>> boolean isSorted(SimpleCollection<T> col){
		for(int i = 0; i < col.size() - 1; ++i){
			if(col.get(i).compareTo(col.get(i + 1)) > 0) return false;
		}

		return true;
	}

	public static <T> SimpleCollection<T> copyCollection (
			SimpleCollection<T> toCopy){
		SimpleCollection<T> res = new Vector<T>(toCopy.size());
		for(int i = 0; i < toCopy.size(); ++i){
			res.push_back(toCopy.get(i));
		}

		return res;
	}

	public static <T> void shuffle(SimpleCollection<T> col){
		// fisher & yates shuffling algorithm
		for(int i = col.size() - 1; i > 0; --i){
			int j = (int)(Math.random() * (i + 1));
			swap(col, j, i);
		}
	}

	private static <T> void swap(SimpleCollection<T> col, int a, int b){
		T tmp = col.get(a);
		col.set(a, col.get(b));
		col.set(b, tmp);
	}

	private static <T extends Comparable<T>> int max(SimpleCollection<T> col){
		int max = 0;

		for(int i = 1; i < col.size(); ++i){
			if(col.get(i).compareTo(col.get(max)) > 0) max = i;
		}

		return max;
	}

	/*****************************/

	public static <T extends Comparable<T>> void shellsort(SimpleCollection<T> col){
		int dist = 1;
		while(dist <= col.size() / 9){
			dist = dist * 3 + 1;
		}

		while(dist != 0){
			for(int i = dist; i < col.size(); ++i){
				T iValue = col.get(i);
				int j = i;

				while(j >= dist && col.get(j - dist).compareTo(iValue) > 0){
					col.set(j, col.get(j - dist));
					j -= dist;
				}
				col.set(j, iValue);
			}

			dist /= 3;
		}
	}

	/*****************************/

	public static <T extends Comparable<T>> void quicksort_parallel(SimpleCollection<T> col){
		shuffle(col);
		swap(col, max(col), col.size() - 1);
		ConcurrentQuicksort.sort(col);
	}

	public static <T extends Comparable<T>> void quicksort(SimpleCollection<T> col){
		shuffle(col);
		swap(col, max(col), col.size() - 1);
		quicksort(col, 0, col.size() - 1);
	}

	private static <T extends Comparable<T>> void quicksort(SimpleCollection<T> col, int left, int right){
		// check for break condition
		if(left >= right) return;

		// find pivot and position it on the left
		int mid = left + (right - left) / 2;
		swap(col, left, median_of_three(col, left, mid, right));

		// partition
		mid = partition(col, left, left, right + 1);

		// repeat recursively
		quicksort(col, left, mid - 1 );
		quicksort(col, mid + 1, right);
	}

	private static <T extends Comparable<T>> int partition(SimpleCollection<T> col, int pivot, int left, int right){
		T piv = col.get(pivot);

		while(true){
			while(col.get(++left).compareTo(piv) < 0);
			while(col.get(--right).compareTo(piv) > 0);

			if(left >= right) break;
			swap(col, left, right);
		}

		swap(col, pivot, right);
		return right;
	}

	public static <T extends Comparable<T>> int median_of_three(SimpleCollection<T> col, int aIndex, int bIndex, int cIndex){
		T a = col.get(aIndex);
		T b = col.get(bIndex);
		T c = col.get(cIndex);

		if(a.compareTo(b) > 0){
			if(b.compareTo(c) > 0){
				return bIndex;
			} else if(a.compareTo(c) > 0){
				return cIndex;
			} else {
				return aIndex;
			}
		} else if(a.compareTo(c) > 0){
			return aIndex;
		} else if(b.compareTo(c) > 0){
			return cIndex;
		} else {
			return bIndex;
		}
	}

	/*****************************/

	public static <T extends Comparable<T>>  void mergesort(SimpleCollection<T> col){
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

	/*****************************/

	public static <T extends Comparable<T>> void insertionsort(SimpleCollection<T> col){
		for(int i = 1; i < col.size(); ++i){
			T iValue = col.get(i);
			int j = i;

			while(j >= 1 && iValue.compareTo(col.get(j - 1)) > 0){
				col.set(j, col.get(j - 1));
				--j;
			}
			col.set(j, iValue);
		}
	}
}
