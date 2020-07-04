package dynamicprogramming;

public class CoinChangeProblem2 {

	public static void main(String[] args) {
		
		int[] coins = { 7, 2, 3, 6 };
		
		int amount = 13;
		
		int numOfCoins = minimumNumberCoins(coins, amount);
		
		System.out.println("No. of coins: " + numOfCoins);
		
		int numOfWays = getTotalNumberOfWaysPossible(coins, amount);
		
		System.out.println("Total No. of ways: " + numOfWays);

	}
	
	private static int minimumNumberCoins(int[] coins, int amount) {
		
		int[] T = new int[amount + 1];
		
		int[] M = new int[amount + 1];
		
		T[0] = 0;
		
		M[0] = -1;
		
		for (int i = 1; i <= amount; i++) {
			
			T[i] = Integer.MAX_VALUE - 1;
			
			M[i] = -1;
		}
		
		
		for (int i = 0; i < coins.length; i++) {
			
			for (int j = 0; j <= amount; j++) {
				
				if (j >= coins[i]) {
					
					if (T[j] > 1 + T[j - coins[i]]) {

						T[j] = 1 + T[j - coins[i]];
						
						M[j] = i;

					}
				}
			}
		}
		
		printCoinsUsed(coins, M);
		
		return T[amount];
	}

	private static void printCoinsUsed(int[] coins, int[] m) {
		
		if (m[m.length - 1] == -1) {
			System.out.println("No solution");
			return;
		}
		
		int x = m.length - 1;
		
		while (x != 0) {
			
			int coinIdx = m[x];
			
			System.out.print(coins[coinIdx] + "\t");
			
			x = x - coins[coinIdx];
		}
		
		System.out.println();
	}
	
	private static int getTotalNumberOfWaysPossible(int[] coins, int amount) {
		
		int[][] T = new int[coins.length + 1][amount + 1];
		
		for (int i = 1; i <= coins.length; i++) {
			
			for (int j = 0; j <= amount; j++) {
				/**
				 * To form amount = 0, there is only one way
				 * i.e no coin should be picked.
				 */
				if (j == 0)
					T[i][j] = 1;
				
				else if (coins[i - 1] > j)
					T[i][j] = T[i - 1][j];
				
				else
					T[i][j] = T[i - 1][j] + T[i][j - coins[i - 1]];
			}
		}
		
		return T[coins.length][amount];
	}

}
