package stringmatching;

/**
 * O(m) -> for preprocessing
 * 
 * O(n) -> for string matching
 * 
 * O(m + n)
 */

import java.util.Arrays;

public class KMPAlgorithm {
	
	
	/**
	 * Matches pattern against the pattern
	 * @param pattern
	 * @return
	 */
	private int[] computePrefixFunction(String pattern) {
		
		int m = pattern.length();
		
		int[] prefixArray = new int[m];
		
		prefixArray[0] = 0;
		
		int k = 0;
		
		for (int q = 1; q < m; q++) {
			
			while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
				
				k = prefixArray[k - 1];
			}
			
			if (pattern.charAt(k) == pattern.charAt(q)) {
				
				k = k + 1;
			}
			
			prefixArray[q] = k;
		}
		
		return prefixArray;
	}
	
	/**
	 * Matches text against the pattern
	 * @param text
	 * @param pattern
	 */
	public void stringMatcher(String text, String pattern) {
		
		int n = text.length();
		
		int m = pattern.length();
		
		int[] prefixArr = computePrefixFunction(pattern);
		
		System.out.println(Arrays.toString(prefixArr));
		
		int q = 0;
		
		for (int i = 0 ; i < n; i++) {
			
			while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
				
				q = prefixArr[q - 1];
			}
			
			if (pattern.charAt(q) == text.charAt(i)) {
				
				q = q + 1;
			}
			
			if (q == m) {
				
				System.out.println("Pattern found at index: " + (i - m));
				
				q = prefixArr[q - 1];
			}
		}
		
	}

}
