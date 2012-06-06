package org.oep.gui;

import javax.swing.SwingUtilities;

import org.oep.core.Core;
import org.oep.core.api.ServiceManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	
	@Override
	public void start(BundleContext context) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(null, null).display();//context.getService(context.getServiceReference(ServiceManager.class)));
			}
		});
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
