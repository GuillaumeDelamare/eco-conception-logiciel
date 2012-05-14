package fr.guillaume.sorter.sortthreads;


public abstract class AbstractSortThread<T extends Comparable<T>> extends Thread {
	protected T[] tab;
	
	public AbstractSortThread(T[] tab) {
		this.tab = tab;
	}
	
	@Override
	public void run() {
		sort();
	}
	
	public abstract void sort();
}
