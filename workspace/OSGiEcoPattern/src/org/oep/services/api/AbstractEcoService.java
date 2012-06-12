package org.oep.services.api;

import java.util.Observable;

public abstract class AbstractEcoService extends Observable implements EcoService {
	private double consumption = 0;
	@Override
	public double getConsuption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
		setChanged();
		notifyObservers();
	}
}
