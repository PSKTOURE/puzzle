package util;

import java.util.ArrayList;


public abstract class AbstractObservableModel implements ObservableModel {
	
protected ArrayList<Observer> observers;
	
	public AbstractObservableModel(ArrayList<Observer> observers) {
		super();
		this.observers = observers;
	}
	
	public AbstractObservableModel() {
		this(new ArrayList<>());
	}

	public void addObserver(Observer e) {
		observers.add(e);
		e.updateModel(this);
	}
	
	public void removeObserver(Observer e) {
		observers.remove(e);
	}
	
	protected void fireChange() {
		for(Observer e : observers) {
			e.updateModel(this);
		}
	}
}

