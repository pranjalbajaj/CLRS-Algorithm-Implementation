package stringmatching;

import java.util.HashMap;

/**
 * O((n - m + 1) m) -> worst case, when each window has same hash, but does not
 * match with pattern.
 * 
 * O(n - m + 1) -> average case, amortized
 * 
 * @author pranjal
 *
 */

public class RabinkarpAlgorithm {
	
	 static final int d = 256;

	 /**
	  * 
	  * @param s
	  * @param p
	  * @param q = a prime number.
	  */
	public void matchString(String s, String p, int q) {
		
		int n = s.length();
		
		int m = p.length();
		
		// hash value of pattern
		double patternHash = 0;
		
		// hash value of string
		double stringHash = 0;
		
		double h = (Math.pow(d, m - 1) % q);
		
		for (int i = 0; i < m; i++) {
			
			patternHash = (d*patternHash + p.charAt(i)) % q;
			
			stringHash = (d*stringHash + s.charAt(i)) % q;
		}
		
		for (int i = 0; i < n - m + 1; i++) {
			
			if (patternHash == stringHash) {
				
				for (int k = 0; k < m; k++) {
					
					if (p.charAt(k) != s.charAt(k + i)) {
						
						break;
					}
					
					if (k == m - 1) {
						
						System.out.println("Pattern found at index: " + i);
					}
				}
			}
			
			if (i < n - m) {
				
				// rolling hash
				stringHash = (d*(stringHash - s.charAt(i)*h) + s.charAt(i + m)) % q;
				
				if (stringHash < 0) {
					
					stringHash += q;
				}
			}
		}
		
	}
}
