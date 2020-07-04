package graphs.objectorientedgraph;

public class Edge<T> {
	
	public Vertex<T> v1;
	
	public Vertex<T> v2;
	
	public int weight;
	
	Edge(Vertex<T> v1, Vertex<T> v2) {
		
		this.v1 = v1;
		
		this.v2 = v2;
		
		this.weight = 0;
	}
	
	Edge(Vertex<T> v1, Vertex<T> v2, int weight) {
		
		this.v1 = v1;
		
		this.v2 = v2;
		
		this.weight = weight;
	}
	
	public String toString() {
		
		return "{" + v1.id + "/" + v1.data + "," + v2.id + "/" + v2.data + "}"; 
	}
	
	public Vertex<T> getAdjacentVertex(Vertex<T> v) {
		
		if (v.equals(v1)) {
			
			return v2;
		}
		else
			return v1;
	}
	
}

