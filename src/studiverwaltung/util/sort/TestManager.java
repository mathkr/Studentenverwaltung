package studiverwaltung.util.sort;

import java.util.ArrayList;

import studiverwaltung.util.structures.SimpleCollection;
import studiverwaltung.util.structures.Vector;

public class TestManager {
	private TestableSort quick = new TestableSort() {
		public void sort(SimpleCollection<Integer> col){
			Quicksort.sort(col);
		}

		@Override
		public String toString(){ return "quicksort"; }
	};

	private TestableSort concurrentQuick = new TestableSort() {
		public void sort(SimpleCollection<Integer> col){
			ConcurrentQuicksort.sort(col);
		}

		@Override
		public String toString(){ return "concurrent quicksort"; }
	};

	private TestableSort merge = new TestableSort() {
		public void sort(SimpleCollection<Integer> col){
			Mergesort.sort(col);
		}

		@Override
		public String toString(){ return "mergesort"; }
	};

	private TestableSort shell = new TestableSort() {
		public void sort(SimpleCollection<Integer> col){
			Shellsort.sort(col);
		}

		@Override
		public String toString(){ return "shellsort"; }
	};

	private TestableSort heap = new TestableSort() {
		public void sort(SimpleCollection<Integer> col){
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
 		Vector<Integer> rnd_500_hi = getRandomVector(500, HIGH_DIVERSITY);
		Vector<Integer> rnd_50k_hi = getRandomVector(50000, HIGH_DIVERSITY);
		Vector<Integer> rnd_1mi_hi = getRandomVector(1000000, HIGH_DIVERSITY);
 		/* Vector<Integer> rnd_5mi_hi = getRandomVector(5000000, HIGH_DIVERSITY); */
		/* Vector<Integer> rnd_5mi_lo = getRandomVector(5000000, LOW_DIVERSITY); */

		Vector<Integer> cst_50k = getConstantVector(50000, CONSTANT_VALUE);

		Vector<Integer> asc_500 = getSortedVector(500, true);
		Vector<Integer> asc_50k = getSortedVector(50000, true);
		Vector<Integer> asc_1mi = getSortedVector(1000000, true);
		/* Vector<Integer> asc_5mi = getSortedVector(5000000, true); */

		Vector<Integer> dsc_500 = getSortedVector(500, false);
 		Vector<Integer> dsc_50k = getSortedVector(50000, false);
		Vector<Integer> dsc_1mi = getSortedVector(1000000, false);
		/* Vector<Integer> dsc_5mi = getSortedVector(5000000, false); */

		ArrayList<TestableSort> testableSorts = new ArrayList<TestableSort>();
		testableSorts.add(quick);
		testableSorts.add(concurrentQuick);
		testableSorts.add(merge);
		testableSorts.add(shell);
		testableSorts.add(heap);

		tests = new ArrayList<SortTest>();

		for(TestableSort ts : testableSorts){
 			tests.add(new SortTest(ts, "rnd 500 high", rnd_500_hi));
			tests.add(new SortTest(ts, "rnd 50k high", rnd_50k_hi));
			tests.add(new SortTest(ts, "rnd 1mi high", rnd_1mi_hi));
 			/* tests.add(new SortTest(ts, "rnd 5mi high", rnd_5mi_hi)); */
			/* tests.add(new SortTest(ts, "rnd 5mi  low", rnd_5mi_lo)); */

 			tests.add(new SortTest(ts, "constant 50k", cst_50k));

			tests.add(new SortTest(ts, "asc 500     ", asc_500));
			tests.add(new SortTest(ts, "asc 50k     ", asc_50k));
			tests.add(new SortTest(ts, "asc 1mi     ", asc_1mi));
			/* tests.add(new SortTest(ts, "asc 5mi     ", asc_5mi)); */

			tests.add(new SortTest(ts, "dsc 500     ", dsc_500));
 			tests.add(new SortTest(ts, "dsc 50k     ", dsc_50k));
			tests.add(new SortTest(ts, "dsc 1mi     ", dsc_1mi));
			/* tests.add(new SortTest(ts, "dsc 5mi     ", dsc_5mi)); */
		}

		testCount = 0;
	}

	public void runTests(int num){
		System.out.println("running tests:");
		for(int i = 0; i < num; ++i){
			for(int j = 0; j < tests.size(); ++j){
				System.out.printf("starting test %04d: " + tests.get(j) + "%n", testCount++);
				tests.get(j).test();
			}
		}
		System.out.println("finished tests");
	}

	public void printTests(){
		for(int i = 0; i < tests.size(); ++i){
			System.out.println(tests.get(i) + ": " + tests.get(i).getAverage() + "ms");
		}
	}

	private Vector<Integer> getSortedVector(int length, boolean ascending){
		Vector<Integer> res = new Vector<Integer>(length);
		for(int i = 0; i < length; ++i){
			res.push_back(ascending ? i : length - i);
		}
		return res;
	}

	private Vector<Integer> getConstantVector(int length, int constant){
		Vector<Integer> res = new Vector<Integer>(length);
		for(int i = 0; i < length; ++i){
			res.push_back(constant);
		}
		return res;
	}

	private Vector<Integer> getRandomVector(int length, int values){
		Vector<Integer> res = new Vector<Integer>(length);
		for(int i = 0; i < length; ++i){
			res.push_back((int) (Math.random() * values));
		}
		return res;
	}

	public static TestManager getTestManager(){
		if(testManager == null){
			testManager = new TestManager();
		}

		return testManager;
	}
}
