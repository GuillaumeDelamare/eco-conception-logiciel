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
	private XuggleReader reader;
	private XuggleServer serveur;
	private LoadController controller;
	private BundleContext context;

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
		
		reader = new XuggleReader();
		reader.setServer(this.serveur);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new EcoclientGUI();
				gui.display();
				reader.setmScreen(gui.xuggleView);
				
				new StartController(EcoClientImpl.this.gui.start, EcoClientImpl.this.reader);
				controller = new LoadController(EcoClientImpl.this.gui.load, EcoClientImpl.this.serveur);
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
			this.serveur = (XuggleServer)o;
			
			switch (event.getType()) {
			case ServiceEvent.REGISTERED :
				reader.setServer(serveur);
				controller.setServer(serveur);
				break;
			
			case ServiceEvent.UNREGISTERING:
				reader.setServer(null);
				controller.setServer(null);
				break;
			
			default:
				break;
			}
		}
	}
	
	
}
