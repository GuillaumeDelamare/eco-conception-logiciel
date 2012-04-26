/**
 * BindingController. Copyright (C) 2003 Didier Donsez
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Library General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Library General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * 
 * Contact: Didier Donsez (Didier.Donsez@ieee.org) Contributor(s):
 *  
 */
package fr.imag.adele.bundle.util;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class BindingController implements ServiceListener {

	Map/* <ServiceReference,Object> */services;

	String filterStr;

	Filter filter;

	BundleContext context;

	public BindingController(BundleContext context, String filterStr,
			Map services) throws InvalidSyntaxException {
		this.context = context;
		this.filterStr = filterStr;
		this.services = services;
		filter = context.createFilter(filterStr);
	}

	public void fillServices() throws InvalidSyntaxException {
		// get references on service
		ServiceReference[] refs = context.getServiceReferences(null, filterStr);
		if (refs != null) {
			for (int i = 0; i < refs.length; i++) {
				Object svc = context.getService(refs[i]);
				if (svc != null)
					services.put(refs[i], svc);
			}
		}
	}

	public void serviceChanged(ServiceEvent e) {
		ServiceReference servref = e.getServiceReference();
		Object ref;
		switch (e.getType()) {
		case ServiceEvent.REGISTERED:
			if (filter.match(servref)) {
				trace(servref + " (from " + servref.getBundle().getLocation()
						+ ") is added");
				services.put(servref, context.getService(servref));
			}
			;
			break;
		case ServiceEvent.UNREGISTERING:
			ref = services.remove(servref);
			if (ref != null) {
				trace(servref + " is removed");
				context.ungetService(servref);
			}
			break;
		case ServiceEvent.MODIFIED:
			ref = services.get(servref);
			if (ref != null && !filter.match(servref)) {
				trace(servref + " is removed since properties has changed");
				services.remove(servref);
				context.ungetService(servref);
			}
			break;
		}
	}

	void trace(String msg) {
		System.out.println("events: " + msg);
	}
}