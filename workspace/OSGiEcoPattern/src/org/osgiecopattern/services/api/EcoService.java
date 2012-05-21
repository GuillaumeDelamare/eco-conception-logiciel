package org.osgiecopattern.services.api;

/**
 * Interface define what is an eco-service. An eco-service provide one functionality to the software. The service must be able to performed his functionality and return his actual consumption. 
 * 
 * @author guillaume
 */
public interface EcoService {
	public void start();
	
	public String getName();
	
	public double getConsuption();
}
