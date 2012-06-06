package org.oep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.oep.core.api.Installer;
import org.oep.core.api.ServiceManager;
import org.oep.gui.controller.TreeNodeController;
import org.osgi.framework.Bundle;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -4909438209076360578L;
	private ServiceManager sm;
	private Installer installer;
	
	private DefaultMutableTreeNode root;
	
	private JLabel serviceListLabel;
	private JTree serviceListTree;
	private JButton installServiceButton, removeServiceButton, installImplButton, removeImplButton;
	
	public MainFrame(ServiceManager sm, Installer installer) {
		this.sm = sm;
		this.installer = installer;
		
		createTreeNode();
		
		createComponent();
		placeComponent();
		createController();
	}
	
	private void createComponent(){
		this.serviceListLabel = new JLabel("Service list :");
		
		this.serviceListTree = new JTree(root);
		this.serviceListTree.setRootVisible(false);
		
		this.installImplButton = new JButton("Install impl");
		this.installServiceButton = new JButton("Install Service");
		this.removeImplButton = new JButton("Remove impl");
		this.removeServiceButton = new JButton("Remove Services");
	}
	
	private void placeComponent() {
		this.setTitle("OSGiEcoPattern");
		this.setLayout(new BorderLayout());
		
		this.add(serviceListLabel, BorderLayout.NORTH);
		
		JScrollPane jsp = new JScrollPane(serviceListTree);{
			this.add(jsp, BorderLayout.CENTER);
		}
		
		JPanel p1 = new JPanel(new GridLayout(2, 1));{
			JPanel p2 = new JPanel(new FlowLayout());{
				p2.add(installServiceButton);
				p2.add(removeServiceButton);
				
				p1.add(p2);
			}
			
			JPanel p3 = new JPanel(new FlowLayout());{
				p3.add(installImplButton);
				p3.add(removeImplButton);
				
				p1.add(p3);
			}
			
			this.add(p1, BorderLayout.SOUTH);
		}
	}
	
	private void createController() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new TreeNodeController(root, installer);
	}
	
	private void createTreeNode() {
		root = new DefaultMutableTreeNode("root");
		
		DefaultMutableTreeNode dmtn;
		for(String s : installer.getInstalledAPIBundle()){
			dmtn = new DefaultMutableTreeNode(s);
			
			for(Bundle b : installer.getInstalledServiceBundle(s)){
				dmtn.add(new DefaultMutableTreeNode(b.getSymbolicName()));
			}
			
			root.add(dmtn);
		}
	}
	
	public void display(){
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
}
