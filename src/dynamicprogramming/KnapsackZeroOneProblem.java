package dynamicprogramming;

public class KnapsackZeroOneProblem {

	public static void main(String[] args) {

		int[] weights = { 1, 3, 4, 5};

		int[] values = { 1, 4, 5, 7 };

		int knapsackWt = 7;

		knapsackSolution(weights, values, knapsackWt);
		
		int[][] arr = new int[weights.length + 1][knapsackWt + 1];
		
		for (int i = 0; i < arr.length; i++) {
			
			for (int j = 0; j < arr[0].length; j++) {
				
				if (i == 0 || j == 0)
					arr[i][j] = 0;
				else
					arr[i][j] = -1;
					
			}
		}
		
		int maxValue = recursiveKnapsackSolution(weights, values, arr, 4, 7);
		
		System.out.println(maxValue);
		
		printWeights(arr, weights, values, 4, 7);

	}

	public static void knapsackSolution(int[] w, int[] v, int kw) {

		int[][] dpArr = new int[w.length + 1][kw + 1];

		for (int i = 1; i < dpArr.length; i++) {

			for (int j = 1; j < dpArr[0].length; j++) {

				if (w[i - 1] > j) {

					dpArr[i][j] = dpArr[i - 1][j];

				} else {

					dpArr[i][j] = Math.max(v[i - 1] + dpArr[i - 1][Math.abs(j - w[i - 1])], dpArr[i - 1][j]);
				}

			}

		}

		System.out.println("Max value: " + dpArr[w.length][kw]);
		
		printWeights(dpArr, w, v, w.length, kw);
	}

	private static void printWeights(int[][] dpArr, int[] w, int[] v, int i, int j) {
		
		if (i == 0 || j == 0)
			return ;
			
		if (dpArr[i][j]  == v[i - 1] + dpArr[i - 1][Math.abs(j - w[i - 1])]) {
			
			System.out.println(w[i - 1]);
			
			printWeights(dpArr, w, v, i - 1, j - w[i - 1]);
		}
		else
			printWeights(dpArr, w, v, i - 1, j);
		
	}

	public static int recursiveKnapsackSolution(int[] w, int[] v, int[][] dpArr, int i, int j) {

		if (dpArr[i][j] != -1)
			return dpArr[i][j];

		if (w[i - 1] > j) {

			dpArr[i][j] = recursiveKnapsackSolution(w, v, dpArr, i - 1, j);

		}
		else {
			dpArr[i][j] = Math.max(v[i - 1] + recursiveKnapsackSolution(w, v, dpArr, i - 1, Math.abs(j - w[i - 1])),
					recursiveKnapsackSolution(w, v, dpArr, i - 1, j));
			
		}
		
		return dpArr[i][j];
	}
}
