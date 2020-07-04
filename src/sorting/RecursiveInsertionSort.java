package sorting;

/**
 * RECURSIVE INSERTION SORT:
 * 
 * Terminology: -> pivot: Element at hand, which needs to be placed at correct position.
 * 				   pivotIndex : Index of pivot element.
 * 
 * Explanation: Recursive implementation of insertion sort.
 * 
 * Complexity: -> Best Case: When the elements are already sorted : O(n)
 * 			   -> Worst Case: When the elements are reverse sorted: O(n^2)
 * 
 * 
 * @author pranjal
 *
 */

public class RecursiveInsertionSort extends Sort {

	@Override
	public void sortAscending(int[] inputArr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortDescending(int[] inputArr) {
		// TODO Auto-generated method stub

	}
	
	public void sort(int[] inputArr, int pivotIndex) {
		
		int pivot = inputArr[pivotIndex];
		
		int i = pivotIndex;
		
		while (i > 0 && inputArr[i - 1] > pivot) {
			
			inputArr[i] = inputArr[i - 1];
			
			i--;
			
		}
		
		inputArr[i] = pivot;
		
		if (pivotIndex + 1 < inputArr.length) {
			
			sort(inputArr, pivotIndex + 1);
		}
	}

}
