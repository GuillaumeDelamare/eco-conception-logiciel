package fr.guillaume.hello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Hello world !!!");
	}
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Goodbye World !!!");
	}

}
