package fr.guillaume.objectgenerator;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Generator<T extends Comparable<T>> extends Thread{
	protected ArrayList<T> buffer;
	protected long sleepTime;
	protected ReentrantLock lock; 
	
	public Generator(long sleepTime) {
		this.buffer = new ArrayList<T>();
		this.sleepTime = sleepTime;
		this.lock = new ReentrantLock();
	}
	
	public T getNext(){
		lock.lock();
		
		T res = buffer.remove(0);
		
		lock.unlock();
		
		return res;
	}
	
	public ArrayList<T> getAll(){
		//TODO instanceof
		lock.lock();
		
		ArrayList<T> res = new ArrayList<T>(this.buffer);
		this.buffer.clear();
		
		lock.unlock();
		
		return res;
	}
	
	protected abstract void generate();
	
	@Override
	public void run() {
		while(true){
			lock.lock();
			
			this.generate();
			
			lock.unlock();
			try {
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
