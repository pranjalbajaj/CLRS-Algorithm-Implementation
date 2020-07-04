package graphs.dfs;

import graphs.Graph;

public class EdgeAndCycleDetection {

	private int time = 0;

	/**
	 * Edge classification is dependent on source, Different source will result in
	 * different classification of the same edges. -------------------------------
	 * for edge (u, v)
	 * 
	 * Visited -> Not visited = Tree edge
	 * 
	 * Incomplete(u) -> Incomplete(v) = Back edge
	 * 
	 * Incomplete(u) -> Complete(v) : -> time(u) > time(v) = Forward edge
	 * 
	 * -> time(u) > time(v) = Cross edge
	 * 
	 * 
	 * 
	 * For convenience 0 has been taken as source in below code.
	 * 
	 * @param graph
	 */
	public void printEdgeType(Graph graph, int source) {

		dfsTraversal(graph, source);

		time = 0;
	}

	public void dfsTraversal(Graph graph, int source) {

		boolean[] visited = new boolean[graph.vertices];

		int[] discovery = new int[graph.vertices];

		boolean[] complete = new boolean[graph.vertices];

		for (int u = source; u < graph.vertices; u++) {

			if (!visited[u]) {

				visited[u] = true;

				dfsVisit(graph, u, visited, discovery, complete);
			}
		}
	}

	public boolean dfsTraversalToDetectCycle(Graph graph, int source) {

		boolean[] visited = new boolean[graph.vertices];

		boolean[] complete = new boolean[graph.vertices];

		for (int u = source; u < graph.vertices; u++) {

			if (!visited[u]) {

				visited[u] = true;

				return dfsVisitToDetectCycle(graph, u, visited, complete);
			}
		}

		return false;
	}

	private void dfsVisit(Graph graph, int u, boolean[] visited, int[] discoveryTime, boolean[] complete) {

		time++;

		discoveryTime[u] = time;

		for (int i = 0; i < graph.adjList[u].size(); i++) {

			int v = graph.adjList[u].get(i);

			if (!visited[v]) {

				visited[v] = true;

				System.out.println("(" + u + ", " + v + ") --> Tree edge");

				dfsVisit(graph, v, visited, discoveryTime, complete);
			} else if (!complete[v]) {

				System.out.println("(" + u + ", " + v + ") --> Back edge");
			} else {

				if (discoveryTime[v] > discoveryTime[u]) {

					System.out.println("(" + u + ", " + v + ") --> Forward edge");
				} else {

					System.out.println("(" + u + ", " + v + ") --> Cross edge");
				}

			}
		}

		complete[u] = true;
	}

	private boolean dfsVisitToDetectCycle(Graph graph, int u, boolean[] visited, boolean[] complete) {

		for (int i = 0; i < graph.adjList[u].size(); i++) {

			int v = graph.adjList[u].get(i);

			if (!visited[v]) {

				visited[v] = true;

				return dfsVisitToDetectCycle(graph, v, visited, complete);
			} else if (!complete[v]) {

				return true;
			}

		}

		complete[u] = true;

		return false;
	}

	public boolean isGraphContainsCycle(Graph graph) {

		// considering 0 as source
		return dfsTraversalToDetectCycle(graph, 0);
	}

}
