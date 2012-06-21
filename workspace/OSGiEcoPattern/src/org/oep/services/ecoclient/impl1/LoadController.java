package org.oep.services.ecoclient.impl1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

public class LoadController implements ActionListener {
	private XuggleServer server;

	public LoadController(JButton load, XuggleServer server) {
		this.server = server;
		
		load.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			server.setVideo(chooser.getSelectedFile().getPath());
		}
	}
	public void setServer(XuggleServer server) {
		this.server = server;
	}
}