package org.oep.core.controller.basic;

import org.oep.core.BundleManager;
import org.oep.core.ServiceManager;
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
				System.out.println("If : max = "+ max+" cursor = "+cursor);
				cursor++;
			}
			else {
				System.out.println("else : max = "+ max+" cursor = "+cursor);
				cursor=-1;
			}
			
			if(cursor >= 0){
				System.out.println("consumption = "+serviceManager.getEcoService(cursor).getConsuption());
				if(serviceManager.getEcoService(cursor).getConsuption() > 3){
					System.out.println("tata");
					try {
						bundleManager.stopServiceBundle(serviceManager.getBundle(cursor));
					} catch (BundleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
