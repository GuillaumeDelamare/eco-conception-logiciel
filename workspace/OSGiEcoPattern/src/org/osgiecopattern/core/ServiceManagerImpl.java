package org.osgiecopattern.core;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgiecopattern.core.api.ServiceManager;
import org.osgiecopattern.services.api.EcoService;

import aQute.bnd.annotation.component.Component;

@Component
public class ServiceManagerImpl implements ServiceListener, ServiceManager{
	BundleContext bc;
	ArrayList<Bundle> registeredBundle = new ArrayList<Bundle>();
	ArrayList<EcoService> registeredService = new ArrayList<EcoService>();
	ArrayList<Bundle> registeredFramework = new ArrayList<Bundle>();

	public ServiceManagerImpl(BundleContext bc) {
		this.bc = bc;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(EcoService es : registeredService){
			s+=es.toString() + "\n";
		}
		return s;
	}
	
	@Override
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()) {
		case ServiceEvent.REGISTERED :
			if(bc.getService(event.getServiceReference()) instanceof EcoService){
				registeredService.add((EcoService)bc.getService(event.getServiceReference()));
			}
			break;
		case ServiceEvent.UNREGISTERING:
			if(bc.getService(event.getServiceReference()) instanceof EcoService){
				registeredService.remove((EcoService)bc.getService(event.getServiceReference()));
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public float getTotalConsumption(){
		float i = 0;
		for (EcoService es : registeredService) {
			i+=es.getConsuption();
		}
		return i;
	}
}
