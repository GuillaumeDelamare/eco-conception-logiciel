package org.osgiecopattern.services.impl.firstecoservice;

import org.osgiecopattern.services.api.EcoService;

/**
 * FirstEcoService is an example of {@link org.osgiecopattern.services.api.EcoService EcoService}'s implementation.  
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
