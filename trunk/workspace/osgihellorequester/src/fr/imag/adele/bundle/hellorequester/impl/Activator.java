/**
 * HelloRequester
 * simple OSGi client.
 * Copyright (C) 2003  Didier Donsez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Contact: Didier Donsez (Didier.Donsez@ieee.org)
 * Contributor(s):
 *
**/
package fr.imag.adele.bundle.hellorequester.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;

import fr.imag.adele.bundle.hello.HelloService;
import fr.imag.adele.bundle.util.BindingController;

public class Activator implements BundleActivator, Runnable {

	final static String filterStr="(&(objectClass=fr.imag.adele.bundle.hello.HelloService)(language=*))";
	final static int DELAY=5000;

	Map/*<ServiceReference,HelloService>*/ helloservices;
	boolean end;
	BundleContext context;

	public void start(BundleContext context) throws BundleException {
		this.context=context;
		helloservices=new HashMap();
		BindingController ctlr;
		try {
			ctlr=new BindingController(context,filterStr,helloservices);
			ctlr.fillServices();
		} catch (InvalidSyntaxException e) {
			throw new BundleException("Invalid LDAP filter", e);
		}
		context.addServiceListener(ctlr);

		end=false;
		new Thread(this).start();
	}

	public void stop(BundleContext context) throws BundleException {
		end=true;
	}

	public synchronized void run() {
		int times=1;
		while( ! end ){
			try {
				Iterator iter=helloservices.values().iterator();

				while(iter.hasNext()){
					HelloService ref=(HelloService)	(iter.next());
					System.out.println(times+":"+ref+ " says '"+ ref.sayHello("Didier")+"'");
				}
				Thread.sleep(DELAY);
			} catch( InterruptedException ie) {
				/* will recheck quit */
			}
			times++;
		}
	}
}


