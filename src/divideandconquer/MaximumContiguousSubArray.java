package divideandconquer;

/**
 * MAXIMUM CONTIGUOUS SUBARRAY:
 * 
 * Terminology: -> low = The lower index of the array.
 * 				-> high = The higher index of the array.
 * 				-> mid = The midpoint of the array.
 * 
 * Explanation: It's a divide an conquer algorithm.
 * 				
 * 				There are three possibilities for maximum contiguous sub array to exists.
 * 
 * 					1. Entire sub array lies in the left part.
 * 					2. Entire sub array lies in the right part.
 * 					3. Some part of sub array lies in the left part while the other
 * 					   lies in right part.
 * 
 * Complexity: O(n log n)
 * 
 * 
 * @author pranjal
 *
 */

import java.util.Arrays;

public class MaximumContiguousSubArray {

	public static void main(String[] args) {
		
		int[] arr = { 100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97 };
		
		int[] priceDifferenceArray = createPriceDiffArray(arr);
		
		System.out.println(Arrays.toString(priceDifferenceArray));
		
		int[] result = maximumSubArray(priceDifferenceArray, 0, priceDifferenceArray.length - 1);
		
		System.out.println(Arrays.toString(result));
		
		
	}
	
	/**
	 * 
	 * @param priceDifferenceArray
	 * @param low
	 * @param high
	 * @returns array which contains the starting index, ending index and sum of the maximum sub array.
	 */
	private static int[] maximumSubArray(int[] priceDifferenceArray, int low, int high) {
			
		int result[] = new int[3];
		
		if (low == high) {
				
			result[0] = low;
			result[1] = high;
			result[2] = priceDifferenceArray[low];
			
			return result;
		}
		
		int mid = (low + high) / 2;
		
		int[] resultLeft = maximumSubArray(priceDifferenceArray, low, mid);
		
		int[] resultRight = maximumSubArray(priceDifferenceArray, mid + 1, high);
		
		int[] resultCross = maximumCrossSubArray(priceDifferenceArray, low, mid, high);
		
		if (resultLeft[2] > resultRight[2] && resultLeft[2] > resultCross[2])
			return resultLeft;
		else if (resultLeft[2] < resultRight[2] && resultRight[2] > resultCross[2])
			return resultRight;
		
		return resultCross;
		
		
	}
	
	private static int[] maximumCrossSubArray(int[] priceDifferenceArray, int low, int mid, int high) {
		
		int[] result = new int[3];
		
		int sum = 0;
		
		int maxLeft = 0;
		
		int leftSum = Integer.MIN_VALUE;
		
		for (int i = mid; i >= low; i--) {
			
			sum+=priceDifferenceArray[i];
			
			if (sum > leftSum) {
				
				leftSum = sum;
				
				maxLeft = i;
			}
		}
		
		sum = 0;
		
		int maxRight = 0;
		
		int rightSum = Integer.MIN_VALUE;
		
		for (int i = mid + 1; i <= high; i++) {
			
			sum+=priceDifferenceArray[i];
			
			if (sum > rightSum) {
				
				rightSum = sum;
				
				maxRight = i;
			}
		}
		
		result[0] = maxLeft;
		
		result[1] = maxRight;
		
		result[2] = leftSum + rightSum;
		
		return result;
	}

	private static int[] createPriceDiffArray(int[] arr) {
		
		int[] diffArr = new int[arr.length - 1];
		
		for (int i = 0; i < arr.length - 1; i++) {
			
			diffArr[i] = arr[i+1] - arr[i];
		}
		
		return diffArr;
	}
		

}
