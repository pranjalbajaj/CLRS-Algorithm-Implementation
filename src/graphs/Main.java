package graphs;

import graphs.bfs.BreadthFirstSearch;
import graphs.dfs.DepthFirstSearch;
import graphs.dfs.EdgeAndCycleDetection;
import graphs.dfs.StronglyConnectedComponents;
import graphs.dfs.TopologicalSort;
import graphs.mst.KruskalMSTAlgo;
import graphs.mst.PrimMSTAlgo;
import graphs.singlesourceshortestpath.BellmanFordAlgo;
import graphs.singlesourceshortestpath.DijKstraAlgorithm;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * Graph graph = new Graph(8);
		 * 
		 * for (int i = 0; i < 8; i++) {
		 * 
		 * graph.addVertex(i); }
		 * 
		 * graph.addEdge(0, 1); graph.addEdge(1, 0); graph.addEdge(1, 6);
		 * graph.addEdge(6, 1); graph.addEdge(0, 7); graph.addEdge(7, 0);
		 * graph.addEdge(7, 4); graph.addEdge(4, 7); graph.addEdge(2, 7);
		 * graph.addEdge(7, 2); graph.addEdge(4, 2); graph.addEdge(2, 4);
		 * graph.addEdge(4, 3); graph.addEdge(3, 4); graph.addEdge(2, 5);
		 * graph.addEdge(5, 2); graph.addEdge(3, 5); graph.addEdge(5, 3);
		 * graph.addEdge(2, 3); graph.addEdge(3, 2);
		 * 
		 * //bfsTest(graph); //------------------------------------------- graph = new
		 * Graph(8);
		 * 
		 * for (int i = 0; i < 8; i++) {
		 * 
		 * graph.addVertex(i); }
		 * 
		 * graph.addEdge(0, 1); graph.addEdge(1, 2); graph.addEdge(1, 4);
		 * graph.addEdge(2, 3); graph.addEdge(3, 1); graph.addEdge(4, 3);
		 * graph.addEdge(0, 4); graph.addEdge(5, 0); graph.addEdge(5, 4);
		 * graph.addEdge(6, 0); graph.addEdge(6, 5); graph.addEdge(6, 7);
		 * graph.addEdge(7, 6); graph.addEdge(7, 5);
		 * 
		 * //dfsTest(graph);
		 * 
		 * graph = new Graph(8);
		 * 
		 * for (int i = 0; i < 8; i++) {
		 * 
		 * graph.addVertex(i); }
		 * 
		 * graph.addEdge(0, 2); graph.addEdge(1, 2); graph.addEdge(1, 3);
		 * graph.addEdge(2, 4); graph.addEdge(4, 5); graph.addEdge(5, 6);
		 * graph.addEdge(4, 7);
		 * 
		 * //topologicalSortTest(graph);
		 * 
		 * //--------------------------------------- graph = new Graph(14);
		 * 
		 * for (int i = 0; i < 14; i++) {
		 * 
		 * graph.addVertex(i); }
		 * 
		 * graph.addEdge(0, 5); graph.addEdge(0, 11); graph.addEdge(0, 4);
		 * graph.addEdge(1, 4); graph.addEdge(1, 8); graph.addEdge(1, 2);
		 * graph.addEdge(2, 5); graph.addEdge(2, 6); graph.addEdge(2, 9);
		 * graph.addEdge(3, 2); graph.addEdge(3, 6); graph.addEdge(3, 13);
		 * graph.addEdge(4, 7); graph.addEdge(5, 8); graph.addEdge(5, 12);
		 * graph.addEdge(6, 5); graph.addEdge(8, 7); graph.addEdge(9, 11);
		 * graph.addEdge(9, 10); graph.addEdge(10, 13); graph.addEdge(12, 9);
		 */
		
		Graph graph = new Graph(4);
		
		for (int i = 0; i < 4; i++) {
			
			graph.addVertex(i);
		}
		
		graph.addEdge(1, 0);
		graph.addEdge(1, 3);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		
		topologicalSortTest(graph);
		
		//dfsTest(graph);
		
		//topologicalSortTest(graph);
		
		//edgeAndCycleDetectionTest(graph);
		
		//stronglyConnectedComponentsTest(graph);
		
		//KruskalTest();
		
		//primTest();
		
		//bellmanfordTest();
		
		//dijkstraTest();
	}

	private static void bellmanfordTest() {
		
		/*
		 * graphs.objectorientedgraph.Graph<Integer> graph = new
		 * graphs.objectorientedgraph.Graph<Integer>();
		 * 
		 * for (int i = 0; i < 5; i++) graph.addVertex(i, i);
		 * 
		 * graph.addDirectedEdge(0, 1, 6); graph.addDirectedEdge(0, 4, 7);
		 * graph.addDirectedEdge(1, 4, 8); graph.addDirectedEdge(3, 0, 2);
		 * graph.addDirectedEdge(4, 3, 9); graph.addDirectedEdge(3, 2, 7);
		 * graph.addDirectedEdge(4, 2, -3); graph.addDirectedEdge(1, 3, -4);
		 * graph.addDirectedEdge(1, 2, 5); graph.addDirectedEdge(2, 1, -2);
		 */
		
		graphs.objectorientedgraph.Graph<Integer> graph = new graphs.objectorientedgraph.Graph<Integer>();
		
		for (int i = 0; i < 6; i++)
			graph.addVertex(i, i);
		
		graph.addDirectedEdge(0, 1, 0);
		graph.addDirectedEdge(0, 2, 0);
		graph.addDirectedEdge(0, 3, 0);
		graph.addDirectedEdge(0, 4, 0);
		graph.addDirectedEdge(0, 5, 0);
		graph.addDirectedEdge(5, 1, -1);
		graph.addDirectedEdge(5, 2, 1);
		graph.addDirectedEdge(1, 4, 4);
		graph.addDirectedEdge(1, 3, 5);
		graph.addDirectedEdge(2, 1, 0);
		graph.addDirectedEdge(3, 5, -3);
		graph.addDirectedEdge(3, 4, -1);
		graph.addDirectedEdge(4, 5, -3);
		
		BellmanFordAlgo<Integer> bellnBellmanFordAlgo = new BellmanFordAlgo(graph ,graph.vertexList.get(0));
		
		bellnBellmanFordAlgo.getSingleSourceShortestPath();
		
		bellnBellmanFordAlgo.printPath(graph.vertexList.get(3));
		
	}
	
	private static void dijkstraTest() {
		
		graphs.objectorientedgraph.Graph<Integer> graph = new graphs.objectorientedgraph.Graph<Integer>();
		
		for (int i = 0; i < 5; i++)
			graph.addVertex(i, i);
		
		graph.addDirectedEdge(0, 1, 6);
		graph.addDirectedEdge(0, 4, 7);
		graph.addDirectedEdge(1, 4, 8);
		graph.addDirectedEdge(3, 0, 2);
		graph.addDirectedEdge(4, 3, 9);
		graph.addDirectedEdge(3, 2, 7);
		graph.addDirectedEdge(4, 2, -3);
		graph.addDirectedEdge(1, 3, -4);
		graph.addDirectedEdge(1, 2, 5);
		graph.addDirectedEdge(2, 1, -2);
		
		DijKstraAlgorithm<Integer> dijKstraAlgorithm = new DijKstraAlgorithm(graph, graph.vertexList.get(0));
		
		dijKstraAlgorithm.getSingleSourceShortestPath();
		
		dijKstraAlgorithm.printPath(graph.vertexList.get(3));
		
	}

	private static void stronglyConnectedComponentsTest(Graph graph) {
		
		graph = new Graph(7);
		
		for (int i = 0; i < 7; i++) {
			
			graph.addVertex(i);
		}
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 3);
		graph.addEdge(6, 5);
		
		StronglyConnectedComponents scc = new StronglyConnectedComponents();
		
		scc.printStronglyConnectedComponents(graph);
		
	}

	private static void edgeAndCycleDetectionTest(Graph graph) {
		
		EdgeAndCycleDetection ec = new EdgeAndCycleDetection();
		
		ec.printEdgeType(graph, 0);
		
		boolean isCyclePresent = ec.isGraphContainsCycle(graph);
		
		System.out.println(isCyclePresent);
		
	}

	private static void topologicalSortTest(Graph graph) {
		
		TopologicalSort ts = new TopologicalSort();
				
		ts.topologicalSort(graph);
	}

	private static void dfsTest(Graph graph) {
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		
		//dfs.dfsTraversal(graph, 0);
		
		int paths = dfs.getPaths(graph, 2, 11);
		
		System.out.println("\npaths: " + paths);
		
		dfs.printPaths(graph, 2, 11);
	}

	private static void bfsTest(Graph graph) {
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		
		bfs.bfsTraversal(graph, 0);
		
		bfs.shortestPath(graph, 0);
		
		bfs.printPath(4, 2);

	}
	
	private static void KruskalTest() {
		
		graphs.objectorientedgraph.Graph<Character> graph = new graphs.objectorientedgraph.Graph<Character>();
		
		graph.addVertex(0, 'A');
		
		graph.addVertex(1, 'B');
		
		graph.addVertex(2, 'C');
		
		graph.addVertex(3, 'D');
		
		graph.addVertex(4, 'E');
		
		graph.addVertex(5, 'F');
		
		graph.addEdge(0, 1, 3);
		
		graph.addEdge(0, 3, 1);
		
		graph.addEdge(1, 2, 1);
		
		graph.addEdge(1, 3, 3);
		
		graph.addEdge(2, 3, 1);
		
		graph.addEdge(2, 4, 5);
		
		graph.addEdge(2, 5, 4);
		
		graph.addEdge(3, 4, 6);
		
		graph.addEdge(4, 5, 2);
		
		KruskalMSTAlgo krukal = new KruskalMSTAlgo();
		
		krukal.getMST(graph);
		
	}
	
	public static void primTest() {
		
		graphs.objectorientedgraph.Graph<Integer> graph = new graphs.objectorientedgraph.Graph<Integer>();
		
		for (int i = 0; i < 9; i++)
			graph.addVertex(i, i);
		
		graph.addEdge(0, 1, 4);
		
		graph.addEdge(0, 7, 8);
		
		graph.addEdge(1, 2, 8);
		
		graph.addEdge(1, 7, 11);
		
		graph.addEdge(2, 3, 7);
		
		graph.addEdge(2, 8, 2);
		
		graph.addEdge(2, 5, 4);
		
		graph.addEdge(3, 4, 9);
		
		graph.addEdge(3, 5, 14);
		
		graph.addEdge(4, 5, 10);
		
		graph.addEdge(5, 6, 2);
		
		graph.addEdge(6, 7, 1);
		
		graph.addEdge(6, 8, 6);
		
		graph.addEdge(7, 8, 7);
		
		
		PrimMSTAlgo primMSTAlgo = new PrimMSTAlgo();
		
		primMSTAlgo.getMST(graph);
	}
}
