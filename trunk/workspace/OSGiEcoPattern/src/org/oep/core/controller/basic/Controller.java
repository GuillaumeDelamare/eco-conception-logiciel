package org.oep.core.controller.basic;

import org.oep.core.BundleManager;
import org.oep.core.ServiceManager;
import org.oep.services.api.EcoService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public class Controller extends Thread{
	private final static long SLEPPING_TIME = 1000;
	private ServiceManager serviceManager;
	private BundleManager bundleManager;
	
	public Controller(ServiceManager serviceManager, BundleManager bundleManager) {
		this.serviceManager = serviceManager;
		this.bundleManager = bundleManager;
	
	}
	
	@Override
	public void run() {
		int cursor = -1;
		int max = serviceManager.getNbOfEcoService();
		
		while(true) {
			try {
				Thread.sleep(SLEPPING_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			max = serviceManager.getNbOfEcoService();
			if(cursor<max - 1){
				cursor++;
			}
			else {
				cursor=-1;
			}
			
			if(cursor >= 0){
				EcoService es = serviceManager.getEcoService(cursor);
				System.out.println("consumption = " + es.getConsuption());
				if(es.getConsuption() > 3.0){
					Bundle bundle = serviceManager.getBundle(cursor);
					try {
						System.out.println("Controller - changement de bundle");
						bundleManager.replaceServiceBundle(bundle, bundleManager.getEquivalentBundle(bundle).get(0));
					} catch (BundleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
