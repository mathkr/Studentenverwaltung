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

package polygon;

import java.awt.*;
import java.awt.event.*;

public class PolygonFrame extends Frame implements Observer {
	private RandomPolygon polygon;
	private PolygonManager polyManager;
	private Color fgColor;

	public PolygonFrame(RandomPolygon polygon, PolygonManager polyManager,
	    Color bgColor, Color fgColor)
	{
		super("Polygon");

		this.polyManager = polyManager;

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

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 'p') {
					polyManager.pauseGenerator();
					printFrame();
					polyManager.unpauseGenerator();
				}
			}

		});

		this.polygon = polygon;
		polygon.addObserver(this);

		setVisible(true);
	}

	public void printFrame() {
		getToolkit().getPrintJob(this, "Polygon Plotter",
		    new JobAttributes(), new PageAttributes());
	}

	public void notifyObserver() {
		repaint();
	}

	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		int[] pointsX = toWindowCoords(polygon.getPointsX(), w);
		int[] pointsY = toWindowCoords(polygon.getPointsY(), h);
		int length = polygon.getSize();

		Polygon p = new Polygon(pointsX, pointsY, length);
		g.fillPolygon(p);
	}

	private int[] toWindowCoords(Double[] points, int windowDimension) {
		int[] res = new int[points.length];

		for (int i = 0; i < points.length; ++i) {
			res[i] = (int)(windowDimension * points[i]);
		}

		return res;
	}
}
