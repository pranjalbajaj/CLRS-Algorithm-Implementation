package graphs.allpairshortestpath;

import java.util.Arrays;

public class AllPairShortestPathByMatrixMultiplication {
	
	static final double infinity = 1000;
	
	/*
	 * O(V^4)
	 * 
	 * V = no. of vertices
	 */
	public double[][] extendShortestPath(double[][] L, double[][] W) {
		
		int n = L.length;
		
		double[][] temp = new double[n][n];
		
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				
				temp[i][j] = infinity;
				
				for (int k = 0; k < n; k++) {
					
					temp[i][j] = Math.min(temp[i][j], L[i][k] + W[k][j]);
				}
			}
		}
		
		return temp;
	}
	
	public double[][] getAllPairShortestPath(double[][] W) {
		
		int n = W.length;
		
		double[][] L = Arrays.copyOf(W, W.length);
		
		for (int i = 1; i < n; i++) {
			
			L = extendShortestPath(L, W);
		}
		
		return L;
	}
	
	/*
	 * O(V^3 log(V))
	 * 
	 * V = no. of vertices
	 */
	public double[][] getAllPairShortestPathByRepeatedSquaring(double[][] W) {
		
		int n = W.length;
		
		double[][] L = Arrays.copyOf(W, W.length);
		
		for (int i = 1; i < n; i*=2) {
			
			L = extendShortestPath(L, L);
		}
		
		return L;
	}

}
