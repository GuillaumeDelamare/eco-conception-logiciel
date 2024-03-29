package fr.guillaume.sorter.sortthreads;

public class QuickSortThread<T extends Comparable<T>> extends AbstractSortThread<T> {

	public QuickSortThread(T[] tab) {
		super(tab);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sort() {
		quickSort(0, tab.length-1);
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
			if(tab[i].compareTo(tab[last])<0) {
				swap(i, j);
				j++;
			}
		}
		swap(last, j);
		return j;
	}
	private void swap(int x, int y) {
		T temp = tab[x];
		tab[x] = tab[y];
		tab[y] = temp;
	}

}
