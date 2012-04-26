package energyDemo.threads;

public class SleepThread extends Thread {
	public SleepThread() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		System.out.println(System.currentTimeMillis()+" - SleepThread : Start");
		while(!isInterrupted()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		System.out.println(System.currentTimeMillis()+" - SleepThread : Done");
	}
}
