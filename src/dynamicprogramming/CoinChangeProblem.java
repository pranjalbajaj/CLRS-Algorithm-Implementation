package dynamicprogramming;

public class CoinChangeProblem {

	public static void main(String[] args) {

		int[] coins = { 8, 1, 5, 6};

		int amount = 11;

		int numOfCoins = minimumNumberCoins(coins, amount);
		
		int[][] dp = new int[coins.length + 1][amount + 1];
		
		for (int i = 0; i <= coins.length; i++) {
			
			for (int j = 0; j <= amount; j++) {
				
				if (i == 0)
					dp[i][j] = Integer.MAX_VALUE - 1;
				else if (j == 0)
					dp[i][j] = 0;
				else 
					dp[i][j] = -1;
					
			}
		}
		
		int res = recursiveMinimumNumberCoins(coins, dp, coins.length, amount);

		System.out.println(res);

	}

	private static int minimumNumberCoins(int[] coins, int amount) {

		/*
		 * 2-D array to store results.
		 */
		int[][] dp = new int[coins.length + 1][amount + 1];

		// iterate for each coin value over the amounts (0 to amount)
		for (int i = 0; i <= coins.length; i++) {

			for (int j = 1; j <= amount; j++) {

				/**
				 * we cannot make 0 amount and also cannot use 0 denomination coin to make some
				 * amount
				 */
				if (i == 0)
					dp[i][j] = Integer.MAX_VALUE - 1;

				else if (coins[i - 1] <= j)
					dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);

				else
					dp[i][j] = dp[i - 1][j];

			}
		}

		return dp[coins.length][amount];
	}

	public static int recursiveMinimumNumberCoins(int[] coins, int[][] dp, int i, int j) {

		if (dp[i][j] != -1)
			return dp[i][j];

		if (coins[i - 1] <= j)
			dp[i][j] = Math.min(recursiveMinimumNumberCoins(coins, dp, i - 1, j),
					1 + recursiveMinimumNumberCoins(coins, dp, i, j - coins[i - 1]));
		else
			dp[i][j] = recursiveMinimumNumberCoins(coins, dp, i - 1, j);
		
		return dp[i][j];
	}

}
