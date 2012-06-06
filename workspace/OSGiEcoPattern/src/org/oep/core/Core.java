package org.oep.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Core implements BundleActivator{
	private IntallerImpl installer;
	private ServiceManagerImpl serviceManager;
	
	public Core() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		installer = new IntallerImpl(context);
		serviceManager = new ServiceManagerImpl(context);
		
		context.addServiceListener(serviceManager);
		context.registerService(org.oep.core.api.ServiceManager.class.getName(), serviceManager, null);
		context.registerService(org.oep.core.api.Installer.class.getName(), installer, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
	
	public IntallerImpl getInstaller() {
		return installer;
	}
	public ServiceManagerImpl getServiceManager() {
		return serviceManager;
	}
}
