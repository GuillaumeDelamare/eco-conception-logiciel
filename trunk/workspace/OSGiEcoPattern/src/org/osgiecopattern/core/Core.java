package org.osgiecopattern.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Core implements BundleActivator{
	private IntallerImpl installer;
	private ServiceManagerImpl serviceManager;
	
	@Override
	public void start(BundleContext context) throws Exception {
		installer = new IntallerImpl(context);
		serviceManager = new ServiceManagerImpl(context);
		
		context.addServiceListener(serviceManager);
		context.registerService(org.osgiecopattern.core.api.ServiceManager.class.getName(), serviceManager, null);
		context.registerService(org.osgiecopattern.core.api.Installer.class.getName(), installer, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
	
}
