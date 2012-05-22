package org.osgiecopattern.shell;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(org.apache.felix.shell.Command.class.getName(), new ConsoCommand(context), null);
		context.registerService(org.apache.felix.shell.Command.class.getName(), new MyInstallCommand(context), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
