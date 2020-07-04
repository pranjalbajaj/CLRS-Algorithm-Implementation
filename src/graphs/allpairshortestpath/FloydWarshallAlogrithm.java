package graphs.allpairshortestpath;

import java.util.Arrays;

public class FloydWarshallAlogrithm {
	
	static final double infinity = 1000.0;
	
	int[][] pred = null;
	
	/**
	 * O(V^3)
	 * 
	 * V = no. of vertices.
	 * @param W
	 */

	public void flyodWarshall(double[][] W) {
		
		int n = W.length;
		
		double[][] result = null;
		
		double[][] L = Arrays.copyOf(W, W.length);
		
		int[][] predecessor = getPredecessorArray(W);
		
		for (int k = 0; k < n; k++) {
			
			result = new double[n][n];
			
			for (int i = 0; i < n; i++) {
				
				for (int j = 0; j < n; j++) {
					
					if (L[i][k] != infinity && L[k][j] != infinity && L[i][j] > L[i][k] + L[k][j]) {
						
						result[i][j] = L[i][k] + L[k][j];
						
						predecessor[i][j] = predecessor[k][j];
					}
					else {
						
						result[i][j] = L[i][j];
					}
				}
			}
			
			L = Arrays.copyOf(result, result.length);
		}
		
		System.out.println("Distance Array");
		
		for(int i = 0; i < n; i++) {
			
			System.out.println(Arrays.toString(result[i]));
		}

		System.out.println("Predecessor Array");
		
		for(int i = 0; i < n; i++) {
			
			System.out.println(Arrays.toString(predecessor[i]));
		}
		
		pred = predecessor;
		
	}

	private int[][] getPredecessorArray(double[][] w) {
		
		int n = w.length;
		
		int[][] predecessor = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				
				if (w[i][j] == 1000.0 || w[i][j] == 0.0) {
					
					predecessor[i][j] = -1;
				}
				else {
					
					predecessor[i][j] = i;
				}
			}
		}
		
		return predecessor;
	}
	
	public void printPath(int source, int destination) {
		
		if (source == destination) {
			
			System.out.print(destination + " --> ");
			
			return;
		}
		
		printPath(source, pred[source][destination]);
		
		System.out.print(destination + " --> ");
	}
	
	/**
	 * To determine transitive closure, assign weight of each edge to 1
	 * and run Flyod Warshall algorithm.
	 * 
	 * d[][] = shortest distance array
	 * n = number of vertices
	 * 
	 * d[i][j] < n ---> There is a path from i to j
	 * d[i][j] = infinity ---> No path from i to j
	 * @param W
	 */
	public void determineTransitiveClosure(double[][] W) {
		
		flyodWarshall(W);
	}
}
