package org.oep.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;

import org.oep.services.api.EcoService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.Component;

@Component
public class ServiceManager extends Observable implements ServiceListener {
	private BundleContext bc;
	private HashMap<Bundle, EcoService> registeredService = new HashMap<Bundle, EcoService>();

	//-------------------------------------------------------------------//
	//-------------------------Constructor-------------------------------//
	//-------------------------------------------------------------------//
	
	public ServiceManager(BundleContext bc) {
		this.bc = bc;
	}
	
	//-------------------------------------------------------------------//
	//----------------------Getter an Setter-----------------------------//
	//-------------------------------------------------------------------//
	
	public synchronized double getTotalConsumption(){
		float i = 0;
		for (EcoService es : registeredService.values()) {
			i+=es.getConsumption();
		}
		return i;
	}
	public double getConsumption(Bundle b) {
		if(registeredService.get(b) == null){
			return 0;
		}
		else{
			return registeredService.get(b).getConsumption();
		}
	}
	public EcoService getEcoService(Bundle bundle){
		return registeredService.get(bundle);
	}
	public int getNbOfEcoService(){
		return registeredService.size();
	}
	public Entry<Bundle, EcoService> getSet(int index) {
		int i = 0;
		Iterator<Entry<Bundle, EcoService>> iterator = registeredService.entrySet().iterator();
		Entry<Bundle, EcoService> e = iterator.next();
		
		for(i = 0; i < index; i++){
			e = iterator.next();
		}
		
		return e;
	}

	//-------------------------------------------------------------------//
	//----------------------Implemented Method---------------------------//
	//-------------------------------------------------------------------//
	

	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> sr = event.getServiceReference();
		Object o =bc.getService(sr);
		if(o instanceof EcoService){
			EcoService es = (EcoService)o;
			switch (event.getType()) {
			case ServiceEvent.REGISTERED :
				synchronized (this) {
					registeredService.put(sr.getBundle() ,es);
				}
				
				setChanged();
				break;
			case ServiceEvent.UNREGISTERING:
				synchronized (this) {
					registeredService.remove(sr.getBundle());
				}
				
				setChanged();
				break;
			default:
				break;
			}
		}
		notifyObservers();
	}
}
