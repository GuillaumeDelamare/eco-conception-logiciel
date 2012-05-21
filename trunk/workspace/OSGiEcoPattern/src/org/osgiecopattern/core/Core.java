package org.osgiecopattern.core;

import org.apache.felix.framework.BundleRevisionImpl;
import org.apache.felix.framework.BundleWiringImpl;
import org.apache.felix.framework.wiring.BundleCapabilityImpl;
import org.apache.felix.framework.wiring.BundleWireImpl;
import org.osgi.framework.Bundle;
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
		
		context.addServiceListener(serviceManager);
		context.registerService(org.apache.felix.shell.Command.class.getName(), new ConsoCommand(serviceManager), null);
		
		Bundle b1 = installer.Install(context, "OSGiEcoPattern.org.osgiecopattern.services.impl.firstecoservice.jar");
		Bundle b2 = installer.Install(context, "OSGiEcoPattern.org.osgiecopattern.services.impl.secondecoservice.jar");
		
		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}
	
}
