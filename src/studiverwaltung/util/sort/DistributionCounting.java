package studiverwaltung.util.sort;

public class DistributionCounting {
	public static void sort(int[] array){
		if(array.length < 2) return;

		int min = array[0];
		int max = array[0];

		for(int i = 0; i < array.length; ++i){
			min = min > array[i] ? array[i] : min;
			max = max > array[i] ? max : array[i];
		}

		System.out.println("min=" + min + ", max=" + max + ", range=" + (max - min + 1) + ", offset=" + (-min));
		sort(array, max - min + 1, -min);
	}

	private static void sort(int[] array, int range, int offset){
		int[] count = new int[range];

		for(int i = 0; i < array.length; ++i){
			++count[offset + array[i]];
		}

		int i = 0;
		for(int j = 0; j < count.length; ++j){
			for(int k = 0; k < count[j]; ++k){
				array[i++] = j - offset;
			}
		}
	}

}
