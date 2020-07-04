package sorting;

/**
 * QUICK SORT:
 * 
 * Terminology: -> pivot: element whose exact location has to be determined.
 * 
 * Explanation: It's a divide an conquer algorithm. We fix a pivot and then place that pivot in a place 
 * 				where all the elements less than pivot are on left side and all greater than pivot are 
 * 				on right side.
 * 				Then we call this procedure recursively by dividing the arrays. 
 * 
 * Complexity: Best Case: O(n*log(n))
 * 			   Average Case: O(n*log(n)) (Because of randomization)
 * 			   Worst Case: O(n^2)
 * 
 * 
 * @author pranjal
 *
 */

import java.util.Random;

public class QuickSort extends Sort {
	
	public void randomizedQuickSort(int[] arr, int low, int high) {

		if (high > low) {

			int pivotIndex = randomizedPartition(arr, low, high);

			randomizedQuickSort(arr, low, pivotIndex - 1);

			randomizedQuickSort(arr, pivotIndex + 1, high);

		}

	}
	
	public void quickSortWithLastElementAsPivot(int[] arr, int low, int high) {

		if (high > low) {

			int pivotIndex = partitionWithLastElementAsPivot(arr, low, high);

			quickSortWithLastElementAsPivot(arr, low, pivotIndex - 1);

			quickSortWithLastElementAsPivot(arr, pivotIndex + 1, high);

		}

	}
	
	public void quickSortWithFirstElementAsPivot(int[] arr, int low, int high) {

		if (high > low) {

			int pivotIndex = partitionWithFirstElementAsPivot(arr, low, high);

			quickSortWithFirstElementAsPivot(arr, low, pivotIndex - 1);

			quickSortWithFirstElementAsPivot(arr, pivotIndex + 1, high);

		}

	}

	public int partitionWithLastElementAsPivot(int[] arr, int low, int high) {

		int pivotIndex = high;

		int i = low - 1;

		for (int j = low; j < pivotIndex; j++) {

			if (arr[j] <= arr[pivotIndex]) {

				i++;
				
				swap(arr, i, j);
			}
		}

		swap(arr, i + 1, pivotIndex);

		return i + 1;
	}
	
	public int partitionWithFirstElementAsPivot(int[] arr, int low, int high) {

		int pivotIndex = low;

		int i = high + 1;

		for (int j = high; j > pivotIndex; j--) {

			if (arr[j] >= arr[pivotIndex]) {

				i--;

				swap(arr, i, j);
			}
		}

		swap(arr, i - 1, pivotIndex);

		return i - 1;
	}
	
	public int randomizedPartition(int[] arr, int low, int high) {
		
		int randomIndex = getRandomIndex(low, high);
		
		if (randomIndex != high) {
			
			swap(arr, randomIndex, high);
		}

		int pivotIndex = partitionWithLastElementAsPivot(arr, low, high);
		
		return pivotIndex;
		
	}
	
	private int getRandomIndex(int low, int high) {
		
		Random random = new Random();
		
		return random.nextInt(high - low) + low;
	}

	public void swap(int[] arr, int x, int y) {
		
		int temp = arr[x];
		
		arr[x] = arr[y];
		
		arr[y] = temp;
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
