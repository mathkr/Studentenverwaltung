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
