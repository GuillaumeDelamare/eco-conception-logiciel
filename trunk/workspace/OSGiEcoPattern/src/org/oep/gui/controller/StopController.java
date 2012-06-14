package org.oep.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import org.oep.core.BundleManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public class StopController implements ActionListener{
	private BundleManager bundleManager;
	private JTable table;
	
	public StopController(BundleManager bundleManager, JTable table) {
		this.table = table;
		this.bundleManager = bundleManager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int select = table.getSelectedRow();
		
		if(select >= 0){
			Object o = table.getModel().getValueAt(select, 0);
			
			if (o instanceof String) {
				Bundle b = bundleManager.getStartedBundle((String)o);
				try {
					bundleManager.stopServiceBundle(b);
				} catch (BundleException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
