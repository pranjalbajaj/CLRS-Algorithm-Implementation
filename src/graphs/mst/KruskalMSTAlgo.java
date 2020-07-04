package graphs.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import advancedDS.DisjointSet;
import graphs.objectorientedgraph.Edge;
import graphs.objectorientedgraph.Graph;
import graphs.objectorientedgraph.Vertex;

public class KruskalMSTAlgo {
	
	public void getMST(Graph<Character> graph) {
		
		Set<Edge<Character>> result = new HashSet<Edge<Character>>();
		
		//disjoint set
		DisjointSet ds = new DisjointSet();
		
		// call makeset for all vertices in the graph
		for (Vertex<Character> v : graph.vertexList) {
			
			ds.makeSet(v.id);
		}
		
		ArrayList<Edge<Character>> edgeList= graph.edgeList;
		
		//sort graph edges in increasing order of weight
		Collections.sort(edgeList, new MyComparator());
		
		for (Edge<Character> e : edgeList) {
			
			if (ds.findSet(e.v1.id) != ds.findSet(e.v2.id)) {
				
				ds.union(e.v1.id, e.v2.id);
				
				result.add(e);
			}
		}
		
		System.out.println(result);
	}

}

class MyComparator implements Comparator<Edge<Character>> {

	@Override
	public int compare(Edge<Character> o1, Edge<Character> o2) {
		
		if (o1.weight > o2.weight) {
			
			return 1;
		}
		else {
			
			return -1;
		}
	}
	
	
}
