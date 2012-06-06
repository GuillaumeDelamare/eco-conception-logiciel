package org.oep.gui;

import javax.swing.JFrame;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;

@Component
public class Frame extends JFrame {
	private static final long serialVersionUID = -4909438209076360578L;

	public Frame() {
		setTitle("OSGiEcoPattern");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Activate
	public void activate(){
		new Frame().setVisible(true);
	}
	
	@Deactivate
	public void deactivate(){
		
	}
}
