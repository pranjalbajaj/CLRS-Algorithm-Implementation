package matrixoperations;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		testLinearEquation();
	}

	public static void testLinearEquation() {
		
		SolveLinearEquations solveLinearEquations = new SolveLinearEquations();
		
		double[][] A = { {1, 2, 0},
						 {3, 4, 4},
						 {5, 6, 3}
					};
		
		double[][] B = { {3}, {7}, {8} };
		
		double[][] x = solveLinearEquations.LUPSolve(A, B);
		
		for (int i = 0; i < x.length; i++) {
			
			System.out.println(Arrays.toString(x[i]));
		}
	}
}
