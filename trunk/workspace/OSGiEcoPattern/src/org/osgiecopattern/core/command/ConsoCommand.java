package org.osgiecopattern.core.command;

import java.io.PrintStream;

import org.apache.felix.shell.Command;
import org.osgiecopattern.core.api.ServiceManager;

public class ConsoCommand implements Command{
	private ServiceManager sm;
	
	public ConsoCommand(ServiceManager sm) {
		this.sm = sm;
	}

	@Override
	public void execute(String arg0, PrintStream arg1, PrintStream arg2) {
		System.out.println(sm.getTotalConsumption());
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
