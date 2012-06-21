package org.oep.services.ecoserveur.impl1;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(EcoServerImpl.class.getName(), new EcoServerImpl(), null);
		context.registerService(XuggleServer.class.getName(), new XuggleServerImpl(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
