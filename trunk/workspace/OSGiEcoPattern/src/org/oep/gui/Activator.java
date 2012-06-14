package org.oep.gui;

import javax.swing.SwingUtilities;

import org.oep.core.BundleManager;
import org.oep.core.ServiceManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	private BundleManager bundleManager;
	private ServiceManager serviceManager;
	
	@Override
	public void start(BundleContext context) throws Exception {
		serviceManager = context.getService(context.getServiceReference(ServiceManager.class));
		bundleManager = context.getService(context.getServiceReference(BundleManager.class));
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(bundleManager, serviceManager).display();
			}
		});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
