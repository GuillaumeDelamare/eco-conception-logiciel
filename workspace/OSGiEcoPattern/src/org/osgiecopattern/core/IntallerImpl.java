package org.osgiecopattern.core;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgiecopattern.core.api.Installer;

public class IntallerImpl implements Installer{
	private static final String BUNDLE_LOCATION = "file:/home/guillaume/workspace/OSGiEcoPattern/generated/";
	
	private BundleContext context;
	private ArrayList<Bundle> installedBundle;
	
	public IntallerImpl(BundleContext context) {
		this.context = context;
		installedBundle = new ArrayList<Bundle>();
	}
	
	public Bundle Install(String bundle) {
		Bundle b;
		try {
			b = context.installBundle(BUNDLE_LOCATION + bundle);
		} catch (BundleException e) {
			return null;
		}
		
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
