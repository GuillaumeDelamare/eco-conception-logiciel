package org.oep.services.exampleservice.impl2;

import org.oep.services.exampleservice.api.ExampleService;

public class ExamplServiceImpl implements ExampleService {
	private double consumption = 2;
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
		consumption = 3.7;
		
		int x, i = 0;
		
		while(i>100000) {
			i++;
			x = (7*13)/3;
		}
		
		consumption = 0.1;
	}

}
