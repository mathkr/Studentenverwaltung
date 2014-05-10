package algorithms.sort;

import algorithms.structures.SimpleCollection;

public class Shellsort {
	public static <T extends Comparable<T>> void sort(SimpleCollection<T> col){
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
}
