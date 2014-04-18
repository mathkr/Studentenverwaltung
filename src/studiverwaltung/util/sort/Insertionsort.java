package studiverwaltung.util.sort;

import studiverwaltung.util.structures.SimpleCollection;

public class Insertionsort {
	public static <T extends Comparable<T>> void sort(SimpleCollection<T> col){
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
