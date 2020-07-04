package graphs.allpairshortestpath;

import graphs.Graph;

public class TransitiveClosure {

	/**
	 * O(V^3)
	 * 
	 * V = no. of vertices.
	 * @param graph
	 * @return
	 */
	public int[][] determineTrasitiveClosure(Graph graph) {
		
		int n = graph.vertices;
		
		int[][] T = new int[n][n];
		
		/**
		 * Initialize T
		 */
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				
				if (i == j || graph.adjList[i].contains(j)) {
					
					T[i][j] = 1;
				}
			}
		}
		
		/**
		 * determine transitive closure
		 */
		
		for (int k = 0; k < n; k++) {
			
			int[][] temp = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				
				for (int j = 0; j < n; j++) {
					
					if (T[i][j] == 1 || T[i][k] * T[k][j] == 1) {
						
						temp[i][j] = 1;
					}
				}
			}
			
			T = temp;
		}
		
		return T;
	}
}
