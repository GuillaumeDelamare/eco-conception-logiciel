package org.example;

import junit.framework.TestCase;

public class ExampleComponentTest extends TestCase {

	public void testSaysHello() throws Exception {
		String result = new ExampleComponent().sayHello("Bob");
		assertEquals("Hello Bob", result);
	}
}
