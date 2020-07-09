package stringmatching;

public class NaiveStringMatcher {

	/**
	 * O((n -m + 1)m)
	 * @param s
	 * @param p
	 */
	public void matchString(String s, String p) {
		
		int n = s.length();
		
		int m = p.length();
		
		
		for (int i = 0; i < n - m + 1; i++) {
			
			for (int j = 0; j < m; j++) {

				if (s.charAt(i + j) != p.charAt(j)) {

					break;	
				} 
				if (j == m - 1) {
					
					System.out.println("Pattern found at index: " + i);
				}
			}
		}
	}
}
