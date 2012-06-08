package org.oep.gui.controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import org.oep.core.BundleManager;
import org.osgi.framework.Bundle;

public class TableModelController implements Observer{
	private BundleManager installer;
	private DefaultTableModel tableModel;
	
	public TableModelController(DefaultTableModel tableModel, BundleManager installer) {
		this.installer = installer;
		this.tableModel = tableModel;
		
		this.installer.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		HashMap<String, Bundle> started = this.installer.getStartedBundle();
		
		
		for(String s : started.keySet()){
			Object[] obj = {	s,
					(started.get(s) == null) ? "none" : started.get(s).getSymbolicName(),
					"TODO",
					"TODO"
			};
			
			tableModel.addRow(obj);
		}
	}
	
}
