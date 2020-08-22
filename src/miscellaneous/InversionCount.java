package miscellaneous;

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

public class InversionCount {
	
	public static void main(String[] args) {
		
		InversionCount invc = new InversionCount();
		
		int[] inputArr = {1, 20, 6, 4, 5};
		
		int count = invc.mergeSort(inputArr, 0, inputArr.length - 1);
		
		System.out.println(count);
	}
	
	public int mergeSort(int[] inputArr, int leftIndex, int rightIndex) {
		
		int count = 0;

		if (leftIndex < rightIndex) {
			
			int middleIndex = (leftIndex + rightIndex) / 2;

			count += mergeSort(inputArr, leftIndex, middleIndex);

			count += mergeSort(inputArr, middleIndex + 1, rightIndex);

			count += merge(inputArr, leftIndex, middleIndex, rightIndex);
		}
		
		return count;
	}
	
	public int merge(int[] inputArr, int leftIndex, int middleIndex, int rightIndex) {
		
		int i = leftIndex;
		
		int j = middleIndex + 1;
		
		int k = 0;
		
		int count = 0;
		
		int[] mergedArray = new int[rightIndex - leftIndex + 1];
		
		while (i <= middleIndex && j <= rightIndex) {
			
			if (inputArr[i] <= inputArr[j]) {
				
				mergedArray[k] = inputArr[i];
				
				i++;
			}
			else {
				
				mergedArray[k] = inputArr[j];
				
				j++;
				
				count += (middleIndex + 1) - i;
				
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
		
		return count;
		
	}

}
