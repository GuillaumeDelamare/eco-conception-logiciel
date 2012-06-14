package org.oep.io.exportconso;

import org.oep.core.ServiceManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		new ExportConso(context.getService(context.getServiceReference(ServiceManager.class))).start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
