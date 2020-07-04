package graphs.maximumflow.pushrelableGraph;

import java.util.ArrayList;

public class Vertex<T> {
	
	public int id;
	
	public T data;
	
	public double excessFlow;
	
	public int height;
	
	// predecessor, used in case of shortest path algorithms.
	public Vertex<T> predecessor;
	
	public ArrayList<Edge<T>> edgeList = null;
	
	Vertex<T> parent = null;
	
	public Vertex(int id, T data) {
		
		this.data = data;
		
		this.id = id;
		
		this.edgeList = new ArrayList<Edge<T>>();
		
	}
	
	public boolean equals(Object obj) {
		
		Vertex<T> v = (Vertex<T>) obj;
		
		if (this.id == v.id && this.data == v.data) {
			
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		
		return ""+this.id;
	}
	
}

