package org.oep.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.oep.core.BundleManager;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -4909438209076360578L;
	private BundleManager installer;
	
	private JTabbedPane tabbedPane;
	private BundleListPanel bundleListPanel;
	private RunningListPanel runningListPanel;
	
	
	public MainFrame(BundleManager installer) {
		this.installer = installer;

		this.setTitle("OSGiEcoPattern");
		
		createComponent();
		placeComponent();
		createController();
	}
	
	
	
	private void createController() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	private void placeComponent() {
		tabbedPane.add("Bundle list",bundleListPanel);
		tabbedPane.add("Running list", runningListPanel);
		add(tabbedPane);
	}



	private void createComponent() {
		tabbedPane = new JTabbedPane();
		bundleListPanel = new BundleListPanel(installer);
		runningListPanel = new RunningListPanel(installer);
	}



	public void display(){
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
}
