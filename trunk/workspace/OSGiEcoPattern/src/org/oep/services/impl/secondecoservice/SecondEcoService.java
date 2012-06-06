package org.oep.services.impl.secondecoservice;

import org.oep.services.api.EcoService;

import aQute.bnd.annotation.component.Component;

@Component
public class SecondEcoService implements EcoService {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Second Eco Service";
	}

	@Override
	public double getConsuption() {
		return 5.3;
	}

}
