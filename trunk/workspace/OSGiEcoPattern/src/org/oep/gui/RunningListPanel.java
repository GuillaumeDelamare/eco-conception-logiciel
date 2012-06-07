package org.oep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
public class RunningListPanel extends JPanel {
	private static final long serialVersionUID = -2396252588618798929L;
	
	private JLabel consuptionLabel, consumptionValueLabel, consumptionUnitLabel, serviceListLabel;
	private JTable table;
	
	public RunningListPanel() {
		createComponent();
		placeComponent();
		createController();
	}
	
	private void createComponent(){
		consumptionUnitLabel = new JLabel("W");
		consumptionValueLabel = new JLabel("0");
		consuptionLabel = new JLabel("Consumption : ");
		serviceListLabel = new JLabel("List of Services");
		
		String[] columnNames = {"Service Name",	"Implementation Name", "State", "Consumption"};
		Object[][] o = {{"", "", "", ""}};
		table = new JTable(o, columnNames);
	}
	private void placeComponent(){
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel(new FlowLayout());{
			p1.add(consuptionLabel);
			p1.add(consumptionValueLabel);
			p1.add(consumptionUnitLabel);
			
			add(p1, BorderLayout.NORTH);
		}
		
		add(serviceListLabel, BorderLayout.CENTER);
		
		add(table, BorderLayout.SOUTH);
		
	}
	private void createController(){
		
	}
}
