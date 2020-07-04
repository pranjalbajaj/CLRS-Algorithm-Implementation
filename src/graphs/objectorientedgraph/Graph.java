package graphs.objectorientedgraph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<T> {
	
	public ArrayList<Vertex<T>> vertexList = null;
	
	public ArrayList<Edge<T>> edgeList = null;
	
	public Graph() {
		
		vertexList = new ArrayList<Vertex<T>>();
		
		edgeList = new ArrayList<Edge<T>>();
	}
	
	public void addVertex(int id, T data) {
		
		Vertex<T> vertex = new Vertex<T>(id, data);
		
		this.vertexList.add(id, vertex);
	}
	
	public void addEdge(int id1, int id2, int wt) {
		
		Vertex<T> v1 = vertexList.get(id1);
		
		Vertex<T> v2 = vertexList.get(id2);
		
		Edge<T> edge = new Edge<T>(v1, v2, wt);
		
		edgeList.add(edge);
		
		v1.edgeList.add(edge);
		
		v2.edgeList.add(edge);
	}
	
	public void addDirectedEdge(int id1, int id2, int wt) {
		
		Vertex<T> v1 = vertexList.get(id1);
		
		Vertex<T> v2 = vertexList.get(id2);
		
		Edge<T> edge = new Edge<T>(v1, v2, wt);
		
		edgeList.add(edge);
		
		v1.edgeList.add(edge);
		
	}
}