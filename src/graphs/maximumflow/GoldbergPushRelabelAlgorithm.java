package graphs.maximumflow;

/**
 * GOLDBERG'S PUSH-RELABEL ALGORITHM:
 * 
 * Terminology: -> excess flow : the amount by which the flow in exceeds flow out.
 * 				   height : the height of the vertex.
 * 						    initially source has height equal to no. of vertices, and all other
 * 							vertex has height 0.
 * 				   push : the operation of pushing the flow along the adjacent edge.
 * 				   relabel : the operation of increasing the height of vertex. 
 * 
 * Explanation: -> push-relabel algorithm unlike ford-fulkerson algorithm, works on one vertex at 
 * 				   a time. Its a greedy approach and we try to flow the maximum flow out of each edge,
 * 				   in flowing maximum flow out of each edge, we perform either push or relabel operation,
 * 				   we can push flow only downhill, i.e, from a vertex of higher height to lower height, if
 * 				   there is a possibility of push but the adjacent vertices are at same height, we invoke,
 * 				   relabel operation to increase the height.
 * 
 * 				   The algorithm terminates when no vertex is overflowing, and the excess flow at sink is the
 * 				   the maximum flow obtained.
 * 
 * Complexity: -> O(V^2E) when relabel to front method is not used.
 * 			   -> O(V^3) when relabel to front is used.
 * 
 * 
 * @author pranjal
 *
 */

import java.util.LinkedList;

import graphs.maximumflow.pushrelableGraph.Edge;
import graphs.maximumflow.pushrelableGraph.Graph;
import graphs.maximumflow.pushrelableGraph.Vertex;

/**
 * Using the relabel to front method.
 * @author pranjal
 *
 * @param <T>
 */

public class GoldbergPushRelabelAlgorithm<T> {
	
	private Graph<T> graph = null;
	
	private Vertex<T> source = null;
	
	private Vertex<T> sink = null;
	
	private LinkedList<Vertex<T>> overflowVertexList = null;
	
	public GoldbergPushRelabelAlgorithm(Graph<T> graph, Vertex<T> source, Vertex<T> sink) {
		
		this.graph = graph;
		
		this.source = source;
		
		this.sink = sink;
		
		this.overflowVertexList = new LinkedList<Vertex<T>>();
	}
	
	public void preflow() {
		
		source.height = graph.vertexList.size();
		
		for (Edge<T> edge : source.edgeList) {
			
			edge.flow = edge.capacity;
			
			Vertex<T> v  = edge.getAdjacentVertex(source);
			
			v.excessFlow = edge.flow;
			
			overflowVertexList.add(v);
			
			updateReverseEdge(v, source, edge.flow);
		}
		
	}
	
	public boolean push(Vertex<T> u) {
		
		for (Edge<T> edge : u.edgeList) {
			
			Vertex<T> v = edge.getAdjacentVertex(u);
			
			if (u.height == v.height + 1 && edge.capacity > edge.flow) {
				
				double flow = Math.min(u.excessFlow, edge.capacity - edge.flow);
				
				edge.flow += flow;
				
				u.excessFlow -= flow;
				
				v.excessFlow += flow;
				
				updateReverseEdge(v, u, flow);
				
				if (v != source && v != sink && !overflowVertexList.contains(v)) {

					overflowVertexList.add(v);

				}
				
				return true;
			}
		}
		
		return false;
	}
	
	public void relable(Vertex<T> u) {
		
		int minHeight = Integer.MAX_VALUE;
		
		for (Edge<T> edge : u.edgeList) {

			if (edge.capacity > edge.flow) {
				
				Vertex<T> v = edge.getAdjacentVertex(u);
				
				if (minHeight > v.height) {
					
					minHeight = v.height;
					
					u.height = v.height + 1;
				}
			}
		}
	}
	
	public void updateReverseEdge(Vertex<T> v2, Vertex<T> v1, double flow) {
		
		Edge<T> reverseEdge = graph.getDirectedEdge(v2, v1);
		
		if (reverseEdge == null) {
			
			graph.addReverseDirectedEdge(v2.id, v1.id, flow);
		}
		else {
			
			reverseEdge.flow -= flow;
		}
	}
	
	public double getMaxFlow() {
		
		preflow();
		
		while (!overflowVertexList.isEmpty()) {
			
			Vertex<T> u = overflowVertexList.getFirst();
			
			boolean isPushPerformed = push(u);
			
			if (!isPushPerformed) {
				
				relable(u);
			}
			
			if (u.excessFlow == 0) {
				
				overflowVertexList.remove(u);
			}
		}
		
		return sink.excessFlow;
	}
	
	public void printFlowOnEachEdge() {
		
		for (Edge<T> edge : graph.edgeList) {
			
			System.out.println(edge.v1.id + " --> " + edge.v2.id + ": flow = " + edge.flow);
		}
	}
}
