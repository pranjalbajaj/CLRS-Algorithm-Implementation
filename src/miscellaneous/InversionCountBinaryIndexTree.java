package miscellaneous;

public class InversionCountBinaryIndexTree {

	public static void main(String[] args) {
		
		InversionCountBinaryIndexTree inv = new InversionCountBinaryIndexTree();
		
		int[] seq = {4, 3, 2 ,1};
		
		int count = inv.getInvCount(seq);
		
		System.out.println(count);

	}
	
	public void updateBIT(int[] bit, int idx) {
		
		while (idx < bit.length) {
			
			bit[idx]+=1;
			
			idx = getNext(idx);
		}
	}
	
	public int getSum(int[] bit, int i) {
		
		int sum = 0;
		
		while (i > 0) {
			
			sum+=bit[i];
			
			i = getParent(i);
		}
		
		return sum;
	}
	
	public int getInvCount(int[] seq) {
		
		int max = getMaxElementInArray(seq);
		
		int[] bit = new int[max + 1];
		
		int invCount = 0;
		
		for (int i = seq.length - 1; i >=0; i--) {
			
			invCount += getSum(bit, seq[i] - 1);
			
			updateBIT(bit, seq[i]);
		}
		
		return invCount;
	}
	
	/**
	 * 1. 2's complement of number (-ve of the number)
	 * 2. Perform AND of 2's complement with original number
	 * 3. Subtract the result from step 2 to original number
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

	private int getMaxElementInArray(int[] arr) {
		
		int max = -1;
		
		for (int i = 0; i < arr.length; i++) {
			
			if (arr[i] > max) {
				
				max = arr[i];
			}
		}
		
		return max;
	}

}
