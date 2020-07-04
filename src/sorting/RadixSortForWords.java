/**
 * Refer Radix Sort
 */
package sorting;

public class RadixSortForWords extends Sort {
	
	public void sort(String[] A) {
		
		int max = getMaxStringLength(A);
		
		for (int i = max - 1; i >= 0; i--) {
			
			sortStable(A, i);
		}
		
	}
	
	private int getMaxStringLength(String[] a) {
		
		int max = a[0].length();
		
		for (int i = 1; i < a.length; i++) {
			
			if (a[i].length() > max)
				max = a[i].length();
		}
		
		return max;
	}

	public void sortStable(String[] A, int k) {
		
		String[] B = new String[A.length];
		
		int[] C = new int[26];
		
		for (int i = 0; i < A.length; i++) {
			
			int charIndex = getCharIndex(A, k, i);
			
			C[charIndex]+=1;
		}
		
		for (int i = 1; i < C.length; i++) {
			
			C[i] = C[i] + C[i - 1];
			
		}
		
		for (int i = A.length - 1; i >= 0; i--) {
			
			int index = getCharIndex(A, k, i);
			
			B[C[index] - 1] = A[i];
			
			C[index]--;
		}
		
		for (int i = 0 ; i < A.length; i++) {
			
			A[i] = B[i];
		}
		
	}
	
	public int getCharIndex(String[] A, int k, int i) {
		
		int charIndex = 0;
		
		if (A[i].length() > k) {
			
			charIndex = A[i].charAt(k) - 'A';
		}
		
		return charIndex;
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
