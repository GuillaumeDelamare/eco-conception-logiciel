package org.osgiecopattern.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgiecopattern.core.command.ConsoCommand;


public class Core implements BundleActivator{
	private Intaller installer;
	private ServiceManagerImpl serviceManager;
	
	@Override
	public void start(BundleContext context) throws Exception {
		installer = new Intaller();
		serviceManager = new ServiceManagerImpl(context);
		
		//context.addBundleListener(serviceManager);
//		context.addFrameworkListener(serviceManager);
		context.addServiceListener(serviceManager);
		
		context.registerService(org.apache.felix.shell.Command.class.getName(), new ConsoCommand(serviceManager), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
	
}
