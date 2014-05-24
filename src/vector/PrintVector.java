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

package vector;

import java.util.List;
import java.util.ArrayList;
import algorithms.structures.Vector;
import algorithms.structures.SimpleCollection;
import util.*;

public class PrintVector<T> extends Vector<T> implements Observable {
	private List<Observer> observers;

	public PrintVector(int initCapacity, int capacityIncrement) {
		super(initCapacity, capacityIncrement);
		init();
	}

	public PrintVector(int initCapacity) {
		super(initCapacity);
		init();
	}

	public PrintVector() {
		super();
		init();
	}

	private void init() {
		this.observers = new ArrayList<Observer>();
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public T[] getArray() {
		return (T[]) elements;
	}

	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.notifyObserver();
		}
	}

	private void changed() {
		notifyObservers();
	}

	@Override
	public void push_back(T arg) {
		super.push_back(arg);
		changed();
	}

	@Override
	public void push_front(T arg) {
		super.push_front(arg);
		changed();
	}

	public void double_back(T arg) {
		int count = size();
		for (int i = 0; i < count; ++i) {
			super.push_back(arg);
		}
		changed();
	}

	public void double_front(T arg) {
		int count = size();
		for (int i = 0; i < count; ++i) {
			super.push_front(arg);
		}
		changed();
	}
}
