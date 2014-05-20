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
