package energyDemo.threads.cpu;

public class ReturnTestThread extends Thread {
	private static final int ITERATION = 10000000;
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()+" - RetunrTestThread return null : Start");
		returnNullTest();
		System.out.println(System.currentTimeMillis()+" - RetunrTestThread return null : Done");
		System.out.println(System.currentTimeMillis()+" - RetunrTestThread return exception : Start");
		returnExceptionTest();
		System.out.println(System.currentTimeMillis()+" - RetunrTestThread return exception : Done");
	}
	
	private void returnNullTest() {
		String s;
		for(int i = 0; i < ITERATION; i++){
			s = fooNull();
			
			if(s != null){
				// traitement
			}
			else {
				// correction
			}
		}
	}
	private String fooNull() {
		if(null == null) {
			return null;
		}
		else {
			return "toto";
		}
	}

	private void returnExceptionTest() {
		for(int i = 0; i < ITERATION; i++){
			try{
				fooException();
				
				// traitement 
			}
			catch (IllegalStateException e) {
				// correction
			}
		}
	}
	private void fooException() {
		throw new IllegalStateException();
	}
}
