package algorithms.tests;

import java.util.ArrayList;

import algorithms.sort.Sort;
import algorithms.structures.SimpleCollection;

public class SortTest {
	private TestableSort testableSort;
	private String name;

	private ArrayList<Long> times;

	private SimpleCollection<Integer> col;

	public SortTest(TestableSort testableSort,
			String name,
			SimpleCollection<Integer> col){

		this.testableSort = testableSort;
		this.name = name;
		this.times = new ArrayList<Long>();
		this.col = col;
	}

	public long test(){
		SimpleCollection<Integer> newCol = Sort.copyCollection(col);

		long before = System.currentTimeMillis();
		testableSort.sort(newCol);
		long after = System.currentTimeMillis();
		long time = after - before;

		boolean hasSorted = Sort.isSorted(newCol);

		if(hasSorted){
			times.add(time);
		} else {
			System.out.println(this + ": incorrect sort");
		}

		return time;
	}

	public TestableSort getTestableSort(){
		return testableSort;
	}

	public double getAverage(){
		double res = 0;
		for(int i = 0; i < times.size(); ++i){
			res += times.get(i);
		}
		
		return (res == 0) ? 0 : res / times.size();
	}

	public double getCorrectedAverage(){
		double res = 0;
		long min = (times.size() > 0) ? times.get(0) : 0;
		long max = (times.size() > 0) ? times.get(0) : 0;

		for(int i = 0; i < times.size(); ++i){
			long val = times.get(i);
			min = (min < val) ? min : val;
			max = (max > val) ? max : val;
			res += val;
		}

		res -= min;
		res -= max;

		return (res == 0) ? 0 : res / times.size();
	}

	@Override
	public String toString(){
		return testableSort + " , " + name;
	}
}
