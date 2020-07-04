package graphs.singlesourceshortestpath;

/**
 * DIJKSTRA'S ALGORITHM FOR SINGLE SOURCE SHORTEST PATH:
 * 
 * Terminology: -> General Graph terminology.
 * 				   
 * 
 * Explanation: -> It's an algorithm to find the shortest distance 
 * 				   between a source and destination. It assumes that
 * 				   we have weighted directed graph, with all weights 
 * 				   being non negative. 
 * 
 * Complexity: -> O(V log(V))
 * 				  V = no. of vertices.
 * 				  considering that at max V vertices are adjacent to any vertex
 * 
 * 
 * @author pranjal
 *
 */

import advancedDS.ObjOrientedFibHeap.GenericFibonacciHeap;
import graphs.objectorientedgraph.Edge;
import graphs.objectorientedgraph.Graph;
import graphs.objectorientedgraph.Vertex;

public class DijKstraAlgorithm<T> extends ShortestPathAlgo<T> {
	
	public DijKstraAlgorithm(Graph<T> graph, Vertex<T> source) {
		
		this.graph = graph;
		
		this.source = source;
	}

	public void getSingleSourceShortestPath() {
		
		/**
		 * STEP 1: Call the initialize procedure, then, insert all vertices
		 * into the heap with key as initial shortest path distance.
		 */
		
		initialize(graph);
		
		GenericFibonacciHeap<Vertex<T>> fibHeap = new GenericFibonacciHeap<Vertex<T>>();
		
		for (Vertex<T> v : graph.vertexList) {
			
			fibHeap.insert(v, initialWt);
		}
		
		// decrease the source shortest path to 0
		fibHeap.decreaseKey(source, 0);
		
		/**
		 * STEP 2: Iterate the entire heap
		 */
		
		while (!fibHeap.isEmpty()) {
			
			//extract the minimum element
			Vertex<T> u = fibHeap.extractMin().getObj();
			
			/**
			 * iterate all adjacent vertices of vertex 'u' and relax the edges 
			 */
			
			for (Edge<T> edge : u.edgeList) {
				
				Vertex<T> v = edge.getAdjacentVertex(u);
				
				if (v.d > u.d + edge.weight) {
					
					fibHeap.decreaseKey(v, u.d + edge.weight);
				}
				
				relax(u, v, edge.weight);
			}
		}
		
	}
}
