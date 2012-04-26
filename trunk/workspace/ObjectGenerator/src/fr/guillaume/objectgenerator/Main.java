package fr.guillaume.objectgenerator;

import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringGenerator sg = new StringGenerator(100);
		sg.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> al = sg.getAll();
		
		
		System.out.println(al);
	}

}
