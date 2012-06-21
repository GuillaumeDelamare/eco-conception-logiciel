package org.oep.services.ecoclient.impl1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

public class StartController implements ActionListener {
	XuggleServer server;
	public StartController(JButton start, XuggleServer server) {
		this.server = server;
		
		start.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		server.play();
	}
	
	public void setServer(XuggleServer server) {
		this.server = server;
	}

}
