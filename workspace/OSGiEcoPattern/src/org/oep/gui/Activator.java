package org.oep.gui;

import javax.swing.SwingUtilities;

import org.oep.core.api.Installer;
import org.oep.core.api.ServiceManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	private Installer i;
	private ServiceManager sm;
	
	@Override
	public void start(BundleContext context) throws Exception {
		sm = context.getService(context.getServiceReference(ServiceManager.class));
		i = context.getService(context.getServiceReference(Installer.class));
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(sm, i).display();
			}
		});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
