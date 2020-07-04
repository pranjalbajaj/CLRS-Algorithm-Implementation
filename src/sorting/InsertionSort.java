package sorting;

/**
 * INSERTION SORT:
 * 
 * Terminology: -> pivot: Element at hand, which needs to be placed at correct position.
 * 
 * Explanation: -> It's an in place sorting algorithm
 * 				Pick elements sequentially starting from second index and compare 
 * 				it with all the elements before it, to determine its sorted position.
 * 				All the elements till the pivot will be sorted after each iteration.
 * 
 * Complexity: -> Best Case: When the elements are already sorted : O(n)
 * 			   -> Worst Case: When the elements are reverse sorted: O(n^2)
 * 
 * 
 * @author pranjal
 *
 */

public class InsertionSort extends Sort {

	@Override
	public void sortAscending(int[] inputArr) {
		
		for (int j= 1; j < inputArr.length; j++) {
			
			int pivot = inputArr[j];
			
			int i = j - 1;
			
			while (i >= 0 && inputArr[i] > pivot) {
				
				inputArr[i+1] = inputArr[i];
				
				i--;
			}
			
			inputArr[i + 1] = pivot;
			
		}
		
	}

	@Override
	public void sortDescending(int[] inputArr) {
		
		for (int j= 1; j < inputArr.length; j++) {
			
			int pivot = inputArr[j];
			
			int i = j - 1;
			
			while (i >= 0 && inputArr[i] < pivot) {
				
				inputArr[i+1] = inputArr[i];
				
				i--;
			}
			
			inputArr[i + 1] = pivot;
			
		}
	}

}
