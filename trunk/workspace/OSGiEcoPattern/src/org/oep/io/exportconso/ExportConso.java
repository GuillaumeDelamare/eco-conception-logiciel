package org.oep.io.exportconso;

import java.io.FileWriter;
import java.io.IOException;

import org.oep.core.ServiceManager;


public class ExportConso extends Thread{
	private int freq;
	private FileWriter fw;
	private ServiceManager manager;
	
	public ExportConso(ServiceManager manager) throws IOException {
		this(1000, "./conso.csv", manager);
	}
	public ExportConso(int freq, ServiceManager manager) throws IOException{
		this(freq, "./conso.csv", manager);
	}
	public ExportConso(int freq, String path, ServiceManager manager) throws IOException{
		fw = new FileWriter(path);
		fw.write("Watts\n");
		this.freq = freq;
		
		this.manager = manager;
	}
	
	public void run() {
		while(true) {
			try {
				sleep(freq);
				
				fw.write(String.valueOf(manager.getTotalConsumption())+"\n");
				fw.flush();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
