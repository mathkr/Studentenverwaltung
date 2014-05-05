package studiverwaltung;

import studiverwaltung.util.structures.SimpleCollection;
import plotter.RPNExpression;
import plotter.PlotterFrame;

public class Main {
 	public static void main(String[] args) {
		if (args.length >= 3) {
			RPNExpression[] rpns =
			    new RPNExpression[args.length - 2];
			for (int i = 2; i < args.length; ++i) {
				rpns[i - 2] = new RPNExpression(args[i]);
			}

			new PlotterFrame(Double.parseDouble(args[0]),
			    Double.parseDouble(args[1]), rpns);
		} else {
			String usage = "USAGE: plot min max rpn-expressions...";
			System.out.println(usage);
		}
	}

	private static void printCollection(SimpleCollection col) {
		for (int i = 0; i < col.size(); ++i) {
			System.out.print(col.get(i) + "\t");
		}
		System.out.println();
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}
}
