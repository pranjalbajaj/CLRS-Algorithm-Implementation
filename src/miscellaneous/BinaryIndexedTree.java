/**
 * Binary Indexed Tree or Fenwick Tree: Best used to calculate the prefix sums of a given sequence of numbers.
 * 
 * Space: O(n)
 * Time: Creation -> O(n(log n))
 * 		 Update -> O(log n)
 * 		 Get Prefix Sum -> O(log n)
 * 
 */
package miscellaneous;

public class BinaryIndexedTree {

	public static void main(String[] args) {
		
		int[] seq = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
		
		BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree();
		
		int[] bit = binaryIndexedTree.createBIT(seq);
		
		int result = binaryIndexedTree.getSum(bit, 6);
		
		System.out.println(result);

	}
	
	public int[] createBIT(int[] sequence) {
		
		int[] bit = new int[sequence.length + 1];
		
		for (int i = 1; i < bit.length; i++) {
			
			updateBIT(bit, sequence[i - 1], i);
		}
		
		return bit;
	}
	
	public void updateBIT(int[] bit, int num, int i) {
		
		while (i < bit.length) {
			
			bit[i]+=num;
			
			i = getNext(i);
		}
	}
	
	public int getSum(int[] bit, int i) {
		
		i = i + 1;
		
		int sum = 0;
		
		while (i > 0) {
			
			sum+=bit[i];
			
			i = getParent(i);
		}
		
		return sum;
	}
	
	/**
	 * 1. 2's complement of number (-ve of the number)
	 * 2. Perform AND of 2's complement with original number
	 * 3. Substract the result from step 2 to original number
	 * @return
	 */
	public int getParent(int i) {
		
		return (i - (i & -i));
	}
	
	/**
	 * 1. 2's complement of number (-ve of the number)
	 * 2. Perform AND of 2's complement with original number
	 * 3. Add the result from step 2 to original number
	 * @return
	 */
	public int getNext(int i) {
		
		return (i + (i & -i));
	}

}
