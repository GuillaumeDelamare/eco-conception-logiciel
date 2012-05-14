package energyDemo.threads.ram;

public class StringThread extends Thread {
	private int time;

	public StringThread(int time) {
		this.time = time;
	}
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()+" - StringThred : Start bad method");
		@SuppressWarnings("unused")
		String s = "";
		for(int i = 0; i<time; i++){
			s+="";
		}
		
		System.out.println(System.currentTimeMillis()+" - StringThred : Bad method done");
		System.out.println(System.currentTimeMillis()+" - StringThred : Start good method");
		
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i<time; i++) {
			buf.append("");
		}
		
		System.out.println(System.currentTimeMillis()+ " - StringThred : Good method done");

	}
}
