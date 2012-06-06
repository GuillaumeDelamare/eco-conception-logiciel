package org.oep.gui.controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.DefaultMutableTreeNode;

import org.oep.core.api.Installer;
import org.osgi.framework.Bundle;

public class TreeNodeController implements Observer {
	DefaultMutableTreeNode root;
	//TODO Corriger le nom de la classe IntallerImpl
	//TODO Corriger le lien vers la classe d'implémentation
	Installer installer;
	
	public TreeNodeController(DefaultMutableTreeNode root, Installer installer) {
		this.root = root;
		this.installer = installer;
		
		this.installer.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		root.removeAllChildren();
		
		DefaultMutableTreeNode dmtn;
		for(String s : installer.getInstalledAPIBundle()){
			System.out.println("coucou controller");
			dmtn = new DefaultMutableTreeNode(s);
			
			for(Bundle b : installer.getInstalledServiceBundle(s)){
				dmtn.add(new DefaultMutableTreeNode(b.getSymbolicName()));
			}
			
			root.add(dmtn);
		}
	}

}
