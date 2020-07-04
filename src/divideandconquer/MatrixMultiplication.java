package divideandconquer;

/**
 * MERGE SORT:
 * 
 * Terminology: -> row_A_startIdx = Starting index of row of A
 * 				-> row_A_endIdx = Ending index of row of A
 * 				-> col_A_startIdx = Starting index of column of A
 * 				-> col_A_endIdx = Ending index of column of A
 * 				-> row_B_startIdx = Starting index of row of A
 * 				-> row_B_endIdx = Ending index of row of B
 * 				-> col_B_startIdx = Starting index of column of B
 * 				-> col_B_endIdx = Ending index of column of B
 * 				-> rowShift = Count of rows to shift in result array
 * 				-> colShift = Count of columns to shift in result array
 * 
 * 	
 * Explanation: It's a divide an conquer algorithm, works for only square matrices with even index,
 * 				if the matrix is not a even square matrix, we have to make it even square matrix by 
 * 				padding zeros. 
 * 
 * Complexity: O(n^3)
 * 
 * 
 * @author pranjal
 *
 */

public class MatrixMultiplication {

	public static void main(String[] args) {

		int[][] A = { { 1, 2, 3, 4}, { 5, 6, 7, 8}, { 9, 10 ,11, 12}, { 13, 14, 15, 16} };

		int[][] B = { { 16, 15, 14, 13 }, { 12, 11, 10, 9 }, { 8, 7, 6, 5 }, {4, 3, 2, 1} };
		
		int[][] C = multiplyMatrix(A, B, 0, A.length - 1, 0, A[0].length - 1, 0, B.length - 1, 0, B[0].length - 1);
		
		printResult(C);

	}

	public static int[][] multiplyMatrix(int[][] A, int[][] B, int row_A_startIdx, int row_A_endIdx, int col_A_startIdx,
			int col_A_endIdx, int row_B_startIdx, int row_B_endIdx, int col_B_startIdx, int col_B_endIdx) {

		if (row_A_startIdx > row_A_endIdx || col_A_startIdx > col_A_endIdx || row_B_startIdx > row_B_endIdx
				|| col_B_startIdx > col_B_endIdx) {
			
			return null;
		}

		int rows_C = row_A_endIdx - row_A_startIdx + 1;

		int cols_C = col_B_endIdx - col_B_startIdx + 1;

		int[][] C = new int[rows_C][cols_C];

		if (C.length == 1 && C[0].length == 1) {

			C[0][0] = A[row_A_startIdx][col_A_startIdx] * B[row_B_startIdx][col_B_startIdx];
		} else {

			int row_A_midIdx = (row_A_startIdx + row_A_endIdx) / 2;

			int col_A_midIdx = (col_A_startIdx + col_A_endIdx) / 2;

			int row_B_midIdx = (row_B_startIdx + row_B_endIdx) / 2;

			int col_B_midIdx = (col_B_startIdx + col_B_endIdx) / 2;

			int rowShift = rows_C / 2;

			int colShift = cols_C / 2;

			addMatrix(
					multiplyMatrix(A, B, row_A_startIdx, row_A_midIdx, col_A_startIdx, col_A_midIdx, row_B_startIdx,
							row_B_midIdx, col_B_startIdx, col_B_midIdx),
					multiplyMatrix(A, B, row_A_startIdx, row_A_midIdx, col_A_midIdx + 1, col_A_endIdx, row_B_midIdx + 1,
							row_B_endIdx, col_B_startIdx, col_B_midIdx),
					C, 0, 0);

			addMatrix(
					multiplyMatrix(A, B, row_A_startIdx, row_A_midIdx, col_A_startIdx, col_A_midIdx, row_B_startIdx,
							row_B_midIdx, col_B_midIdx + 1, col_B_endIdx),
					multiplyMatrix(A, B, row_A_startIdx, row_A_midIdx, col_A_midIdx + 1, col_A_endIdx, row_B_midIdx + 1,
							row_B_endIdx, col_B_midIdx + 1, col_B_endIdx),
					C, 0, colShift);

			addMatrix(
					multiplyMatrix(A, B, row_A_midIdx + 1, row_A_endIdx, col_A_startIdx, col_A_midIdx, row_B_startIdx,
							row_B_midIdx, col_B_startIdx, col_B_midIdx),
					multiplyMatrix(A, B, row_A_midIdx + 1, row_A_endIdx, col_A_midIdx + 1, col_A_endIdx,
							row_B_midIdx + 1, row_B_endIdx, col_B_startIdx, col_B_midIdx),
					C, rowShift, 0);

			addMatrix(
					multiplyMatrix(A, B, row_A_midIdx + 1, row_A_endIdx, col_A_startIdx, col_A_midIdx, row_B_startIdx,
							row_B_midIdx, col_B_midIdx + 1, col_B_endIdx),
					multiplyMatrix(A, B, row_A_midIdx + 1, row_A_endIdx, col_A_midIdx + 1, col_A_endIdx,
							row_B_midIdx + 1, row_B_endIdx, col_B_midIdx + 1, col_B_endIdx),
					C, rowShift, colShift);

		}

		return C;

	}
	
	private static void addMatrix(int[][] m1, int[][] m2, int[][] result, int rowShift, int colShift) {
		
		if (m1 == null || m2 == null)
			return;
		
		for (int i = 0; i < m1.length; i++) {
			
			for (int j = 0; j < m2.length; j++) {
				
				result[i + rowShift][j + colShift] = m1[i][j] + m2[i][j];
			}
			
		}
	}
	
	private static void printResult(int[][] c) {
		
		for (int i = 0; i < c.length; i++) {
			
			for (int j = 0; j < c[0].length; j++) {
				
				System.out.print(c[i][j] + " ");
			}
			
			System.out.println();
		}
		
	}
}
