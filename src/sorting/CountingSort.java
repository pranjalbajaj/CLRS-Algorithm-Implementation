/**
 * COUNTING SORT:
 * 
 * Terminology: -> A -> Input Array
 * 				-> B -> Array to store sorted result.
 * 				-> C -> Array to store count/frequency.
 * 
 * Explanation: It is not a comparison sort algorithm. The basic idea of counting sort is to count 
 * 				the number of elements which are less than or equal to the number at hand and then
 * 				by logic determine the location of the element at hand in the sorted array.
 * 
 * Complexity: O(n)
 * 
 * 
 * @author pranjal
 *
 */
package sorting;

public class CountingSort extends Sort {
	
	public int[] sortUnstableForPositiveNumbers(int[] A, int k) {
		
		int[] B = new int[A.length];
		
		// k is the max value possible in input array
		int[] C = new int[k + 1];
		
		for (int i = 0; i < A.length; i++) {
			
			C[A[i]]+=1;
		}
		
		for (int i = 1; i < C.length; i++) {
			
			C[i] = C[i] + C[i - 1];
			
		}
		
		for (int i = 0; i < A.length; i++) {
			
			B[C[A[i]] - 1] = A[i];
			
			C[A[i]]--;
		}
		
		return B;
	}
	

	public int[] sortStableForPositiveNumbers(int[] A, int k) {
		
		int[] B = new int[A.length];
		
		// k is the max value possible in input array
		int[] C = new int[k + 1];
		
		for (int i = 0; i < A.length; i++) {
			
			C[A[i]]+=1;
		}
		
		for (int i = 1; i < C.length; i++) {
			
			C[i] = C[i] + C[i - 1];
			
		}
		
		for (int i = A.length - 1; i >= 0; i--) {
			
			B[C[A[i]] - 1] = A[i];
			
			C[A[i]]--;
		}
		
		return B;
	}
	
	public int[] sortStableForAllNumbers(int[] A) {
	
		int[] maxMin = getMaxAndMinElement(A);
		
		int min = maxMin[0];
				
		int max = maxMin[1];
		
		int[] B = new int[A.length];
		
		// k is the max value possible in input array
		int[] C = new int[max - min + 1];
		
		for (int i = 0; i < A.length; i++) {
			
			C[A[i] - min]+=1;
		}
		
		for (int i = 1; i < C.length; i++) {
			
			C[i] = C[i] + C[i - 1];
			
		}
		
		for (int i = A.length - 1; i >= 0; i--) {
			
			B[C[A[i] - min] - 1] = A[i];
			
			C[A[i] - min]--;
		}
		
		return B;
	}
	
	private int[] getMaxAndMinElement(int[] a) {
		
		int[] result = new int[2];
		
		int max = a[0];
		
		int min = a[0];
		
		for (int i = 1; i < a.length; i++) {
			
			if (a[i] > max)
				max = a[i];
			if (a[i] < min)
				min = a[i];
		}
		
		result[0] = min;
		
		result[1] = max;
		
		return result;
	}
	
	@Override
	public void sortAscending(int[] inputArr) {
		

	}

	@Override
	public void sortDescending(int[] inputArr) {
		// TODO Auto-generated method stub

	}

}
