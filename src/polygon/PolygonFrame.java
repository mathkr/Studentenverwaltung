package polygon;

import java.awt.*;
import java.awt.event.*;

public class PolygonFrame extends Frame implements Observer {
	private RandomPolygon polygon;
	private Color fgColor;

	public PolygonFrame(RandomPolygon polygon, Color bgColor,
	    Color fgColor)
	{
		super("Polygon");

		setBackground(bgColor);
		setForeground(fgColor);

		setBounds(100, 100, 400, 400);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});

		this.polygon = polygon;
		polygon.addObserver(this);

		setVisible(true);
	}

	public void notifyObserver() {
		repaint();
	}

	public void paint(Graphics g) {
		int[] pointsX = toWindowCoords(polygon.getPointsX(), true);
		int[] pointsY = toWindowCoords(polygon.getPointsY(), false);
		int length = polygon.getSize();

		Polygon p = new Polygon(pointsX, pointsY, length);
		g.fillPolygon(p);
	}

	private int[] toWindowCoords(Double[] points, boolean xAxis) {
		int[] res = new int[points.length];

		for (int i = 0; i < points.length; ++i) {
			res[i] = xAxis ? (int)(getWidth() * points[i]) :
			    (int)(getHeight() * points[i]);
		}

		return res;
	}
}
