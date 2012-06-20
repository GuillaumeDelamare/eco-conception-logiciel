package org.oep.services.exampleservice.impl2;

import org.oep.services.exampleservice.api.ExampleService;

public class ExamplServiceImpl implements ExampleService {
	private double consumption = 2.0;

	@Override
	public double getConsumption() {
		consumption+=(Math.random()*0.2)-0.07;
		return consumption;
	}

	@Override
	public void compute() {
	}

}
