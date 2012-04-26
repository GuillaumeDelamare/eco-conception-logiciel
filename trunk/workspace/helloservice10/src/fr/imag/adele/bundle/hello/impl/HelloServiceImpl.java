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

import fr.imag.adele.bundle.hello.HelloService;

public class HelloServiceImpl implements HelloService {
	private String language;

	public HelloServiceImpl() {
		setLanguage("en");
	}

	public HelloServiceImpl(String language) {
		setLanguage(language);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String sayHello(String name) {
		if (language.equals("fr")) {
			return "\nSalut " + name + " !!\n";
		} else if (language.equals("it")) {
			return "\nCiao " + name + " !!\n";
		} else if (language.equals("es")) {
			return "\nHola " + name + " !!\n";
		} else if (language.equals("en-us")) {
			return "\nHi " + name + " !!\n";
		} else if (language.equals("de")) {
			return "\nHallo " + name + " !!\n";
		} else {
			return "\nHello " + name + " !!\n";
		}
	}
}


