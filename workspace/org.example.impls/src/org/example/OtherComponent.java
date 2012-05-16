package org.example;

import org.example.api.Greeting;

import aQute.bnd.annotation.component.*;

@Component
public class OtherComponent implements Greeting {

	@Override
	public String sayHello(String name) {
		
		return "Bonjour "+ name;
	}

}
