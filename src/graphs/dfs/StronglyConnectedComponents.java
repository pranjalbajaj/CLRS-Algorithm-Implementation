package graphs.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import graphs.Graph;

public class StronglyConnectedComponents {
	
	public void printStronglyConnectedComponents(Graph graph) {
		
		LinkedList<Integer> auxStack = new LinkedList<Integer>();
		
		//considering source as 0
		dfsTraversal(graph, 0, auxStack);
		
		Graph reverseGraph = reverseGraph(graph);
		
		boolean[] visited = new boolean[reverseGraph.vertices];
		
		List<Set<Integer>> result = new ArrayList<Set<Integer>>();
		
		while (!auxStack.isEmpty()) {
			
			int u = auxStack.remove(auxStack.size() - 1);
			
			if (!visited[u]) {
				
				visited[u] = true;
				
				Set<Integer> set = new HashSet<Integer>();
				
				dfsVisitForReverseGraph(reverseGraph, u, visited, set);
				
				result.add(set);
				
			}
			
		}
		
		for (int i = 0; i < result.size(); i++) {
			
			System.out.println(result.get(i));
		}
	}
	
	private Graph reverseGraph(Graph graph) {
		
		Graph revGraph = new Graph(graph.vertices);
		
		for (int i = 0; i < graph.vertices; i++) {
			
			revGraph.addVertex(i);
		}
		
		for (int i = 0; i < graph.vertices; i++) {
			
			for (int j = 0; j < graph.adjList[i].size(); j++) {
				
				int v = graph.adjList[i].get(j);
				
				revGraph.addEdge(v, i);
			}
		}
		
		return revGraph;
	}

	public void dfsTraversal(Graph graph, int source, LinkedList<Integer> auxStack) {
		
		boolean[] visited = new boolean[graph.vertices];

		for (int u = source; u < graph.vertices; u++) {

			if (!visited[u]) {

				visited[u] = true;

				dfsVisit(graph, u, visited, auxStack);
			}
		}
	
	}
	
	private void dfsVisit(Graph graph, int u, boolean[] visited, LinkedList<Integer> auxStack) {

		for (int i = 0; i < graph.adjList[u].size(); i++) {

			int v = graph.adjList[u].get(i);

			if (!visited[v]) {

				visited[v] = true;

				dfsVisit(graph, v, visited, auxStack);
			}
		}
		
		auxStack.add(u);
	}
	
	private void dfsVisitForReverseGraph(Graph graph, int u, boolean[] visited, Set<Integer> set) {

		set.add(u);
		
		for (int i = 0; i < graph.adjList[u].size(); i++) {	

			int v = graph.adjList[u].get(i);

			if (!visited[v]) {

				visited[v] = true;

				dfsVisitForReverseGraph(graph, v, visited, set);
			}
		}
	}

}
