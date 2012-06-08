package org.oep.shell;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.oep.core.BundleManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class InstallAPICommand implements Command{
	private BundleManager installer;

	public InstallAPICommand(BundleContext context) {
		ServiceReference[] refs;
		try {
			refs = context.getServiceReferences(BundleManager.class.getName(), null);
			if (refs != null) {
				installer = context.getService(refs[0]);

			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
