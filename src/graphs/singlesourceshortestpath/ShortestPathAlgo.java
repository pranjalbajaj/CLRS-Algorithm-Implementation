package graphs.singlesourceshortestpath;

import graphs.objectorientedgraph.Graph;
import graphs.objectorientedgraph.Vertex;

public abstract class ShortestPathAlgo<T> {
	
	//assuming that the wt of all edges is less than 1000
	static final int initialWt = 1000;
	
	Graph<T> graph = null;
	
	Vertex<T> source = null;
	
	public void initialize(Graph<T> graph) {

		for (Vertex<T> vertex : graph.vertexList) {

			// ideally it should be assigned to a value larger than the highest weight path
			vertex.d = initialWt;

			vertex.predecessor = null;
		}

		// assign distance 0 to the source vertex
		source.d = 0;

	}

	public void relax(Vertex<T> u, Vertex<T> v, int weight) {

		if (v.d > u.d + weight) {

			v.d = u.d + weight;

			v.predecessor = u;
		}
	}
	
	public void printPath(Vertex<T> destination) {

		if (destination.predecessor != null) {

			printPath(destination.predecessor);
		}

		System.out.print(destination + "(" + destination.d + ")" + " -->");
	}
	
	public Vertex<T> getSource() {
		return source;
	}

	public void setSource(Vertex<T> source) {
		this.source = source;
	}

	public abstract void getSingleSourceShortestPath();

}
