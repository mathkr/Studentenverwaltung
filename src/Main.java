public class Main {
 	public static void main(String[] args){
		int[] a = new int[10];
		for(int i = 0; i < a.length; ++i){
			a[i] = 44 - (int)(Math.random() * 90);
		}

		printArray(a);

		util.sort.DistributionCounting.sort(a);

		printArray(a);
	}

	private static void printArray(int[] a){
		for(int i = 0; i < a.length; ++i){
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}
}
