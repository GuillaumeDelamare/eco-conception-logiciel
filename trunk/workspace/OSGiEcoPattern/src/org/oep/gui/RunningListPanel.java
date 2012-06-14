package org.oep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.oep.core.BundleManager;
import org.oep.core.ServiceManager;
import org.oep.gui.controller.ConsumptionController;
import org.oep.gui.controller.TotalConsumptionController;
import org.oep.gui.controller.StartController;
import org.oep.gui.controller.StopController;
import org.oep.gui.controller.TableModelController;
public class RunningListPanel extends JPanel {
	private static final long serialVersionUID = -2396252588618798929L;
	
	private BundleManager bundleManager;
	private ServiceManager serviceManager;
	
	private DefaultTableModel tableModel;
	
	private JLabel consuptionLabel, consumptionValueLabel, consumptionUnitLabel;
	private JTable table;
	private JButton start, stop;
	
	public RunningListPanel(BundleManager bundleManager, ServiceManager serviceManager) {
		this.bundleManager = bundleManager;
		this.serviceManager = serviceManager;
		
		createComponent();
		placeComponent();
		createController();
	}
	
	private void createComponent(){
		consumptionUnitLabel = new JLabel("W");
		consumptionValueLabel = new JLabel("0");
		consuptionLabel = new JLabel("Total consumption : ");
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("API name");
		tableModel.addColumn("Running implementation");
		tableModel.addColumn("Consumption");
		
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		start = new JButton("Start");
		stop = new JButton("Stop");
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
		
		JPanel p3 = new JPanel(new FlowLayout());{
			p3.add(start);
			p3.add(stop);
			
			add(p3, BorderLayout.SOUTH);
		}
	}
	private void createController(){
		new TableModelController(tableModel, bundleManager);
		new TotalConsumptionController(serviceManager, consumptionValueLabel).start();
		new ConsumptionController(tableModel, serviceManager, bundleManager).start();
		start.addActionListener(new StartController(bundleManager, table));
		stop.addActionListener(new StopController(bundleManager, table));
		
	}
}
