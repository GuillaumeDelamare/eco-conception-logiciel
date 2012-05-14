package fr.guillaume.objectgenerator;

public class StringGenerator extends Generator<String> {
	private final static int MAX_LENGTH = 100; 
	
	public StringGenerator(long sleepTime) {
		super(sleepTime);
	}
	
	@Override
	protected void generate() {
		StringBuffer s= new StringBuffer();
		int size = (int)(Math.random()*MAX_LENGTH);
		
		for(int i = 0; i<size; i++){
			int c = (int)(Math.random()*25)+97;
			
			s.append((char)c);
		}
		
		buffer.add(new String(s));
	}

}
