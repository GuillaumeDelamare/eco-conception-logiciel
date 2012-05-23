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
			b.start();
		} catch (BundleException e) {
			return null;
		}
		
		String s = b.getHeaders().get(Constants.IMPORT_PACKAGE);
		
		if(s!=null){
			System.out.println("toto : " + s);
			s = s.replaceAll(";version=\".+?\"", "");
			String[] exportPackage = s.split(",");
			
			for(String sub : exportPackage)
				System.out.println(sub);
		}
		
		s = b.getHeaders().get(Constants.EXPORT_PACKAGE);
		
		if(s!=null){
			s = s.replaceAll(";version=\".+\"", "");
			String[] importPackage = s.split(",");
			for(String sub : importPackage)
				System.out.println(sub);
		}
		
		
		
		System.out.println(b.getRegisteredServices()[0].getClass());
		
		installedBundle.add(b);
		
		return b;
	}
}
