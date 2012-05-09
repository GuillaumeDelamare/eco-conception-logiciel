package org.example;

import org.example.api.Greeting;

import aQute.bnd.annotation.component.*;

@Component
public class ExampleComponent implements Greeting {

	@Override
	public String sayHello(String name) {
		return "Hello "+ name;
	}
}