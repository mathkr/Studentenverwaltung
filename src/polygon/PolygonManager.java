package polygon;

import java.awt.Color;

public class PolygonManager {
	public static void main (String[] args) {
		new PolygonManager();
	}

	private PolygonFrame polygonFrame;
	private RandomPolygon polygon;

	public PolygonManager() {
		polygon = new RandomPolygon(3);
		polygonFrame = new PolygonFrame(polygon, Color.BLACK,
		    Color.WHITE);

		new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(500);
					polygon.addRandomPoints(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
