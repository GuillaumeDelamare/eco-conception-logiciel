package org.osep.services.impl.firstecoservice;

import org.osep.services.api.EcoService;

/**
 * FirstEcoService is an example of {@link org.osep.services.api.EcoService EcoService}'s implementation.  
 * 
 * @author guillaume
 *
 */
public class FirstEcoService implements EcoService {

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "First Eco-Service";
	}

	@Override
	public double getConsuption() {
		return 3;
	}

}
