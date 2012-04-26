package org.firstbundle;

import org.osgi.framework.BundleContext;

import aQute.bnd.annotation.component.*;

@Component
public class FirstBundle {
	@Activate
	public void activate(BundleContext bc) {
		System.out.println("activate !!");
	}
	
	@Deactivate
	public void deactivate(BundleContext bc) {
		System.out.println("deactivate !!!");
	}

}