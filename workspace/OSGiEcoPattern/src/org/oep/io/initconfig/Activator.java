package org.oep.io.initconfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.oep.core.BundleManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		File f = new File("./init.conf");
		
		if(f.exists()){
			BundleManager bm = context.getService(context.getServiceReference(BundleManager.class));
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line = br.readLine();
			while(!line.equals("families")){
				System.out.println("ignore "+line);
				
				line = br.readLine();
			}
			
			line = br.readLine();
			while(!line.equals("bundles")){
				System.out.println("install families "+line);
				bm.installAPIBundle(line);
				
				line = br.readLine();
			}
			
			line = br.readLine();
			while(line!=null){
				System.out.println("install bundle "+line);
				bm.installImplBundle(line);
				
				line = br.readLine();
			}
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
