package org.oep.services.ecoclient.impl1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EcoclientGUI extends JFrame{
	private static final long serialVersionUID = -5689376965600505446L;
	protected JButton load, start;
	protected XuggleViewImpl xuggleView;

	public EcoclientGUI() {
		createComponnent();
		placeComponnent();
		createController();
	}
	private void createComponnent(){
		load = new JButton("Load");
		start = new JButton("Start");
		
		xuggleView = new XuggleViewImpl();
	}
	private void placeComponnent() {
		JPanel p1 = new JPanel(new FlowLayout());{
			p1.add(load);
			p1.add(start);
			
			this.add(p1, BorderLayout.SOUTH);
		}
		JPanel p2 = new JPanel(new BorderLayout());{
			p2.add(xuggleView);
			
			this.add(p2, BorderLayout.CENTER);
		}
	}
	private void createController() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void display(){
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
