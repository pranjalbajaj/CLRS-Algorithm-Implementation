package dynamicprogramming;

public class RodCuttingProblem {

	public static void main(String[] args) {

		int[] lengthWisePriceArray = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

		int[] revenueArray = new int[lengthWisePriceArray.length];

		int[] sizeArray = new int[lengthWisePriceArray.length];

		for (int i = 0; i < revenueArray.length; i++) {

			revenueArray[i] = -1;

			sizeArray[i] = -1;
		}

		int maxRevenue = 0;
		
		RodCuttingProblem rodCut = new RodCuttingProblem();
		/* 
		 * maxRevenue = rodCut.cutRod2(lengthWisePriceArray, 5);
		 * 
		 * System.out.println(maxRevenue);
		 */

		maxRevenue = 0;

		maxRevenue = rodCut.memoizedCutRodTopDownApproach(lengthWisePriceArray, revenueArray, sizeArray, 4);

		System.out.println("Max Revenue: " + maxRevenue);
		
		int n = 4;

		while (n > 0) {

			System.out.println(sizeArray[n]);

			n = n - sizeArray[n];
		}

		maxRevenue = 0;

		maxRevenue = rodCut.memoizedCutRodBottomUpApproach(lengthWisePriceArray, revenueArray, sizeArray, 10);

		System.out.println("Max Revenue: " + maxRevenue);

		n = 10;

		while (n > 0) {

			System.out.println(sizeArray[n]);

			n = n - sizeArray[n];
		}
	}

	/**
	 * O(2^(n-1))
	 * 
	 * @param lengthWisePriceArray
	 * @param lengthOfRod
	 * @return
	 */
	public int cutRod1(int[] lengthWisePriceArray, int lengthOfRod) {

		if (lengthOfRod == 0)
			return 0;

		int maxRevenue = 0;

		for (int cut = 0; cut < lengthOfRod; cut++) {

			maxRevenue = Math.max(maxRevenue,
					lengthWisePriceArray[lengthOfRod - cut] + cutRod1(lengthWisePriceArray, cut));

		}

		return maxRevenue;

	}

	/**
	 * O(2^(n-1))
	 * 
	 * @param lengthWisePriceArray
	 * @param lengthOfRod
	 * @return
	 */
	public int cutRod2(int[] lengthWisePriceArray, int lengthOfRod) {

		if (lengthOfRod == 0)
			return 0;

		int maxRevenue = 0;

		for (int i = 1; i <= lengthOfRod; i++) {

			maxRevenue = Math.max(maxRevenue, lengthWisePriceArray[i] + cutRod2(lengthWisePriceArray, lengthOfRod - i));

		}

		return maxRevenue;

	}

	/**
	 * Top-Down approach O(n^2)
	 * 
	 * @param lengthWisePriceArray
	 * @param revenueArray
	 * @param sizeArray
	 * @param lengthOfRod
	 * @return
	 */
	public int memoizedCutRodTopDownApproach(int[] lengthWisePriceArray, int[] revenueArray, int[] sizeArray,
			int lengthOfRod) {

		if (revenueArray[lengthOfRod] >= 0)
			return revenueArray[lengthOfRod];

		if (lengthOfRod == 0)
			return 0;

		int maxRevenue = 0;

		for (int i = 1; i <= lengthOfRod; i++) {

			int temp = lengthWisePriceArray[i]
					+ memoizedCutRodTopDownApproach(lengthWisePriceArray, revenueArray, sizeArray, lengthOfRod - i);
			
			if (temp > maxRevenue) {
				
				maxRevenue = temp;
				
				sizeArray[lengthOfRod] = i;
			}

		}

		revenueArray[lengthOfRod] = maxRevenue;

		return maxRevenue;

	}

	/**
	 * Bottom-Up approach O(n^2)
	 * 
	 * @param lengthWisePriceArray
	 * @param revenueArray
	 * @param sizeArray
	 * @param lengthOfRod
	 * @return
	 */
	public int memoizedCutRodBottomUpApproach(int[] lengthWisePriceArray, int[] revenueArray, int[] sizeArray,
			int lengthOfRod) {

		revenueArray[0] = 0;

		for (int i = 1; i <= lengthOfRod; i++) {

			int maxRevenue = 0;

			for (int j = 1; j <= i; j++) {

				// maxRevenue = Math.max(maxRevenue, lengthWisePriceArray[j] + revenueArray[i -
				// j]);

				if (maxRevenue < lengthWisePriceArray[j] + revenueArray[i - j]) {

					maxRevenue = lengthWisePriceArray[j] + revenueArray[i - j];

					sizeArray[i] = j;
				}
			}

			revenueArray[i] = maxRevenue;
		}

		return revenueArray[lengthOfRod];
	}
}
