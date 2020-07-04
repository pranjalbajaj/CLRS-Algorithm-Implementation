package graphs.singlesourceshortestpath;

/**
 * BELLMAN FORD ALGORITHM FOR SINGLE SOURCE SHORTEST PATH:
 * 
 * Terminology: -> General Graph terminology.
 * 				   
 * 
 * Explanation: -> It's an algorithm to find the shortest distance 
 * 				   between a source and destination.
 * 
 * Complexity: -> O(VE)
 * 				  V = no. of vertices.
 * 				  E = no. of edges.
 * 
 * 
 * @author pranjal
 *
 */

import graphs.objectorientedgraph.Edge;
import graphs.objectorientedgraph.Graph;
import graphs.objectorientedgraph.Vertex;

public class BellmanFordAlgo<T> extends ShortestPathAlgo<T> {
	
	public boolean isNegativeCyclePresent = false;

	public BellmanFordAlgo(Graph<T> graph, Vertex<T> source) {

		this.graph = graph;
		
		this.source = source;
	}

	public void getSingleSourceShortestPath() {

		initialize(graph);

		/**
		 * For V - 1 passes (V is no. of vertex)
		 * 
		 * 
		 * Iterate over all edges and call the relax procedure,this will finally place
		 * the shortest distance on vertex from the source.
		 */

		for (int i = 1; i <= graph.vertexList.size(); i++) {

			for (Edge<T> edge : graph.edgeList) {

				// start vertex
				Vertex<T> u = edge.v1;

				// end vertex
				Vertex<T> v = edge.v2;

				int weight = edge.weight;

				relax(u, v, weight);
			}
		}
		/**
		 * determine if there is a negative cycle
		 */
		for (Edge<T> edge : graph.edgeList) {

			// start vertex
			Vertex<T> u = edge.v1;

			// end vertex
			Vertex<T> v = edge.v2;

			int weight = edge.weight;

			if (v.d > u.d + weight) {

				isNegativeCyclePresent = true;

				break;
			}
		}

		//System.out.println(isNegativeCyclePresent);
	}

}
