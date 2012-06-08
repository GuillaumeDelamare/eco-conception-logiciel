package org.oep.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

public class BundleManager extends Observable{
	private static final String BUNDLE_LOCATION = "file:";
	
	private BundleContext context;
	private HashMap<String, List<Bundle>> bundleRegister;
	private HashMap<String, Bundle> startedBundle;
	
	//-------------------------------------------------------------------//
	//-------------------------Constructor-------------------------------//
	//-------------------------------------------------------------------//
	
	public BundleManager(BundleContext context) {
		this.context = context;
		bundleRegister = new HashMap<String, List<Bundle>>();
		startedBundle = new HashMap<String, Bundle>();
	}
	
	//-------------------------------------------------------------------//
	//-------------------Installation Method-----------------------------//
	//-------------------------------------------------------------------//
	
	public Bundle install(String bundle) throws BundleException {		
		return context.installBundle(BUNDLE_LOCATION + bundle);
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
				
				bundleRegister.put(sub, new ArrayList<Bundle>());
				startedBundle.put(sub, null);

				setChanged();
			}
			notifyObservers();
		}
		
		return b;
	}
	public Bundle installImplBundle(String bundle) throws BundleException{
		Bundle b = install(bundle);
		
		String s = b.getHeaders().get(Constants.IMPORT_PACKAGE);
		
		if(s!=null){
			System.out.println("Installer : EXPORT_PACKAGE = "+ s);
			
			s = s.replaceAll(";version=\".+?\"", "");
			String[] exportPackage = s.split(",");
			
			for(String sub : exportPackage){
				System.out.println("Installer : try to find " + sub);
				
				List<Bundle> l = bundleRegister.get(sub);
				
				if(l != null){
					l.add(b);
					System.out.println("Installer : add bundle implementing " + sub);

					setChanged();
				}
			}
			
			notifyObservers();
		}
		
		return b;
	}
	
	//-------------------------------------------------------------------//
	//-------------------Start and Stop Method---------------------------//
	//-------------------------------------------------------------------//
	
	public void startServiceBundle(Bundle bundle) throws BundleException{
		String s = bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
		
		if(s!=null){
			s = s.replaceAll(";version=\".+?\"", "");
			String[] exportPackage = s.split(",");
			
			for(String sub : exportPackage){
				System.out.println("Installer : try to find " + sub);
				Bundle b = startedBundle.get(sub);
				
				if(b != null){
					throw new IllegalStateException("Installer : trying to start a bundle whereas another bundle is already started");
				}
				
				bundle.start();
				startedBundle.put(sub, bundle);
				setChanged();
			}
		}
		notifyObservers();
	}
	public void stopServiceBundle(Bundle bundle) throws BundleException {
		String s = bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
		if(s!=null){
			s = s.replaceAll(";version=\".+?\"", "");
			String[] exportPackage = s.split(",");
			
			for(String sub : exportPackage){
				startedBundle.remove(sub);
			}
		}
		bundle.stop();
	}
	
	//-------------------------------------------------------------------//
	//----------------------Getter an Setter-----------------------------//
	//-------------------------------------------------------------------//
	
	public Set<String> getInstalledAPIBundle() {
		return bundleRegister.keySet();
	}
	public List<Bundle> getInstalledServiceBundle(String APIBundle) {
		return bundleRegister.get(APIBundle);
	}
	public HashMap<String, Bundle> getStartedBundle(){
		return new HashMap<String, Bundle>(startedBundle);
	}
}
