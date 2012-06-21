package org.oep.gui.controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import org.oep.core.BundleManager;
import org.osgi.framework.Bundle;

public class TableModelController implements Observer{
	private BundleManager bundleManager;
	private DefaultTableModel tableModel;
	
	public TableModelController(DefaultTableModel tableModel, BundleManager bundleManager) {
		this.bundleManager = bundleManager;
		this.tableModel = tableModel;
		
		this.bundleManager.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		clearTableModel();
		
		HashMap<String, Bundle> started = this.bundleManager.getStartedBundles();
		
		for(String s : started.keySet()){
			Object[] obj = {	s,
					(started.get(s) == null) ? "none" : started.get(s).getSymbolicName(),
					0
			};
			
			tableModel.addRow(obj);
		}
	}
	private void clearTableModel(){
		while(tableModel.getRowCount()>0) {
			tableModel.removeRow(0);
		}
	}
}
