package org.oep.gui;

import javax.swing.SwingUtilities;

import org.oep.core.BundleManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private BundleManager i;
	
	@Override
	public void start(BundleContext context) throws Exception {
		ServiceReference[] refs = context.getServiceReferences(BundleManager.class.getName(), null);
		if (refs != null)
        {
            i = context.getService(refs[0]);
		
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new MainFrame(i).display();
				}
			});
        }
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}