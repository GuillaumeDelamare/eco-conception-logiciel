package org.oep.gui.controller;

import java.text.DecimalFormat;

import javax.swing.table.TableModel;

import org.oep.core.BundleManager;
import org.oep.core.ServiceManager;

public class ConsumptionController extends Thread {
	private TableModel tablemodel;
	private ServiceManager servicemanager;
	private BundleManager bundleManager;
	
	
	public ConsumptionController(TableModel tablemodel, ServiceManager servicemanager, BundleManager bundelManager) {
		this.tablemodel = tablemodel;
		this.servicemanager = servicemanager;
		this.bundleManager = bundelManager;
	}


	@Override
	public void run() {
		double consumption = 0.;
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		
		while(true){
			try {
				sleep(1000);
				
				for(int i = 0; i < tablemodel.getRowCount(); i++){
					consumption = servicemanager.getConsumption(bundleManager.getStartedBundles().get((String)tablemodel.getValueAt(i, 0)));
					tablemodel.setValueAt(twoDForm.format(consumption) , i, 2);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
