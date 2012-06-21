package org.oep.services.ecoserveur.impl2;

import org.oep.services.ecoserveur.api.EcoServeur;

public class EcoServerImpl implements EcoServeur {
	protected double consumption = 0;
	
	@Override
	public double getConsumption() {
		return consumption;
	}
}
