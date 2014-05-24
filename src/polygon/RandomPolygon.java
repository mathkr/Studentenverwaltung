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

import java.util.List;
import java.util.ArrayList;
import util.*;

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
