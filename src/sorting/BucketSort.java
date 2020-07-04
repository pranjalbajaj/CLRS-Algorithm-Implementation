package sorting;

import java.util.ArrayList;
import java.util.List;

public class BucketSort extends Sort {

	public void sort(double[] A) {
		
		int n = A.length;
		
		List<Double>[] B = new List[n];
		
		for (int i = 0; i < n; i++) {
			
			B[i] = new ArrayList<Double>();
			
		}
		
		for (int i = 0; i < n; i++) {
			
			B[(int) (n*A[i])].add(A[i]); 
		}
		
		for (int i = 0; i < n; i++) {
			
			insertionSort(B[i]);
		}
		
		int index = 0;
		
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < B[i].size(); j++) {
			
				A[index++] = (double) B[i].get(j); 	
			}
		}
		
	}
	
	public void insertionSort(List<Double> list) {
		
		for (int j= 1; j < list.size(); j++) {
			
			double pivot = list.get(j);
			
			int i = j - 1;
			
			while (i >= 0 && list.get(i) > pivot) {
				
				list.remove(i + 1);
				
				list.add(i + 1, list.get(i));
				
				i--;
			}
			
			list.remove(i + 1);
			
			list.add(i + 1, pivot);
			
		}
		
	}
	
	@Override
	public void sortAscending(int[] inputArr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortDescending(int[] inputArr) {
		// TODO Auto-generated method stub

	}

}
