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
		
		String[] exportedPackage = getExportedPackage(b);
		
		if(exportedPackage == null) {
			throw new IllegalArgumentException(bundle + " don't export any package.");
		}
		else {
			for(String sub : exportedPackage){
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
		String[] importPackage = getImportedPackage(b);
			
		for(String sub : importPackage){
			List<Bundle> l = bundleRegister.get(sub);
			
			if(l != null){
				l.add(b);
				setChanged();
			}
		}
		
		notifyObservers();
		
		return b;
	}
	public void uninstallImplBundle(Bundle bundle) throws BundleException {
		if(startedBundle.containsValue(bundle)){
			throw new IllegalStateException("Impossible to uninstall started bundle");
		}
		else{
			for(List<Bundle> l : bundleRegister.values()){
				l.remove(bundle);
				setChanged();
			}
			bundle.uninstall();
		}
		notifyObservers();
	}
	public void uninstallAPIBundle(Bundle bundle){
		String[] exportedPackage = getExportedPackage(bundle);
		
		
	}
	
	
	//-------------------------------------------------------------------//
	//-------------------Start and Stop Method---------------------------//
	//-------------------------------------------------------------------//
	
	public void startServiceBundle(Bundle bundle) throws BundleException{
		String[] importPackage = getImportedPackage(bundle);
		
		for(String sub : importPackage){
			Bundle b = startedBundle.get(sub);
			
			if(b != null){
				throw new IllegalStateException("Installer : trying to start a bundle whereas another bundle is already started");
			}
			startedBundle.put(sub, bundle);
			setChanged();
		}
		bundle.start();
		notifyObservers();
	}
	public void stopServiceBundle(Bundle bundle) throws BundleException {
		String[] importPackage = getImportedPackage(bundle);	
		
		for(String sub : importPackage){
			startedBundle.remove(sub);
		}
		bundle.stop();
	}
	public void replaceServiceBundle(Bundle oldBundle, Bundle newBundle) throws BundleException{
		//if(startedBundle.containsValue(oldBundle)){
			stopServiceBundle(oldBundle);
			
			startServiceBundle(newBundle);
//		}
//		else {
//			throw new IllegalStateException();
//		}
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
	public List<Bundle> getEquivalentBundle(Bundle bundle) {
		String[] tabS = getImportedPackage(bundle);
		
		for(String s : tabS){
			if(bundleRegister.containsKey(s)){
				List<Bundle> res = new ArrayList<Bundle>(bundleRegister.get(s));
				res.remove(bundle);
				
				return res;
			}
		}
		
		return null;
	}
	private String[] getImportedPackage(Bundle bundle){
		String s = bundle.getHeaders().get(Constants.IMPORT_PACKAGE);
		if(s!=null){
			s = s.replaceAll(";version=\".+?\"", "");
			return s.split(",");
		}
		else {
			return null;
		}
	}
	private String[] getExportedPackage(Bundle bundle){
		String s = bundle.getHeaders().get(Constants.EXPORT_PACKAGE);
			
		if(s!=null){
			s = s.replaceAll(";version=\".+\"", "");
			s = s.replaceAll(";uses:=\".+\"", "");
			return s.split(",");
		}
		else {
			return null;
		}
	}
	
}
