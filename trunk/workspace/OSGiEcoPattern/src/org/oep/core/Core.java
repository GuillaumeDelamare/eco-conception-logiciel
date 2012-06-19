package org.oep.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Core implements BundleActivator{
	private BundleManager installer;
	private ServiceManager serviceManager;
	
	public Core() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		installer = new BundleManager(context);
		serviceManager = new ServiceManager(context);
		
		context.addServiceListener(serviceManager);
		context.registerService(org.oep.core.ServiceManager.class.getName(), serviceManager, null);
		context.registerService(org.oep.core.BundleManager.class.getName(), installer, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
	
	public BundleManager getInstaller() {
		return installer;
	}
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
}
