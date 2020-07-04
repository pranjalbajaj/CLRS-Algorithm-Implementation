package graphs.allpairshortestpath;

import java.util.Arrays;

import graphs.Graph;

public class Test {

	public static void main(String[] args) {
		
		//testAllPairShortestPathByMatrixMultiplication();
		
		//floydWarshallTest();
		
		//testTransitiveClosure();
		
		testJohnsonAlgorithm();
	}
	
	public static void testAllPairShortestPathByMatrixMultiplication() {
		
		/**
		 * Directed weighted graph represented by adjacency matrix.
		 * 
		 * i = j -> weight = 0 i != j and there is path from i to j -> weight = weight
		 * of edge i != j and there is not path from i to j -> weight = infinity
		 * (represented as 1000)
		 */
		double[][] W = { { 0, 3, 8, 1000, -4 },
						 { 1000, 0, 1000, 1, 7 },
						 { 1000, 4, 0, 1000, 1000 },
						 { 2, 1000, -5, 0, 1000 },
						 {1000, 1000, 1000, 6, 0 }
						};
		
		AllPairShortestPathByMatrixMultiplication allPair = new AllPairShortestPathByMatrixMultiplication();
		
		double[][] result = null;
		
		//result = allPair.getAllPairShortestPath(W);
		
		result = allPair.getAllPairShortestPathByRepeatedSquaring(W);
		
		for (int i = 0; i < result.length; i++) {
			
			System.out.println(Arrays.toString(result[i]));
			
		}
	}
	
	public static void floydWarshallTest() {
		
		/**
		 * Directed weighted graph represented by adjacency matrix.
		 * 
		 * i = j -> weight = 0 i != j and there is path from i to j -> weight = weight
		 * of edge i != j and there is not path from i to j -> weight = infinity
		 * (represented as 1000)
		 */
		double[][] W = { { 0, 3, 8, 1000, -4 },
						 { 1000, 0, 1000, 1, 7 },
						 { 1000, 4, 0, 1000, 1000 },
						 { 2, 1000, -5, 0, 1000 },
						 {1000, 1000, 1000, 6, 0 }
						};
		
		FloydWarshallAlogrithm floydWarshallAlogrithm = new FloydWarshallAlogrithm();
		
		floydWarshallAlogrithm.flyodWarshall(W);
		
		floydWarshallAlogrithm.printPath(3, 1);
		
		double[][] W1 = { { 0, 1, 1, 1000, 1 },
				 { 1000, 0, 1000, 1, 1 },
				 { 1000, 1, 0, 1000, 1000 },
				 { 1, 1000, 1, 0, 1000 },
				 {1000, 1000, 1000, 1, 0 }
				};
		
		floydWarshallAlogrithm.determineTransitiveClosure(W1);
		
	}
	
	public static void testTransitiveClosure() {
		
		Graph graph = new Graph(4);
		
		for (int i = 0; i < 4; i++)
			graph.addVertex(i);
		
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 2);
		graph.addEdge(2, 1);
		graph.addEdge(3, 0);
		
		TransitiveClosure transitiveClosure = new TransitiveClosure();
		
		int[][] T = transitiveClosure.determineTrasitiveClosure(graph);
		
		for (int i = 0; i < 4; i++) {
			
			System.out.println(Arrays.toString(T[i]));
		}
	}
	
	public static void testJohnsonAlgorithm() {
		
		graphs.objectorientedgraph.Graph<Integer> graph = new graphs.objectorientedgraph.Graph<Integer>();
		
		for (int i = 0; i < 5; i++) {
			
			graph.addVertex(i, i);
		}
		
		graph.addDirectedEdge(0, 1, 3);
		graph.addDirectedEdge(0, 4, -4);
		graph.addDirectedEdge(0, 2, 8);
		graph.addDirectedEdge(1, 3, 1);
		graph.addDirectedEdge(1, 4, 7);
		graph.addDirectedEdge(2, 1, 4);
		graph.addDirectedEdge(3, 2, -5);
		graph.addDirectedEdge(3, 0, 2);
		graph.addDirectedEdge(4, 3, 6);
		
		JohnsonAlgorithm<Integer> johnsonAlgorithm = new JohnsonAlgorithm<Integer>();
		
		double[][] result = johnsonAlgorithm.getAllPairShortestPathArray(graph);
		
		for (int i = 0; i < result.length; i++) {
			
			System.out.println(Arrays.toString(result[i]));
		}
	}
}
