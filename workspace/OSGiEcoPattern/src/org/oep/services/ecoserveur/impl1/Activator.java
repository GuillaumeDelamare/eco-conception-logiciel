package org.oep.services.ecoserveur.impl1;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;
import org.oep.services.ecoserveur.impl1.EcoServerImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		EcoServerImpl esi = new EcoServerImpl();
		context.registerService(EcoServerImpl.class.getName(), esi, null);
		context.registerService(XuggleServer.class.getName(), new XuggleServerImpl(esi), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

}
