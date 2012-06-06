package org.oep.shell;

import java.io.PrintStream;

import org.apache.felix.shell.Command;
import org.oep.core.api.ServiceManager;
import org.osgi.framework.BundleContext;

public class ConsoCommand implements Command{
	private ServiceManager sm;
	
	public ConsoCommand(BundleContext context) {
		sm = context.getService(context.getServiceReference(ServiceManager.class));
		
		assert(sm == null);
	}

	@Override
	public void execute(String line, PrintStream out, PrintStream err) {
		out.println(sm.getTotalConsumption());
	}

	@Override
	public String getName() {
		return "conso";
	}

	@Override
	public String getShortDescription() {
		return "Print the total consumption of the program";
	}

	@Override
	public String getUsage() {
		return "conso";
	}

}
