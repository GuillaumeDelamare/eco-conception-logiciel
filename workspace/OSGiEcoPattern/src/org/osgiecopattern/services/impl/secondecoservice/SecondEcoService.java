package org.osgiecopattern.services.impl.secondecoservice;

import org.osgiecopattern.services.api.EcoService;

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
	public int getConsuption() {
		return 5;
	}

}