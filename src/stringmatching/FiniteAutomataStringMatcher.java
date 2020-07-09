package stringmatching;

public class FiniteAutomataStringMatcher {

	private static final int N_CHARS = 256;
	
	/**
	 * O(m * N_chars) -> to compute transition function.
	 * 
	 * O(n) -> to match string
	 * 
	 * Total time = O(m * N_chars) + O(n);
	 * 
	 * 
	 * @param str
	 * @param pattern
	 */
	
	public void matchString(String str, String pattern) {
		
		int[][] tf = computeTransitionFunction(pattern);
		
		int n = str.length();
		
		int m = pattern.length();
		
		// initial state is 0
		int state = 0;
		
		for (int i = 0; i < n; i++) {
			
			state = tf[state][str.charAt(i)];
			
			if (state == m) {
				
				System.out.println("Pattern found at index: " + (i - m + 1));
			}
		}
	}
	
	
	private int[][] computeTransitionFunction(String pattern) {
		
		int m = pattern.length();
		
		int[][] tf = new int[m + 1][N_CHARS];
		
		for (int i = 0; i <= m; i++) {
			
			for (int j = 0 ; j < N_CHARS; j++) {
				
				tf[i][j] = getNextTransitionState(pattern, i, j);
			}
		}
		
		return tf;
	}

	private int getNextTransitionState(String pattern, int currentState, int currentChar) {
		
		if (currentState < pattern.length() && pattern.charAt(currentState) == currentChar) {
			
			return currentState + 1;
		}
		
		/**
		 *  let len denote the longest length prefix in pattern (identified till currentState), which can possibly be 
		 *  suffix in (pattern + currentChar) 
		 */
		for (int len = currentState; len > 0; len--) {
			
			// if the condition matches then, there is a chance that the prefix with longest length
			//equal to the currentState might be possible.
			// if the condition does't match then we reduce the length by 1
			
			if (pattern.charAt(len - 1) == currentChar) {
				
				int i = 0;
				
				while (i < len - 1) {
					
					if (pattern.charAt(i) != pattern.charAt(currentState - len + i + 1)) {
						break;
					}
					
					i++;
				}
				
				if (i == len - 1) {
					
					return len;
				}
			}
		}
		
		return 0;
	}
}
