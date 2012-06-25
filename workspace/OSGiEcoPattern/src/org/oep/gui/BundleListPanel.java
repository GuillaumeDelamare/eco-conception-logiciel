package org.oep.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.oep.core.BundleManager;
import org.oep.gui.controller.InstallAPIController;
import org.oep.gui.controller.InstallImplController;
import org.oep.gui.controller.RemoveAPIController;
import org.oep.gui.controller.RemoveImplController;
import org.oep.gui.controller.TreeNodeController;
import org.osgi.framework.Bundle;

public class BundleListPanel extends JPanel {
	private static final long serialVersionUID = 6244150962254780615L;
	
	private BundleManager bundleManager;
	
	private DefaultTreeModel root;
	
	private JLabel serviceListLabel;
	private JTree serviceListTree;
	private JButton installAPIButton, removeAPIButton, installImplButton, removeImplButton;
	
	
	public BundleListPanel(BundleManager bundleManager) {
		this.bundleManager = bundleManager;
		
		createTreeNode();
		
		createComponent();
		placeComponent();
		createController();
	}
	
	private void createComponent(){
		this.serviceListLabel = new JLabel("Services list :");
		
		this.serviceListTree = new JTree(root);
		this.serviceListTree.setRootVisible(false);
		
		this.installImplButton = new JButton("Install Bundle");
		this.installAPIButton = new JButton("Install Bundle Family");
		this.removeImplButton = new JButton("Remove Bundle");
		this.removeAPIButton = new JButton("Remove Bundle Family");
	}
	
	private void placeComponent() {
		this.setLayout(new BorderLayout());
		
		this.add(serviceListLabel, BorderLayout.NORTH);
		
		JScrollPane jsp = new JScrollPane(serviceListTree);{
			this.add(jsp, BorderLayout.CENTER);
		}
		
		JPanel p1 = new JPanel(new GridLayout(2, 1));{
			JPanel p2 = new JPanel(new FlowLayout());{
				p2.add(installAPIButton);
				p2.add(removeAPIButton);
				
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
		new TreeNodeController(root, bundleManager);
		installAPIButton.addActionListener(new InstallAPIController(bundleManager));
		installImplButton.addActionListener(new InstallImplController(bundleManager));
		removeAPIButton.addActionListener(new RemoveAPIController());
		removeImplButton.addActionListener(new RemoveImplController(bundleManager));
	}
	
	private void createTreeNode() {
		DefaultMutableTreeNode r = new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode dmtn;
		for(String s : bundleManager.getInstalledAPIBundle()){
			dmtn = new DefaultMutableTreeNode(s);
			
			for(Bundle b : bundleManager.getInstalledServiceBundle(s)) {
				dmtn.add(new DefaultMutableTreeNode(b.getSymbolicName()));
			}
			
			r.add(dmtn);
		}
		
		root = new DefaultTreeModel(r);
	}
}
