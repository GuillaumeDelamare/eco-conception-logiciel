package org.oep.services.exampleservice.impl1;

import org.oep.services.exampleservice.api.ExampleService;

public class ExamplServiceImpl implements ExampleService {
	private double consumption = 5;
	@Override
	public void start() {
	}

	@Override
	public String getName() {
		return "Example Service";
	}

	@Override
	public double getConsuption() {
		return consumption;
	}

	@Override
	public void compute() {
		// TODO Auto-generated method stub
		
	}
}
