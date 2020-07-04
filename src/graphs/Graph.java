package graphs;

import java.util.LinkedList;

public class Graph {

	// no. of vertices in a graph
	public int vertices;

	// adjacency list to map edges
	public LinkedList<Integer>[] adjList = null;

	public Graph(int vertices) {

		this.vertices = vertices;

		adjList = new LinkedList[vertices];
	}

	public void addVertex(int vertex) {

		adjList[vertex] = new LinkedList<Integer>();
	}

	public void addEdge(int v1, int v2) {

		adjList[v1].add(v2);
	}
}
