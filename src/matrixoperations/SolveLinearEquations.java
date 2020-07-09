package matrixoperations;

public class SolveLinearEquations {

	double[][] L = null;

	double[][] U = null;
	
	int[] pi = null;

	/**
	 * Numerically unstable,
	 * division by 0 is a possibility
	 * @param A
	 */
	public void LUDecomposition(double[][] A) {

		int n = A.length;

		this.L = new double[n][n];

		this.U = new double[n][n];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				if (i == j) {

					L[i][j] = 1;
				}
			}
		}

		for (int k = 0; k < n; k++) {

			U[k][k] = A[k][k];

			for (int i = k + 1; i < n; i++) {

				L[i][k] = A[i][k] / U[k][k];

				U[k][i] = A[k][i];

			}

			for (int i = k + 1; i < n; i++) {

				for (int j = k + 1; j < n; j++)

					A[i][j] = A[i][j] - L[i][k] * U[k][j];

			}
		}
	}
	
	/**
	 * Numerically stable,
	 * 
	 * avoids division by 0
	 * 
	 * -> in place decomposition of L and U in A
	 */
	public void LUPDecomposition(double[][] A) {
		
		int n = A.length;
		
		pi = new int[n];
		
		for (int i = 0; i < n; i++) {
			
			pi[i] = i;
		}
		
		for (int k = 0; k < n; k++) {
			
			double p = 0;
			
			int temp = k;
			
			for (int i = k; i <n; i++) {
				
				if (Math.abs(A[i][k]) > p) {
					
					p = Math.abs(A[i][k]);
					
					temp = i;
				}
			}
			
			if (p == 0) {
				
				System.out.println("Singular matrix");
				
				return;
			}
			
			int a = pi[temp];
			
			pi[temp] = pi[k];
			
			pi[k] = a;
			
			for (int i = 0; i < n; i++) {
				
				double r = A[k][i];
				
				A[k][i] = A[temp][i];
				
				A[temp][i] = r;
			}
			
			for (int i = k + 1; i < n; i++) {
				
				A[i][k] = A[i][k] / A[k][k];
				
				for (int j = k + 1; j < n; j++) {
					
					A[i][j] -= A[i][k]*A[k][j];
				}
			}
		}
		
		determineLU(A);
	}
	
	private void determineLU(double[][] A) {
		
		int n = A.length;
		
		this.L = new double[n][n];

		this.U = new double[n][n];
		
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				
				if (i <= j) {
					
					U[i][j] = A[i][j];
					
					if (i == j) {
						
						L[i][j] = 1;
					}
				}
				else {
					
					L[i][j] = A[i][j];
				}
			}
 		}
	}
	
	public double[][] LUSolve(double[][] A, double[][] B) {
		
		LUDecomposition(A);
		
		int n = A.length;
		
		double[][] x = new double[n][1];
		
		double[][] y = new double[n][1];
		
		for (int i = 0; i < n; i++) {
			
			double w = 0;
			
			for (int j = 0; j < i; j++) {
				
				w+=(L[i][j]*y[j][0]);
			}
			
			y[i][0] = B[i][0] - w;
		}
		
		for (int i = n - 1; i >= 0; i--) {
			
			double w = 0;
			
			for (int j = i + 1; j < n; j++) {
				
				w+=(U[i][j]*x[j][0]);
			}
			
			x[i][0] = (y[i][0] - w) / U[i][i];
		}
		
		return x;
	}
	
	public double[][] LUPSolve(double[][] A, double[][] B) {
		
		LUPDecomposition(A);
		
		int n = A.length;
		
		double[][] x = new double[n][1];
		
		double[][] y = new double[n][1];
		
		for (int i = 0; i < n; i++) {
			
			double w = 0;
			
			for (int j = 0; j < i; j++) {
				
				w+=(L[i][j]*y[j][0]);
			}
			
			y[i][0] = B[pi[i]][0] - w;
		}
		
		for (int i = n - 1; i >= 0; i--) {
			
			double w = 0;
			
			for (int j = i + 1; j < n; j++) {
				
				w+=(U[i][j]*x[j][0]);
			}
			
			x[i][0] = (y[i][0] - w) / U[i][i];
		}
		
		return x;
	}
}
