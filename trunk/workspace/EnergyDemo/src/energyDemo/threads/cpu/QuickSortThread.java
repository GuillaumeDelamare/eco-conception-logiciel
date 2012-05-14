package energyDemo.threads.cpu;

public class QuickSortThread extends Thread {
	private int[] tab;
	
	public QuickSortThread(int size) {
		tab = new int[size];
		for(int i=0;i<size;i++){
			tab[i] = (int)(Math.random()*size*10);
		}
		
		
	}
	
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()+" - QuickThread : Start");
		quickSort(0, tab.length-1);
		System.out.println(System.currentTimeMillis()+" - QuickThread : Done");
	}
	
	private void quickSort(int first, int last) {
		if(first < last){
			int pivot = pivot(first, last);
			pivot = partitionner(first, last, pivot);
			quickSort(first, pivot-1);
			quickSort(pivot+1, last);
		}

	}
	
	private int pivot(int first, int last) {
		return (int)(first + Math.random() * (last - first));
	}
	private int partitionner(int first, int last, int pivot) {
		swap(pivot, last);
		
		int j = first;
		
		for(int i=first;i<last;i++) {
			if(tab[i]<tab[last]) {
				swap(i, j);
				j++;
			}
		}
		swap(last, j);
		return j;
	}
	private void swap(int x, int y) {
		int temp = tab[x];
		tab[x] = tab[y];
		tab[y] = temp;
	}
	
	@Override
	public String toString() {
		String s="";
		
		for(int i : tab) {
			s+=i+" ";
		}
		return s;
	}
}
