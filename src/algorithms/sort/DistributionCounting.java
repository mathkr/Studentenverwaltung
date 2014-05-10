/* Programming assignments for 'Programmieren I + II' at the
 * Hochschule Bremerhaven, GERMANY.
 *
 * Copyright (C) 2014 Matthis Krause
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package algorithms.sort;

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
