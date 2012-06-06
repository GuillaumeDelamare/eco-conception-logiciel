package org.osep.services.exampleservice.impl1;

import org.osep.services.exampleservice.api.ExampleService;

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
