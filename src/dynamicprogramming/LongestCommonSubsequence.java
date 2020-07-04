package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequence {

	public static void main(String[] args) {

		LongestCommonSubsequence lcs = new LongestCommonSubsequence();

		String a = "abaaba";

		String b = "babbab";

		int[][] T = new int[a.length() + 1][b.length() + 1];

		/*
		 * int result = lcs.length(a, b, T);
		 * 
		 * List<String> lcsList = lcs.printAllLCS(a, b, a.length(), b.length(), T);
		 * 
		 * System.out.println(lcsList.toString());
		 */

		int length = lcs.getLongestCommonSubsequenceMemoized(a, b, 0, 0, T);

		System.out.println(length);
	}

	public int length(String a, String b, int[][] T) {

		int m = a.length();

		int n = b.length();

		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {

				if (a.charAt(i - 1) == b.charAt(j - 1))
					T[i][j] = T[i - 1][j - 1] + 1;
				else
					T[i][j] = Math.max(T[i - 1][j], T[i][j - 1]);
			}
		}

		return T[m][n];
	}

	public String printLCS(String a, String b, int i, int j, int[][] T) {

		if (i == 0 || j == 0)
			return "";

		if (a.charAt(i - 1) == b.charAt(j - 1))
			return printLCS(a, b, i - 1, j - 1, T) + a.charAt(i - 1);
		else {

			if (T[i - 1][j] > T[i][j - 1]) {

				return printLCS(a, b, i - 1, j, T);
			} else {
				return printLCS(a, b, i, j - 1, T);
			}
		}
	}

	/**
	 * print all LCS. Traces all paths in the table T and stores all strings in
	 * List.
	 * 
	 * @param a
	 * @param b
	 * @param i
	 * @param j
	 * @param T
	 * @return
	 */
	public List<String> printAllLCS(String a, String b, int i, int j, int[][] T) {

		if (i == 0 || j == 0) {

			ArrayList<String> list = new ArrayList<String>();

			list.add("");

			return list;
		}

		if (a.charAt(i - 1) == b.charAt(j - 1)) {

			List<String> list = printAllLCS(a, b, i - 1, j - 1, T);

			for (int k = 0; k < list.size(); k++) {

				list.set(k, list.get(k) + a.charAt(i - 1));
			}

			return list;
		} else {

			if (T[i - 1][j] > T[i][j - 1]) {

				return printAllLCS(a, b, i - 1, j, T);
			}
			if (T[i - 1][j] < T[i][j - 1]) {
				return printAllLCS(a, b, i, j - 1, T);
			}

			List<String> top = printAllLCS(a, b, i - 1, j, T);

			List<String> left = printAllLCS(a, b, i, j - 1, T);

			top.addAll(left);

			return top;
		}
	}

	/**
	 * Naive recursive implementation
	 * 
	 * @param a
	 * @param b
	 * @param i
	 * @param j
	 * @return
	 */
	public int getLongestCommonSubsequence(String a, String b, int i, int j) {

		if (i == a.length() || j == b.length())
			return 0;

		if (a.charAt(i) == b.charAt(j))
			return 1 + getLongestCommonSubsequence(a, b, i + 1, j + 1);
		else {

			return Math.max(getLongestCommonSubsequence(a, b, i + 1, j), getLongestCommonSubsequence(a, b, i, j + 1));
		}

	}

	/**
	 * Naive recursive implementation
	 * 
	 * @param a
	 * @param b
	 * @param i
	 * @param j
	 * @return
	 */
	public int getLongestCommonSubsequenceMemoized(String a, String b, int i, int j, int[][] T) {

		if (i == a.length() || j == b.length())
			return 0;

		if (T[i + 1][j + 1] != 0)
			return T[i + 1][j + 1];

		if (a.charAt(i) == b.charAt(j)) {

			T[i + 1][j + 1] = 1 + getLongestCommonSubsequenceMemoized(a, b, i + 1, j + 1, T);

		} else {

			T[i + 1][j + 1] = Math.max(getLongestCommonSubsequenceMemoized(a, b, i + 1, j, T),
					getLongestCommonSubsequenceMemoized(a, b, i, j + 1, T));
		}

		return T[i + 1][j + 1];

	}

}
