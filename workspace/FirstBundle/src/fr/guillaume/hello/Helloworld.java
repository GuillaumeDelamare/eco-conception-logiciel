package fr.guillaume.hello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Helloworld implements BundleActivator {

	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("Hello world !!!");
		
		wait();
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Goodbye World !!!");
	}
	
	public void deactivate() {
		System.out.println("Pouet !!!");

	}
}
