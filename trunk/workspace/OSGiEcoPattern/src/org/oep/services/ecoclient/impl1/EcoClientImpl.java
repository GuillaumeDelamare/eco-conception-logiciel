package org.oep.services.ecoclient.impl1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.oep.services.ecoclient.api.EcoClient;

public class EcoClientImpl extends JFrame implements EcoClient{
	private static final long serialVersionUID = -9222064904864693841L;

	private JButton load, start;
	
	private double consumption = 0;

	public EcoClientImpl() {
		createComponnent();
		placeComponnent();
		createController();
	}
	private void createComponnent(){
		load = new JButton("Load");
		start = new JButton("Start");
	}
	private void placeComponnent() {
		JPanel p1 = new JPanel(new FlowLayout());{
			p1.add(load);
			p1.add(start);
			
			this.add(p1, BorderLayout.SOUTH);
		}
		JPanel p2 = new JPanel(new BorderLayout());{
			//TODO ajouter la vue de la vidéo
			
			this.add(p2);
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
	
	@Override
	public double getConsumption() {
		// TODO Auto-generated method stub
		return consumption;
	}
	
	
}
