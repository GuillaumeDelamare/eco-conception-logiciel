package org.oep.core.controller.basic;

import java.util.List;
import java.util.Map.Entry;

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
		int max;
		EcoService es = null;
		Bundle bundle = null;
		
		while(true) {
			try {
				Thread.sleep(SLEPPING_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			synchronized (serviceManager) {
//				max = serviceManager.getNbOfEcoService();
//				if(cursor<max - 1){
//					cursor++;
//					es = serviceManager.getEcoService(cursor);
//					bundle = serviceManager.getBundle(cursor);
//				}
//				else {
//					cursor=-1;
//				}
//				
//			}
			synchronized (serviceManager) {
				max = serviceManager.getNbOfEcoService();
				if(cursor < max - 1) {
					cursor++;
					Entry<Bundle, EcoService> e = serviceManager.getSet(cursor); 
					bundle = e.getKey();
					es = e.getValue();
					
					if(es.getConsumption() > 3.0){
						try {
							List<Bundle> newBundle = bundleManager.getEquivalentBundle(bundle);
							if(newBundle.size()>0){
								bundleManager.replaceServiceBundle(bundle, newBundle.get(0));
							}
						} catch (BundleException be) {
							// TODO Auto-generated catch block
							be.printStackTrace();
						}
					}
				}
				else {
					cursor=-1;
				}
			}
			
			if(cursor >= 0){
				
			}
		}
	}
}
