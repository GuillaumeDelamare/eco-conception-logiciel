package org.oep.gui.controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import org.oep.core.ServiceManager;


public class ConsumptionController implements Observer{
	private ServiceManager manager;
	private JLabel label;
	
	public ConsumptionController(ServiceManager manager, JLabel label){
		this.manager = manager;
		this.label = label;
		
		this.manager.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		label.setText(String.valueOf(manager.getTotalConsumption()));
	}

}
