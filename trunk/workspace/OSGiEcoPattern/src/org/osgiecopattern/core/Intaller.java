package org.osgiecopattern.core;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class Intaller {
	private static final String BUNDLE_LOCATION = "file:/home/guillaume/workspace/OSGiEcoPattern/generated/";
	
	ArrayList<Bundle> installedBundle;
	
	public Intaller() {
		installedBundle = new ArrayList<Bundle>();
	}
	
	public void Install(BundleContext bc, String bundle) throws BundleException {
		Bundle b = bc.installBundle(BUNDLE_LOCATION + bundle);
		//TODO Séparer le démarage de l'installation
		b.start();
		installedBundle.add(b);
	}
}
