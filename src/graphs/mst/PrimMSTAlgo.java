package graphs.mst;

import java.util.ArrayList;
import java.util.HashMap;

import advancedDS.ObjOrientedFibHeap.GenericFibNode;
import advancedDS.ObjOrientedFibHeap.GenericFibonacciHeap;
import graphs.objectorientedgraph.Edge;
import graphs.objectorientedgraph.Graph;
import graphs.objectorientedgraph.Vertex;

public class PrimMSTAlgo {
	
	public void getMST(Graph<Integer> graph) {
		
		/**
		 * STEP 1
		 * Create min heap and put all the vertices into the heap
		 * with their key value as max integer value. 
		 */
		GenericFibonacciHeap<Vertex<Integer>> fh = new GenericFibonacciHeap<Vertex<Integer>>(); 
		
		for (Vertex<Integer> v : graph.vertexList) {
			
			fh.insert(v, Integer.MAX_VALUE);
		}
		
		// vertex + edge map
		HashMap<Vertex<Integer>, Edge<Integer>> vertexToEdgeMap = new HashMap<Vertex<Integer>, Edge<Integer>>();
		
		//list to store result
		ArrayList<Edge<Integer>> mst = new ArrayList<Edge<Integer>>();
		
		/**
		 * STEP 2
		 * Pick any vertex to start with, here
		 * I chose 0.
		 */
		Vertex<Integer> startVertex = graph.vertexList.get(0);
		
		fh.decreaseKey(startVertex, 0);
		
		
		
		/**
		 * STEP 3
		 * Iterate all vertices in min heap.
		 * Extract the min element and explore all its edges.
		 */
		
		while (!fh.isEmpty()) {
			
			Vertex<Integer> u = (Vertex<Integer>)fh.extractMin().getObj();
			
			if (vertexToEdgeMap.containsKey(u)) {
				
				mst.add(vertexToEdgeMap.get(u));
			}
			
			for (Edge<Integer> edge : u.edgeList) {
				
				Vertex<Integer> v = edge.getAdjacentVertex(u);
				
				GenericFibNode<Vertex<Integer>> node = fh.getNode(v);
				
				if (node != null && node.getKey() > edge.weight) {
					
					fh.decreaseKey(node, edge.weight);
					
					vertexToEdgeMap.put(v, edge);
				}
			}
			
		}
		
		System.out.println(mst);
	}

}
