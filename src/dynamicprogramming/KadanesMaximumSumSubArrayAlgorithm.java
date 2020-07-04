package dynamicprogramming;

/**
 * KADANE ALGORITHM FOR MAXIMUM SUM CONTIGUOUS SUB ARRAY:
 * 
 * 
 * Terminology: -> max: maximum sum of sub array (to be returned as result).
 * 				   tempMax: maximum sum of contiguous elements traced so far.
 * 				   startIdx: Starting index of maximum sub array.
 * 				   endIdx: Ending index of maximum sub array.
 * 
 * Explanation: 1. Initialize max and tempMax with the first element of array.
 * 				2. Iterate over the elements starting from index 1 and calculate the maximum sum
 * 				of contiguous elements as you iterate over them and store it in tempMax variable.
 * 				3. If the value of tempMax becomes greater than max, assign value of tempMax to max.
 * 				4. At the end max will contain the maximum sum of sub array.
 * 
 * Complexity: O(n)
 * 
 * 
 * @author pranjal
 *
 */

import java.util.Arrays;

public class KadanesMaximumSumSubArrayAlgorithm {

	public static void main(String[] args) {
		
		int arr[] = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		
		int[] result = new int[3];
		
		int startIdx = 0;
		
		int endIdx = 0;
		
		int max = arr[0];
		
		int tempMax = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			
			if (arr[i] < tempMax + arr[i]) {
				
				tempMax = tempMax + arr[i];
				
				endIdx++;
			}
			else {
				
				tempMax = arr[i];
				
				startIdx = endIdx = i;
			}
			
			if (tempMax > max) {
				
				max = tempMax;
				
				result[0] = startIdx;
				
				result[1] = endIdx;
			}
			
		}
		
		result[2] = max;
		
		System.out.println(Arrays.toString(result));

	}

}
