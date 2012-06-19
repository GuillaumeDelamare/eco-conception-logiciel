package org.oep.core.controller.basic;

import org.oep.core.BundleManager;
import org.oep.core.ServiceManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		ServiceManager serviceManager = context.getService(context.getServiceReference(ServiceManager.class));
		BundleManager bundleManager = context.getService(context.getServiceReference(BundleManager.class));
		Controller controller = new Controller(serviceManager, bundleManager);
		
		context.registerService(org.oep.core.controller.api.Controller.class.getName(), controller, null);
		
		controller.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
