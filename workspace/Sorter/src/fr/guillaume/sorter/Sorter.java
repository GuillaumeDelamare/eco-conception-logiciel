package fr.guillaume.sorter;

import java.util.ArrayList;

import fr.guillaume.objectgenerator.Generator;
import fr.guillaume.objectgenerator.IntegerGenrator;
import fr.guillaume.sorter.sortthreads.QuickSortThread;

public class Sorter {
	private final Integer test;
	public Sorter() {
		test=0;
	}
	
	
	public static void main(String[] args) {
		Generator generator = new IntegerGenrator(1);
		
		generator.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Integer> tab = generator.getAll();
		Integer[] i = new Integer[tab.size()];
		tab.toArray(i);

		System.out.println("tableau non trié : ");
		String s = "";
		for (Integer integer : i) {
			s+=integer + " ";
		}
		System.out.println(s);
		
		new QuickSortThread<Integer>(i).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("tableau trié : ");
		s = "";
		for (Integer integer : i) {
			s+=integer + " ";
		}
		System.out.println(s);
		
	}
}