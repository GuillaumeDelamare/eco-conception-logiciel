package org.oep.shell;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.oep.core.BundleManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class InstallServiceBundleCommand implements Command {
	private BundleManager installer;

	public InstallServiceBundleCommand(BundleContext context) {
		installer = context.getService(context.getServiceReference(BundleManager.class));
	}

	@Override
	public void execute(String line, PrintStream out, PrintStream err) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		tokenizer.nextToken(); // discard first token

		if (tokenizer.hasMoreTokens())
			try {
				installer.installImplBundle(tokenizer.nextToken());
			} catch (BundleException e) {
				err.println("Service Bundle installation failled !");
			}
	}

	@Override
	public String getName() {
		return "isb";
	}

	@Override
	public String getShortDescription() {
		return "Install the bundle offering a service in the OSGi Eco Pattern";
	}

	@Override
	public String getUsage() {
		return "isb <bundle name>";
	}
}
