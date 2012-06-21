package org.oep.services.ecoserveur.impl1;

import org.oep.services.ecoserveur.api.EcoServeur;

public class EcoServerImpl implements EcoServeur {
	private double consumption = 0;
	
	
	
	
	@Override
	public double getConsumption() {
		consumption+=(Math.random()*0.2)-0.08;
		return consumption;
	}

}
