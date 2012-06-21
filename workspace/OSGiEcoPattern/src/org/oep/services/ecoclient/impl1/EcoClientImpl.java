package org.oep.services.ecoclient.impl1;

import javax.swing.SwingUtilities;

import org.oep.services.ecoclient.api.EcoClient;
import org.oep.services.ecoserveur.api.xuggle.XuggleServer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class EcoClientImpl implements EcoClient, ServiceListener{
	private double consumption = 0;
	
	private EcoclientGUI gui;
	private XuggleServer serveur;
	private LoadController loadController;
	private StartController startController;
	private BundleContext context;
	
	private String videoPath = null;
	private boolean started = false;

	public EcoClientImpl(BundleContext context) {
		this.context = context;
		
		ServiceReference<XuggleServer> sr = this.context.getServiceReference(XuggleServer.class);
		if(sr == null){
			this.serveur = null;
		}
		else{
			this.serveur = this.context.getService(this.context.getServiceReference(XuggleServer.class));
		}
		
		context.addServiceListener(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new EcoclientGUI();
				
				if(serveur!=null){
					serveur.setmScreen(gui.xuggleView);
				}
				
				startController = new StartController(EcoClientImpl.this.gui.start, EcoClientImpl.this.serveur);
				loadController = new LoadController(EcoClientImpl.this.gui.load, EcoClientImpl.this.serveur);
				
				gui.display();
			}
		});
	}
	
	
	@Override
	public double getConsumption() {
		return consumption;
	}


	@Override
	public void serviceChanged(ServiceEvent event) {
		ServiceReference<?> sr = event.getServiceReference();
		Object o =context.getService(sr);
		
		if(o instanceof XuggleServer){
			switch (event.getType()) {
			case ServiceEvent.REGISTERED :
				this.serveur = (XuggleServer)o;
				
				loadController.setServer(serveur);
				startController.setServer(serveur);
				
				serveur.setmScreen(gui.xuggleView);
				serveur.setVideo(videoPath);
				if(started){
					serveur.play();
				}
				
				break;
			
			case ServiceEvent.UNREGISTERING:
				loadController.setServer(null);
				startController.setServer(null);
				
				started = serveur.isStarted();
				if(started){
					serveur.stop();
				}
				videoPath = serveur.getVideo();
				
				this.serveur = null;
				break;
			
			default:
				break;
			}
		}
	}
	
	
}
