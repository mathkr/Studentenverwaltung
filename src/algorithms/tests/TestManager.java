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

package algorithms.tests;

import java.util.ArrayList;

import algorithms.sort.ConcurrentQuicksort;
import algorithms.sort.Heapsort;
import algorithms.sort.Mergesort;
import algorithms.sort.Quicksort;
import algorithms.sort.Shellsort;
import algorithms.structures.SimpleCollection;
import algorithms.structures.Vector;

public class TestManager {
	private TestableSort quick = new TestableSort() {
		public void sort(SimpleCollection<Integer> col) {
			Quicksort.sort(col);
		}

		@Override
		public String toString(){ return "quicksort"; }
	};

	private TestableSort concurrentQuick = new TestableSort() {
		public void sort(SimpleCollection<Integer> col) {
			ConcurrentQuicksort.sort(col);
		}

		@Override
		public String toString(){ return "concurrent quicksort"; }
	};

	private TestableSort merge = new TestableSort() {
		public void sort(SimpleCollection<Integer> col) {
			Mergesort.sort(col);
		}

		@Override
		public String toString(){ return "mergesort"; }
	};

	private TestableSort shell = new TestableSort() {
		public void sort(SimpleCollection<Integer> col) {
			Shellsort.sort(col);
		}

		@Override
		public String toString(){ return "shellsort"; }
	};

	private TestableSort heap = new TestableSort() {
		public void sort(SimpleCollection<Integer> col) {
			Heapsort.sort(col);
		}

		@Override
		public String toString(){ return "heapsort"; }
	};

	private ArrayList<SortTest> tests;
	private static TestManager testManager;

	private static final int HIGH_DIVERSITY = 100000;
	private static final int LOW_DIVERSITY = 8;
	private static final int CONSTANT_VALUE = 5;

	private int testCount;

	private TestManager() {
		System.out.println("preparing sort data...");

 		Vector<Integer> rnd_500_hi = getRandomVector(500,
		    HIGH_DIVERSITY);

		Vector<Integer> rnd_50k_hi = getRandomVector(50000,
		    HIGH_DIVERSITY);

		Vector<Integer> rnd_1mi_hi = getRandomVector(1000000,
		    HIGH_DIVERSITY);

 		Vector<Integer> rnd_5mi_hi = getRandomVector(5000000,
		    HIGH_DIVERSITY);

		Vector<Integer> cst_50k = getConstantVector(50000,
		    CONSTANT_VALUE);

		Vector<Integer> asc_500 = getSortedVector(500, true);
		Vector<Integer> asc_50k = getSortedVector(50000, true);
		Vector<Integer> asc_1mi = getSortedVector(1000000, true);
		Vector<Integer> asc_5mi = getSortedVector(5000000, true);

		Vector<Integer> dsc_500 = getSortedVector(500, false);
 		Vector<Integer> dsc_50k = getSortedVector(50000, false);
		Vector<Integer> dsc_1mi = getSortedVector(1000000, false);
		Vector<Integer> dsc_5mi = getSortedVector(5000000, false);

		ArrayList<TestableSort> testableSorts =
		    new ArrayList<TestableSort>();

		testableSorts.add(quick);
		testableSorts.add(concurrentQuick);
		testableSorts.add(merge);
		testableSorts.add(shell);
		testableSorts.add(heap);

		tests = new ArrayList<SortTest>();

		for (TestableSort ts : testableSorts) {
 			tests.add(new SortTest(ts, "rnd 500 high", rnd_500_hi));
			tests.add(new SortTest(ts, "rnd 50k high", rnd_50k_hi));
			tests.add(new SortTest(ts, "rnd 1mi high", rnd_1mi_hi));
 			tests.add(new SortTest(ts, "rnd 5mi high", rnd_5mi_hi));

 			tests.add(new SortTest(ts, "constant 50k", cst_50k));

			tests.add(new SortTest(ts, "asc 500     ", asc_500));
			tests.add(new SortTest(ts, "asc 50k     ", asc_50k));
			tests.add(new SortTest(ts, "asc 1mi     ", asc_1mi));
			tests.add(new SortTest(ts, "asc 5mi     ", asc_5mi));

			tests.add(new SortTest(ts, "dsc 500     ", dsc_500));
 			tests.add(new SortTest(ts, "dsc 50k     ", dsc_50k));
			tests.add(new SortTest(ts, "dsc 1mi     ", dsc_1mi));
			tests.add(new SortTest(ts, "dsc 5mi     ", dsc_5mi));
		}

		testCount = 0;
	}

	public void runTests(int num) {
		System.out.println("running tests:");
		for (int i = 0; i < num; ++i) {
			for (int j = 0; j < tests.size(); ++j) {
				System.out.printf("starting test %04d: " +
				    tests.get(j) + "%n", testCount++);
				tests.get(j).test();
			}
		}
		System.out.println("finished tests");
	}

	public void printTests() {
		for (int i = 0; i < tests.size(); ++i) {
			System.out.println(tests.get(i) + ": " +
			    tests.get(i).getAverage() + "ms");
		}
	}

	private Vector<Integer> getSortedVector(int length, boolean ascending) {
		Vector<Integer> res = new Vector<Integer>(length);
		for (int i = 0; i < length; ++i) {
			res.push_back(ascending ? i : length - i);
		}
		return res;
	}

	private Vector<Integer> getConstantVector(int length, int constant) {
		Vector<Integer> res = new Vector<Integer>(length);
		for (int i = 0; i < length; ++i) {
			res.push_back(constant);
		}
		return res;
	}

	private Vector<Integer> getRandomVector(int length, int values) {
		Vector<Integer> res = new Vector<Integer>(length);
		for (int i = 0; i < length; ++i) {
			res.push_back((int) (Math.random() * values));
		}
		return res;
	}

	public static TestManager getTestManager() {
		if (testManager == null) {
			testManager = new TestManager();
		}

		return testManager;
	}
}
