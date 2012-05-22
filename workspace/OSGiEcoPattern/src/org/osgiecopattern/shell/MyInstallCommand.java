package org.osgiecopattern.shell;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleContext;
import org.osgiecopattern.core.api.Installer;

public class MyInstallCommand implements Command{
	private Installer installer;
	
	public MyInstallCommand(BundleContext context) {
		installer = context.getService(context.getServiceReference(Installer.class));
	}
	
	@Override
	public void execute(String line, PrintStream out, PrintStream err) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		tokenizer.nextToken(); // discard first token

		if (tokenizer.hasMoreTokens())
			if(installer.Install(tokenizer.nextToken())==null)
				err.println("Installation failled !");
	}

	@Override
	public String getName() {
		return "myinstall";
	}

	@Override
	public String getShortDescription() {
		return "Install the bundle in the OSGi Eco Pattern";
	}

	@Override
	public String getUsage() {
		return "myinstall <bundle name>";
	}

}
