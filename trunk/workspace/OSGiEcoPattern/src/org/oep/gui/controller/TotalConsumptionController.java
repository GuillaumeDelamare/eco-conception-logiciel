package org.oep.gui.controller;

import java.text.DecimalFormat;

import javax.swing.JLabel;

import org.oep.core.ServiceManager;


public class TotalConsumptionController extends Thread{
	private ServiceManager manager;
	private JLabel label;
	
	public TotalConsumptionController(ServiceManager manager, JLabel label){
		this.manager = manager;
		this.label = label;
	}
	
	@Override
	public void run() {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		while(true){
			try {
				sleep(1000);
				label.setText(String.valueOf(twoDForm.format(manager.getTotalConsumption())));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
