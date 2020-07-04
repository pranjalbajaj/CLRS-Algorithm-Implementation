package graphs.maximumflow;

import graphs.maximumflow.pushrelableGraph.Graph;
import graphs.maximumflow.pushrelableGraph.Vertex;

public class Test {

	public static void main(String[] args) {
		
		//testFordFulkerson();
		
		testGlodbergPushRelable();
	}

	private static void testGlodbergPushRelable() {
		
		Graph<Integer> graph = new Graph<Integer>();
		
		for (int i = 0; i < 6; i++) {
			
			graph.addVertex(i, i);
		}
		
		
		  graph.addDirectedEdge(0, 1, 16); 
		  graph.addDirectedEdge(0, 2, 13);
		  graph.addDirectedEdge(1, 2, 10); 
		  graph.addDirectedEdge(2, 1, 4);
		  graph.addDirectedEdge(1, 3, 12); 
		  graph.addDirectedEdge(2, 4, 14);
		  graph.addDirectedEdge(3, 2, 19); 
		  graph.addDirectedEdge(3, 5, 20);
		  graph.addDirectedEdge(4, 3, 7); 
		  graph.addDirectedEdge(4, 5, 4);
		 
		
		/*
		 * graph.addDirectedEdge(0, 1, 10); graph.addDirectedEdge(0, 3, 12);
		 * graph.addDirectedEdge(1, 2, 15); graph.addDirectedEdge(3, 1, 5);
		 * graph.addDirectedEdge(3, 4, 6); graph.addDirectedEdge(2, 4, 8);
		 * graph.addDirectedEdge(2, 5, 3); graph.addDirectedEdge(4, 5, 17);
		 */
		
		Vertex<Integer> source = graph.vertexList.get(0);
		
		Vertex<Integer> sink = graph.vertexList.get(5);
		
		GoldbergPushRelabelAlgorithm<Integer> goldberg = new GoldbergPushRelabelAlgorithm<Integer>(graph, source, sink);
		
		System.out.println(goldberg.getMaxFlow());
		
		goldberg.printFlowOnEachEdge();
	}

	private static void testFordFulkerson() {
		
		/*double[][] capacity = {{0, 3, 0, 3, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 0},
                {3, 0, 0, 1, 2, 0, 0},
                {0, 0, 0, 0, 2, 6, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0}};*/
		
		/**
		 * Maximum bipartite matching
		 */
		double[][] capacity = {
								{0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							};
		FordFulkersonMethodEdmondsKarpAlgorithm ffek = new FordFulkersonMethodEdmondsKarpAlgorithm();
		
		double maxFlow = ffek.getMaxFlow(capacity, 0, 10);
		
		ffek.printaugmentedPaths();
		
		System.out.println("Max FLow: " + maxFlow);

		
	}

}
