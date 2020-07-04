package graphs.allpairshortestpath;

import graphs.objectorientedgraph.Edge;
import graphs.objectorientedgraph.Graph;
import graphs.objectorientedgraph.Vertex;
import graphs.singlesourceshortestpath.BellmanFordAlgo;
import graphs.singlesourceshortestpath.DijKstraAlgorithm;

public class JohnsonAlgorithm<T> {

	/**
	 * O(V^2 log(V) + VE)
	 * @param graph
	 * @return
	 */
	public double[][] getAllPairShortestPathArray(Graph<T> graph) {
		
		// initialize new graph G'
		T data = null;
		
		int id = graph.vertexList.size();
		
		graph.addVertex(id, data);
		
		for (Vertex<T> v : graph.vertexList) {
			
			graph.addDirectedEdge(id, v.id, 0);
		}
		
		// run bellmanford algorithm
		
		BellmanFordAlgo<T> bellmanFordAlgo = new BellmanFordAlgo<T>(graph, graph.vertexList.get(id));
		
		bellmanFordAlgo.getSingleSourceShortestPath();
		
		if (bellmanFordAlgo.isNegativeCyclePresent) {
			
			System.out.println("Negative cycle is present");
			
			return null;
		}
		else {
			
			//reweight
			
			for (Edge<T> edge : graph.edgeList) {
				
				edge.weight = edge.weight + edge.v1.d - edge.v2.d;
				
				edge.v1.h = edge.v1.d;
				
				edge.v2.h = edge.v2.d;
			}
			
			graph.vertexList.remove(id);
			
			DijKstraAlgorithm<T> dijKstraAlgorithm = new DijKstraAlgorithm<T>(graph, null);
			
			int n = graph.vertexList.size();
			
			double[][] result = new double[n][n];
			
			Vertex<T> u = null;
			
			for (int i = 0; i < n; i++) {
				
				u = graph.vertexList.get(i);
				
				dijKstraAlgorithm.setSource(u);
				
				dijKstraAlgorithm.getSingleSourceShortestPath();
				
				for (int j = 0; j < n; j++) {
					
					Vertex<T> v = graph.vertexList.get(j);
					
					result[i][j] = v.d + v.h - u.h;
				}
			}
			
			return result;
		}
	}
}
