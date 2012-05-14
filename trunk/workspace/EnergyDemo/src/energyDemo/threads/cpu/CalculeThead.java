package energyDemo.threads.cpu;

public class CalculeThead extends Thread {
	public void run() {
		@SuppressWarnings("unused")
		long x;
		
		System.out.println(System.currentTimeMillis()+" - ComputeThread : Start");
		
		while (!isInterrupted()) {		
			x = 13 * 17 + 15;
		}
		
		System.out.println(System.currentTimeMillis()+" - ComputeThread : Done");
	}
}
