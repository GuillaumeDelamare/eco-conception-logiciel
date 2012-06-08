package org.oep.core;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.oep.services.api.EcoService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.Component;

@Component
public class ServiceManager extends Observable implements ServiceListener, Observer {
	private BundleContext bc;
	private ArrayList<ServiceReference<?>> registeredService = new ArrayList<ServiceReference<?>>();

	public ServiceManager(BundleContext bc) {
		this.bc = bc;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> sr = event.getServiceReference();
		Object o =bc.getService(sr);
		System.out.println("toto");
		if(o instanceof EcoService){
			switch (event.getType()) {
			case ServiceEvent.REGISTERED :
				System.out.println("coucou");
				registeredService.add(sr);
				break;
			case ServiceEvent.UNREGISTERING:
				registeredService.remove(sr);
				break;
			default:
				break;
			}
		}
		
	}
	
	public float getTotalConsumption(){
		float i = 0;
		for (ServiceReference<?> sr : registeredService) {
			EcoService es = (EcoService) bc.getService(sr);
			i+=es.getConsuption();
		}
		return i;
	}
	public EcoService getEcoService(int id){
		return (EcoService)bc.getService(registeredService.get(id));
	}
	public Bundle getBundle(int id) {
		return registeredService.get(id).getBundle();
	}
	public int getNbOfEcoService(){
		return registeredService.size();
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
}
