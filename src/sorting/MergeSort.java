package sorting;

/**
 * MERGE SORT:
 * 
 * Terminology: -> leftIndex = The starting index of the array.
 * 				-> rightIndex = The end index of the array.
 * 				-> middleIndex = The midpoint of the array.
 * 
 * Explanation: It's a divide an conquer algorithm. Recursively divide the array into two parts and then call the 
 * 				merge procedure to sort the individual arrays. 
 * 
 * Complexity: O(n log n)
 * 
 * 
 * @author pranjal
 *
 */

public class MergeSort extends Sort {

	@Override
	public void sortAscending(int[] inputArr) {
		
		mergeSort(inputArr, 0, inputArr.length - 1);

	}

	@Override
	public void sortDescending(int[] inputArr) {
		// TODO Auto-generated method stub

	}
	
	public void mergeSort(int[] inputArr, int leftIndex, int rightIndex) {

		if (leftIndex < rightIndex) {
			
			int middleIndex = (leftIndex + rightIndex) / 2;

			mergeSort(inputArr, leftIndex, middleIndex);

			mergeSort(inputArr, middleIndex + 1, rightIndex);

			merge(inputArr, leftIndex, middleIndex, rightIndex);
		}
	}
	
	public void merge(int[] inputArr, int leftIndex, int middleIndex, int rightIndex) {
		
		int i = leftIndex;
		
		int j = middleIndex + 1;
		
		int k = 0;
		
		int[] mergedArray = new int[rightIndex - leftIndex + 1];
		
		while (i <= middleIndex && j <= rightIndex) {
			
			if (inputArr[i] <= inputArr[j]) {
				
				mergedArray[k] = inputArr[i];
				
				i++;
			}
			else {
				
				mergedArray[k] = inputArr[j];
				
				j++;
				
			}
			
			k++;
		}
		
		while (i <= middleIndex) {
			
			mergedArray[k] = inputArr[i];
			
			i++;
			
			k++;
		}
		
		while (j <= rightIndex) {
			
			mergedArray[k] = inputArr[j];
			
			j++;
			
			k++;
		}
		
		for (int p = 0; p < mergedArray.length; p++) {
			
			inputArr[leftIndex] = mergedArray[p];
			
			leftIndex++;
			
		}
		
	}

}
