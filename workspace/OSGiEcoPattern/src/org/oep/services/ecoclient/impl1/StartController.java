package org.oep.services.ecoclient.impl1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StartController implements ActionListener {
	XuggleReader reader;
	public StartController(JButton start, XuggleReader reader) {
		this.reader = reader;
		
		start.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		reader.play();
	}

}
