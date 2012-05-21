package org.osgiecopattern.core;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

public class Intaller {
	private static final String BUNDLE_LOCATION = "file:/home/guillaume/workspace/OSGiEcoPattern/generated/";
	
	ArrayList<Bundle> installedBundle;
	
	public Intaller() {
		installedBundle = new ArrayList<Bundle>();
	}
	
	public Bundle Install(BundleContext bc, String bundle) throws BundleException {
		Bundle b = bc.installBundle(BUNDLE_LOCATION + bundle);
		
		b.start();
		
		
		if(b.getHeaders().get("Import-Package")!=null){
			System.out.println("toto : " + b.getHeaders().get(Constants.IMPORT_PACKAGE));
			String[] exportPackage = b.getHeaders().get("Import-Package").split(",");
			for(String s : exportPackage)
				System.out.println(s);
		}
		if(b.getHeaders().get("Export-Package")!=null){
			String[] importPackage = b.getHeaders().get("Export-Package").split(",");
			for(String s : importPackage)
				System.out.println(s);
		}
		
		
		System.out.println(b.getRegisteredServices());
		
		installedBundle.add(b);
		
		return b;
	}
}
