package plotter;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class PlotterFrame extends Frame {
	private class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private WindowListener winListener = new WindowListener() {
		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowClosed(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowOpened(WindowEvent e) {}
	};

	private static final double RES = 1000.0;
	private double min;
	private double max;
	private double step;

	private ArrayList<Point>[] points;

	public PlotterFrame(double min, double max, RPNExpression... rpns) {
		super("Plotter");
		setSize(500, 500);

		this.min = min;
		this.max = max;
		this.step = (max - min) / RES;

		points = createPoints(rpns);

		addWindowListener(winListener);
		setVisible(true);
	}

	private ArrayList<Point>[] createPoints(RPNExpression[] rpns) {
		ArrayList<Point>[] res = new ArrayList[rpns.length];

		for (int i = 0; i < rpns.length; ++i) {
			ArrayList<Point> points = new ArrayList<Point>();

			for (int j = (int)Math.floor(min / (max - min) * RES);
			     j <= (int)Math.ceil(max / (max - min)* RES);
			     ++j)
			{
				double x = 0 + j * step;
				points.add(new Point(x, rpns[i].solve(x)));
			}

			res[i] = points;
		}

		return res;
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.RED);
		g.drawLine(getX(min), getY(0), getX(max), getY(0));
		g.drawLine(getX(0), getY(min), getX(0), getY(max));

		g.setColor(Color.BLACK);
		for (int i = 0; i < points.length; ++i) {
			for (int j = 0; j < points[i].size() - 1; ++j) {
				Point a = points[i].get(j);
				Point b = points[i].get(j + 1);

				if (!Double.isNaN(a.y) &&
				    !Double.isNaN(b.y) &&
    				    !Double.isInfinite(a.y) &&
				    !Double.isInfinite(b.y))
				{
					g.drawLine(getX(a.x), getY(a.y),
							getX(b.x), getY(b.y));
				}
			}
		}
	}

	private int getX(double x) {
		Insets ins = getInsets();

		int width = getWidth() - ins.left - ins.right;

		double valueRange = max - min;
		double centerX = ins.left +
		    (width * (Math.abs(min) / valueRange));

		double halfaxisX = width - (centerX - ins.left);

		return (int)(centerX + (halfaxisX / max) * x);
	}

	private int getY(double y) {
		Insets ins = getInsets();

		int height = getHeight() - ins.top - ins.bottom;

		double valueRange = max - min;
		double centerY = getHeight() - ins.bottom -
		    (height * (Math.abs(min) / valueRange));

		double halfaxisY = centerY - ins.top;

		return (int)(centerY - (halfaxisY / max) * y);
	}
}
