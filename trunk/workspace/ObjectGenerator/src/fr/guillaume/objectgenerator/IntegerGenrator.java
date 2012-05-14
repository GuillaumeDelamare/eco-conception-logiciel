package fr.guillaume.objectgenerator;

public class IntegerGenrator extends Generator<Integer> {

	public IntegerGenrator(long sleepTime) {
		super(sleepTime);
	}

	@Override
	protected void generate() {
		buffer.add(Integer.valueOf((int)(Math.random()*100)));
	}

}
