package stringmatching;	

public class Test {

	public static void main(String[] args) {
		
		//testNaiveStringMatcher();
		
		//testRabinKarp();
		
		//testFiniteAutomata();
		
		testKMPAlgo();

	}
	
	private static void testKMPAlgo() {
		
		KMPAlgorithm kmp = new KMPAlgorithm();
		
		kmp.stringMatcher("bacbababaababacaabcbababacab", "ababaca");
		
	}

	private static void testFiniteAutomata() {
		
		FiniteAutomataStringMatcher fasm = new FiniteAutomataStringMatcher();
		
		//fasm.matchString("abababacaba", "ababaca");
		
		fasm.matchString("abaababaabaab", "abaababaabaab");
		
	}

	private static void testRabinKarp() {
		
		RabinkarpAlgorithm rabinkarpAlgorithm = new RabinkarpAlgorithm();
		
		rabinkarpAlgorithm.matchString("acaabcaab", "aab", 101);
		
	}

	public static void testNaiveStringMatcher() {
		
		NaiveStringMatcher naive = new NaiveStringMatcher();
		
		naive.matchString("acaabcaab", "aab");
	}

}
