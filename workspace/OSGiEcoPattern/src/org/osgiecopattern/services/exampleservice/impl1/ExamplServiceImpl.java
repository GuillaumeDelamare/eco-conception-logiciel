package org.osgiecopattern.services.exampleservice.impl1;

import org.osgiecopattern.services.exampleservice.api.ExampleService;

public class ExamplServiceImpl implements ExampleService {

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "Example Service";
	}

	@Override
	public double getConsuption() {
		return 1.7;
	}

}
