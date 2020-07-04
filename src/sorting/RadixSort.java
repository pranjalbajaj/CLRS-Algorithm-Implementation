/**
 * RADIX SORT:
 * 
 * Terminology: -> A -> Input Array
 * 				-> B -> Array to store sorted result.
 * 				-> C -> Array to store count/frequency.
 * 
 * Explanation: It is not a comparison sort algorithm. 
 * 				The basic idea of radix sort is to pick the digits of the numbers in array, 
 * 				starting from least significant digit of each number in the array and use 
 * 				stable counting sort to sort the numbers based that digit only in the array
 * 				and then repeat the process till you reach the most significant digit.
 * 				At the end we will have the sorted array.
 * 
 * Complexity: d = maximum number of digits in the numbers given in array
 * 				
 * 			   O(d*n);
 * 
 * 
 * @author pranjal
 *
 */

package sorting;

public class RadixSort extends Sort {
	
	public void sort(int[] A) {
		
		int max = getMaxElement(A);
		
		for (int i = 1; max/i > 0; i*=10) {
			
			sortStable(A, i);
		}
		
	}
	
	private int getMaxElement(int[] a) {
		
		int max = a[0];
		
		for (int i = 1; i < a.length; i++) {
			
			if (a[i] > max)
				max = a[i];
		}
		
		return max;
	}

	public void sortStable(int[] A, int k) {
		
		int[] B = new int[A.length];
		
		int[] C = new int[10];
		
		for (int i = 0; i < A.length; i++) {
			
			C[(A[i]/k)%10]+=1;
		}
		
		for (int i = 1; i < C.length; i++) {
			
			C[i] = C[i] + C[i - 1];
			
		}
		
		for (int i = A.length - 1; i >= 0; i--) {
			
			B[C[(A[i]/k)%10] - 1] = A[i];
			
			C[(A[i]/k)%10]--;
		}
		
		for (int i = 0 ; i < A.length; i++) {
			
			A[i] = B[i];
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
