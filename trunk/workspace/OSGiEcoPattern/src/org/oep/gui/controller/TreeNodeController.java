package org.oep.gui.controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.oep.core.BundleManager;
import org.osgi.framework.Bundle;

public class TreeNodeController implements Observer {
	DefaultTreeModel root;
	BundleManager installer;
	
	public TreeNodeController(DefaultTreeModel root, BundleManager installer) {
		this.root = root;
		
		this.installer = installer;
		this.installer.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Object obj = root.getRoot(); 
		if(obj instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode r = (DefaultMutableTreeNode) obj;
			
			r.removeAllChildren();
			
			DefaultMutableTreeNode dmtn;
			for(String s : installer.getInstalledAPIBundle()){
				dmtn = new DefaultMutableTreeNode(s);
				
				for(Bundle b : installer.getInstalledServiceBundle(s)) {
					dmtn.add(new DefaultMutableTreeNode(b.getSymbolicName()));
				}
				
				r.add(dmtn);
			}
			
			root.setRoot(r);
		} else {
			System.err.println("Cas étrange !");
		}
	}

}
