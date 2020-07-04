package graphs.maximumflow.pushrelableGraph;

public class Edge<T> {
	
	public Vertex<T> v1;
	
	public Vertex<T> v2;
	
	public double capacity;
	
	public double flow;
	
	Edge(Vertex<T> v1, Vertex<T> v2, double capacity) {
		
		this.v1 = v1;
		
		this.v2 = v2;
		
		this.capacity = capacity;
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

