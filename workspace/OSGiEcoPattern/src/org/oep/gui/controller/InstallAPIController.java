package org.oep.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.oep.core.BundleManager;
import org.osgi.framework.BundleException;

public class InstallAPIController implements ActionListener  {
	private BundleManager installer;
	
	public InstallAPIController(BundleManager installer) {
			this.installer = installer;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar File", "jar");
		chooser.setFileFilter(filter);
		
		int returnVal = chooser.showOpenDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				installer.installAPIBundle(chooser.getSelectedFile().getPath());
			} catch (BundleException e2) {
				JOptionPane.showMessageDialog(null, "Bundle instalation failled", "Installation error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
