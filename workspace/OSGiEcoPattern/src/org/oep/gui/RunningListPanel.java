package org.oep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.oep.core.BundleManager;
import org.oep.gui.controller.TableModelController;
public class RunningListPanel extends JPanel {
	private static final long serialVersionUID = -2396252588618798929L;
	
	private BundleManager installer;
	
	private DefaultTableModel tableModel;
	
	private JLabel consuptionLabel, consumptionValueLabel, consumptionUnitLabel;
	private JTable table;
	
	public RunningListPanel(BundleManager installer) {
		this.installer = installer;
		
		createComponent();
		placeComponent();
		createController();
	}
	
	private void createComponent(){
		consumptionUnitLabel = new JLabel("W");
		consumptionValueLabel = new JLabel("0");
		consuptionLabel = new JLabel("Total consumption : ");
		
		String[] columnNames = {"Service Name",	"Implementation Name", "State", "Consumption"};
		Object[][] o = {};
		
		tableModel = new DefaultTableModel(o, columnNames);
		table = new JTable(tableModel);
	}
	private void placeComponent(){
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel(new FlowLayout());{
			p1.add(consuptionLabel);
			p1.add(consumptionValueLabel);
			p1.add(consumptionUnitLabel);
			
			add(p1, BorderLayout.NORTH);
		}
		
		JPanel p2 = new JPanel(new BorderLayout());{
			p2.add(table.getTableHeader(), BorderLayout.PAGE_START);
			p2.add(table, BorderLayout.CENTER);
			
			add(p2, BorderLayout.CENTER);
		}
	}
	private void createController(){
		new TableModelController(tableModel, installer);
	}
}
