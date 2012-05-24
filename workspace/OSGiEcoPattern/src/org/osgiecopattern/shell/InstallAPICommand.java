package org.osgiecopattern.shell;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgiecopattern.core.api.Installer;

public class InstallAPICommand implements Command{
	private Installer installer;
	
	public InstallAPICommand(BundleContext context) {
		installer = context.getService(context.getServiceReference(Installer.class));
	}
	
	@Override
	public void execute(String line, PrintStream out, PrintStream err) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		tokenizer.nextToken(); // discard first token

		if (tokenizer.hasMoreTokens())
			try {
				installer.installAPIBundle(tokenizer.nextToken());
			} catch (BundleException e) {
				err.println("API installation failled !");
			}
				
	}

	@Override
	public String getName() {
		return "iapi";
	}

	@Override
	public String getShortDescription() {
		return "Install the bundle of an API in the OSGi Eco Pattern";
	}

	@Override
	public String getUsage() {
		return "iapi <bundle name>";
	}

}
