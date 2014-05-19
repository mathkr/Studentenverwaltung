package polygon;

import java.util.List;
import java.util.ArrayList;

public class RandomPolygon implements Observable {
	private List<Double> pointsX;
	private List<Double> pointsY;

	private List<Observer> observers;

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.notifyObserver();
		}
	}

	public RandomPolygon(int initialSize) {
		observers = new ArrayList<Observer>();
		this.pointsX = new ArrayList<Double>(initialSize);
		this.pointsY = new ArrayList<Double>(initialSize);

		addRandomPoints(initialSize);
	}

	public void addRandomPoints(int count) {
		for (int i = 0; i < count; ++i) {
			double x = getRandomValue(0, 1.0);
			double y = getRandomValue(0, 1.0);
			pointsX.add(x);
			pointsY.add(y);
		}

		notifyObservers();
	}

	private double getRandomValue(double min, double max) {
		double x = min + Math.random() * (max - min);
		return x;
	}

	public Double[] getPointsX() {
		return pointsX.toArray(new Double[0]);
	}

	public Double[] getPointsY() {
		return pointsY.toArray(new Double[0]);
	}

	public int getSize() {
		return pointsX.size();
	}
}
