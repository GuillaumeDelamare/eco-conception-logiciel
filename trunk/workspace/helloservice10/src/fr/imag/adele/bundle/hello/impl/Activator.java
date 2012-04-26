/**
 * HelloService
 * simple OSGi service.
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
package fr.imag.adele.bundle.hello.impl;

import java.util.Dictionary;
import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;

/**
 * The activator.
 */
public class Activator implements BundleActivator {

    private ServiceRegistration reg;
    
    private String LANGUAGE="fr";

    public void start(BundleContext context) throws BundleException {
	// initialize service properties
	Dictionary props = new Hashtable();
	props.put("description", "a simple example of OSGi service");
	props.put("language", LANGUAGE);
	// register a service implementation
	reg = context.registerService(
			fr.imag.adele.bundle.hello.HelloService.class.getName(),
			new HelloServiceImpl(LANGUAGE),
			props
		);
    }

    public void stop(BundleContext context) throws BundleException {
	// unregister the service
	if (reg != null)
	    reg.unregister();
    }
}
