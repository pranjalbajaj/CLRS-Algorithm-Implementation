package graphs.maximumflow.pushrelableGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph<T> {
	
	public ArrayList<Vertex<T>> vertexList = null;
	
	public ArrayList<Edge<T>> edgeList = null;
	
	private HashMap<String, Edge<T>> vertexToEdgeMap = null;
	
	public Graph() {
		
		vertexList = new ArrayList<Vertex<T>>();
		
		edgeList = new ArrayList<Edge<T>>();
		
		vertexToEdgeMap = new HashMap<String, Edge<T>>();
	}
	
	public void addVertex(int id, T data) {
		
		Vertex<T> vertex = new Vertex<T>(id, data);
		
		this.vertexList.add(id, vertex);
	}
	
	public void addEdge(int id1, int id2, double wt) {
		
		Vertex<T> v1 = vertexList.get(id1);
		
		Vertex<T> v2 = vertexList.get(id2);
		
		Edge<T> edge = new Edge<T>(v1, v2, wt);
		
		edgeList.add(edge);
		
		v1.edgeList.add(edge);
		
		v2.edgeList.add(edge);
	}
	
	public void addDirectedEdge(int id1, int id2, double wt) {
		
		Vertex<T> v1 = vertexList.get(id1);
		
		Vertex<T> v2 = vertexList.get(id2);
		
		Edge<T> edge = new Edge<T>(v1, v2, wt);
		
		edgeList.add(edge);
		
		vertexToEdgeMap.put(v1.id + "-" + v2.id, edge);
		
		v1.edgeList.add(edge);
		
	}
	
	public void addReverseDirectedEdge(int id1, int id2, double wt) {
		
		Vertex<T> v1 = vertexList.get(id1);
		
		Vertex<T> v2 = vertexList.get(id2);
		
		Edge<T> edge = new Edge<T>(v1, v2, wt);
		
		vertexToEdgeMap.put(v1.id + "-" + v2.id, edge);
		
		v1.edgeList.add(edge);
		
	}
	
	public Edge<T> getDirectedEdge(Vertex<T> v1, Vertex<T> v2) {
		
		return vertexToEdgeMap.get(v1.id + "-" + v2.id);
	}
}