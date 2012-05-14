package energyDemo.threads.cpu;

public class FactorielThread extends Thread{
	private int n;
	
	public FactorielThread(int n) {
		this.n = n;
	}
	
	
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()+" - FactorialThread : Start");
		long r = factorielle(this.n);
		
		System.out.println(System.currentTimeMillis()+" - FactorialThread : Done - Result = "+r);
	
	}
	
	private long factorielle(int n) {
		if(n==0) {
			return 1;
		}
		else {
			return n * factorielle(n - 1);
		}
	}
}
