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

import java.awt.Color;

public class PolygonManager {
	public static void main (String[] args) {
		new PolygonManager();
	}

	private PolygonFrame polygonFrame;
	private RandomPolygon polygon;
	private GeneratorThread generatorThread;

	private class GeneratorThread extends Thread {
			private boolean paused = false;
			private int sleepTime;

			GeneratorThread(int sleepTime) {
				this.sleepTime = sleepTime;
			}

			@Override
			public void run() {
				while (true) {
					while (!paused) {
						addPoint();

						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			public void addPoint() {
				polygon.addRandomPoints(1);
			}

			public void pause() {
				this.paused = true;
			}

			public void unpause() {
				this.paused = false;
			}
	}

	public PolygonManager() {
		polygon = new RandomPolygon(3);
		polygonFrame = new PolygonFrame(polygon, this, Color.BLACK,
		    Color.WHITE);

		generatorThread = new GeneratorThread(500);

		generatorThread.start();
	}

	public void pauseGenerator() {
		generatorThread.pause();
	}

	public void unpauseGenerator() {
		generatorThread.unpause();
	}
}
