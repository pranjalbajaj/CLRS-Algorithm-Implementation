package graphs.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import graphs.Graph;

public class TopologicalSort {
	
	//print a node when it is finished i.e it has no more vertices to be explored.
	public void topologicalSort(Graph graph) {
		
		LinkedList<Integer> sortedList = new LinkedList<Integer>();
		
		dfsTraversal(graph, sortedList);
		
		for (int i = 0; i < sortedList.size(); i++) {
			
			if (i != sortedList.size() - 1)
				System.out.print(sortedList.get(i) + " -> ");
			else
				System.out.print(sortedList.get(i));
		}
	}
	
	public void dfsTraversal(Graph graph, LinkedList<Integer> sortedList) {
		
		int[] parent = new int[graph.vertices];
		
		// before traversal, set parent of each vertex to -1
		Arrays.fill(parent, -1);
		
		for (int u = 0; u < graph.vertices; u++) {
			
			if (parent[u] == -1) {
				
				parent[u] = u;
				
				dfsVisit(graph, u, parent, sortedList);
			}
		}
	}

	private void dfsVisit(Graph graph, int u, int[] parent, LinkedList<Integer> sortedList) {
		
		for (int i = 0; i < graph.adjList[u].size(); i++) {
			
			int v = graph.adjList[u].get(i);
			
			if (parent[v] == -1) {
				
				parent[v] = u;
				
				dfsVisit(graph, v, parent, sortedList);
			}
		}
		
		sortedList.add(u);
	}

}
