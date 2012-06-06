package org.oep.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.oep.core.api.Installer;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

public class IntallerImpl implements Installer{
	private static final String BUNDLE_LOCATION = "file:/home/guillaume/workspace/OSGiEcoPattern/generated/";
	
	private BundleContext context;
	private ArrayList<Bundle> installedBundle;
	private HashMap<String, List<Bundle>> serviceBundle;
	private HashMap<String, Bundle> startedServiceBundle;
	
	public IntallerImpl(BundleContext context) {
		this.context = context;
		installedBundle = new ArrayList<Bundle>();
		serviceBundle = new HashMap<String, List<Bundle>>();
		startedServiceBundle = new HashMap<String, Bundle>();
	}
	
	public Bundle install(String bundle) throws BundleException {
		Bundle b;
		
		b = context.installBundle(BUNDLE_LOCATION + bundle);
		
		installedBundle.add(b);
		
		return b;
	}
	public Bundle installAPIBundle(String bundle) throws BundleException{
		Bundle b = install(bundle);
		
		String s = b.getHeaders().get(Constants.EXPORT_PACKAGE);
		
		if(s!=null){
			System.out.println("Installer : EXPORT_PACKAGE = "+ s);
			s = s.replaceAll(";version=\".+\"", "");
			s = s.replaceAll(";uses:=\".+\"", "");
			String[] importPackage = s.split(",");
			for(String sub : importPackage){
				System.out.println("Installer : add API " + sub);
				serviceBundle.put(sub, new ArrayList<Bundle>());
				startedServiceBundle.put("sub", null);
			}
		}
		
		return b;
	}
	public Bundle installServiceBundle(String bundle) throws BundleException{
		Bundle b = install(bundle);
		
		String s = b.getHeaders().get(Constants.IMPORT_PACKAGE);
		
		if(s!=null){
			System.out.println("Installer : EXPORT_PACKAGE = "+ s);
			s = s.replaceAll(";version=\".+?\"", "");
			String[] exportPackage = s.split(",");
			
			for(String sub : exportPackage){
				System.out.println("Installer : try to find " + sub);
				List<Bundle> l = serviceBundle.get(sub);
				
				if(l != null){
					l.add(b);
					System.out.println("Installer : add bundle implementing " + sub);
				}
			}
		}
		
		return b;
	}
	
	public void startServiceBundle(Bundle bundle) throws BundleException{
		String s = bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
		
		if(s!=null){
			s = s.replaceAll(";version=\".+?\"", "");
			String[] exportPackage = s.split(",");
			
			for(String sub : exportPackage){
				System.out.println("Installer : try to find " + sub);
				Bundle b = startedServiceBundle.get(sub);
				
				if(b != null){
					throw new IllegalStateException("Installer : trying to start a bundle whereas another bundle is already started");
				}
				
				bundle.start();
				startedServiceBundle.put(sub, bundle);
			}
		}
	}
	
	public void replaceServiceBundle(Bundle oldBundle, Bundle newBundle) {
		
	}
}
