package dynamicprogramming;

public class MatrixChainMultiplication {

	public static void main(String[] args) {

		int[][] p = { { 2, 3 }, { 3, 6 }, { 6, 4 }, { 4, 5 } };

		MatrixChainMultiplication mat = new MatrixChainMultiplication();

		mat.parenthesization(p);
		
		int n = p.length;
		
		int[][] m = new int[n][n];

		int[][] s = new int[n][n];
		
		for (int i = 0; i < n ; i++) {
			
			for (int j = 0; j < n; j++) {
				
				m[i][j] = Integer.MAX_VALUE;
			}
		}
		
		mat.recursiveParenthesization(p, m, s, 0, n -1);
		
		mat.printParenthesisExpression(s, 0, n -1);
		
	}

	public void parenthesization(int[][] p) {

		int n = p.length;

		int[][] m = new int[n][n];

		int[][] s = new int[n][n];

		for (int chainLength = 2; chainLength <= n; chainLength++) {

			for (int i = 0; i < n - chainLength + 1; i++) {

				int j = i + chainLength - 1;

				m[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {

					int q = m[i][k] + m[k + 1][j] + p[i][0] * p[k][1] * p[j][1];

					if (q < m[i][j]) {

						m[i][j] = q;

						s[i][j] = k;
					}
				}
			}
		}

		printParenthesisExpression(s, 0, n - 1);
	}

	private void printParenthesisExpression(int[][] s, int i, int j) {

		if (i == j)
			System.out.print("A" + i);
		else {

			System.out.print("(");

			printParenthesisExpression(s, i, s[i][j]);

			printParenthesisExpression(s, s[i][j] + 1, j);

			System.out.print(")");
		}

	}
	
	/**
	 * Recursive implementation - optimized with memoization
	 * @param p
	 * @param m
	 * @param s
	 * @param i
	 * @param j
	 * @return
	 */
	private int recursiveParenthesization(int[][] p, int[][] m, int[][] s, int i, int j) {
		
		if (m[i][j] < Integer.MAX_VALUE)
			return m[i][j];
		
		if (i == j)
			m[i][j] = 0;

		else {
			for (int k = i; k < j; k++) {

				int q = recursiveParenthesization(p, m, s, i, k) + recursiveParenthesization(p, m, s, k + 1, j)
						+ p[i][0] * p[k][1] * p[j][1];

				if (q < m[i][j]) {

					m[i][j] = q;

					s[i][j] = k;
				}
			}
		}
		
		return m[i][j];
	}
}
