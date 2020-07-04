package miscellaneous;

import java.util.Arrays;

public class BinaryAddition {

	public static void main(String[] args) {
		
		int A[] = {1,1,1,1};
		
		int B[] = {1,1,1,1};
		
		int[] C = addBinaryNumbers(A,B);
		
		System.out.println(Arrays.toString(C));

	}
	
	static int[] addBinaryNumbers(int[] A, int[] B)
	{	
		int[] C = null;
		
		if (A.length == B.length) {
			
			int len = A.length;
			
			C = new int[A.length + 1];
			
			int carry = 0;
			
			for (int i = len - 1; i >= 0; i--) {
				
				C[i+1] = (A[i] + B[i] + carry) % 2;
				
				if ((A[i] + B[i] + carry) >= 2) {
					
					carry = 1;
				}
				else {
					carry = 0;
				}
				
			}
			
			C[0] = carry;
			
		}
		else {
			
			System.out.println("Addition not possible");
		}
		
		return C;
	}

}
