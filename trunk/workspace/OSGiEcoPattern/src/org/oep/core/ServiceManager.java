package org.oep.core;

import java.util.ArrayList;

import org.oep.services.api.EcoService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import aQute.bnd.annotation.component.Component;

@Component
public class ServiceManager implements ServiceListener {
	private BundleContext bc;
	private ArrayList<EcoService> registeredService = new ArrayList<EcoService>();

	public ServiceManager(BundleContext bc) {
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
	
	public float getTotalConsumption(){
		float i = 0;
		for (EcoService es : registeredService) {
			i+=es.getConsuption();
		}
		return i;
	}
}
