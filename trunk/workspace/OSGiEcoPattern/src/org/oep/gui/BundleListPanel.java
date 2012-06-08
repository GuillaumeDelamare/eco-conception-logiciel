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
import javax.swing.tree.DefaultTreeModel;

import org.oep.core.BundleManager;
import org.oep.gui.controller.InstallAPIController;
import org.oep.gui.controller.InstallImplController;
import org.oep.gui.controller.TreeNodeController;
import org.osgi.framework.Bundle;

public class BundleListPanel extends JPanel {
	private static final long serialVersionUID = 6244150962254780615L;
	
	private BundleManager installer;
	
	private DefaultTreeModel root;
	
	private JLabel serviceListLabel;
	private JTree serviceListTree;
	private JButton installServiceButton, removeServiceButton, installImplButton, removeImplButton;
	
	
	public BundleListPanel(BundleManager installer) {
		this.installer = installer;
		
		createTreeNode();
		
		createComponent();
		placeComponent();
		createController();
	}
	
	private void createComponent(){
		this.serviceListLabel = new JLabel("Services list :");
		
		this.serviceListTree = new JTree(root);
		this.serviceListTree.setRootVisible(false);
		
		this.installImplButton = new JButton("Install impl");
		this.installServiceButton = new JButton("Install Service");
		this.removeImplButton = new JButton("Remove impl");
		this.removeServiceButton = new JButton("Remove Services");
	}
	
	private void placeComponent() {
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
		new TreeNodeController(root, installer);
		installServiceButton.addActionListener(new InstallAPIController(installer));
		installImplButton.addActionListener(new InstallImplController(installer));
	}
	
	private void createTreeNode() {
		DefaultMutableTreeNode r = new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode dmtn;
		for(String s : installer.getInstalledAPIBundle()){
			dmtn = new DefaultMutableTreeNode(s);
			
			for(Bundle b : installer.getInstalledServiceBundle(s)) {
				dmtn.add(new DefaultMutableTreeNode(b.getSymbolicName()));
			}
			
			r.add(dmtn);
		}
		
		root = new DefaultTreeModel(r);
	}
}
