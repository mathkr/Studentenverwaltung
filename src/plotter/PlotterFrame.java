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

	private static final Color[] COLORS = { Color.BLACK, Color.BLUE,
	    Color.GREEN, new Color(0.5f, 0.5f, 0.5f), Color.CYAN.darker(),
	    Color.MAGENTA };


	private WindowListener winListener = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
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

	@SuppressWarnings("unchecked")
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
		printScale(g);

		for (int i = 0; i < points.length; ++i) {
			g.setColor(COLORS[i % COLORS.length]);

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

	private void printScale(Graphics g) {
		g.setColor(Color.RED);

		g.drawLine(getX(min), getY(0), getX(max), getY(0));
		g.drawLine(getX(0), getY(min), getX(0), getY(max));

		double scaleWidth = 0.125;

		for (int i = 1; i < (int)Math.ceil(max - min); ++i) {
			g.drawLine(getX(-scaleWidth), getY(i),
			    getX(scaleWidth), getY(i));
			g.drawLine(getX(-scaleWidth), getY(-i),
			    getX(scaleWidth), getY(-i));

			g.drawLine(getX(-scaleWidth/2), getY(i - 0.5),
			    getX(scaleWidth/2), getY(i - 0.5));
			g.drawLine(getX(-scaleWidth/2), getY(-i + 0.5),
			    getX(scaleWidth/2), getY(-i + 0.5));

			g.drawLine(getX(i), getY(scaleWidth),
			    getX(i), getY(-scaleWidth));
			g.drawLine(getX(-i), getY(scaleWidth),
			    getX(-i), getY(-scaleWidth));

			g.drawLine(getX(i - 0.5), getY(scaleWidth/2),
			    getX(i - 0.5), getY(-scaleWidth/2));
			g.drawLine(getX(-i + 0.5), getY(scaleWidth/2),
			    getX(-i + 0.5), getY(-scaleWidth/2));
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
