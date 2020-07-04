package dynamicprogramming;

public class FibonacciSeries {

	public static void main(String[] args) {
		
		FibonacciSeries fibonacciSeries = new FibonacciSeries();
		
		int n = fibonacciSeries.fib3(6);
		
		System.out.println(n);

	}
	
	/**
	 * Using recursion
	 * @param n
	 * @return
	 */
	public int fib1(int n) {
		
		if (n <= 1)
			return n;
		
		return fib1(n - 1) + fib1(n -2);
	}
	
	/**
	 * Iterative (space optimized)
	 * @param n
	 * @return
	 */
	public int fib2(int n) {
		
		int num1 = 1;
		
		int num2 = 1;
		
		int num3;
		
		for (int i = 2; i < n; i++) {
			
			num3 = num1 + num2;
			
			num1 = num2;
			
			num2 = num3;
		}
		
		return num2;
	}
	
	/**
	 * Dynamic Programming
	 */
	
	public int fib3(int n) {
		
		int[] series = new int[n + 1];
		
		series[0] = 0;
		
		series[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			
			series[i] = series[i - 1] + series[i - 2];
		}
		
		return series[n];
	}

}
