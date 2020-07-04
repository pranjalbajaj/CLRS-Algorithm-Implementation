package graphs.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import graphs.Graph;

public class DepthFirstSearch {

	public void dfsTraversal(Graph graph, int source) {

		int[] parent = new int[graph.vertices];

		// before traversal, set parent of each vertex to -1
		Arrays.fill(parent, -1);

		for (int u = source; u < graph.vertices; u++) {

			if (parent[u] == -1) {

				parent[u] = u;

				dfsVisit(graph, u, parent);
			}
		}
	}

	private void dfsVisit(Graph graph, int u, int[] parent) {

		System.out.print(u + " ");

		for (int i = 0; i < graph.adjList[u].size(); i++) {

			int v = graph.adjList[u].get(i);

			if (parent[v] == -1) {

				parent[v] = u;

				dfsVisit(graph, v, parent);
			}
		}
	}

	public int getPaths(Graph graph, int source, int destination) {

		// keep track of vertex visited
		boolean[] visited = new boolean[graph.vertices];

		// store path from intermediate vertex to destination vertex
		// --memoization--
		int[] paths = new int[graph.vertices];

		return getPaths(graph, source, destination, visited, paths);
	}

	public void printPaths(Graph graph, int source, int destination) {

		// list to store path
		ArrayList<Integer> pathList = new ArrayList<Integer>();

		// keep track of vertex visited
		boolean[] visited = new boolean[graph.vertices];

		printPaths(graph, source, destination, visited, pathList);

	}

	private void printPaths(Graph graph, int source, int destination, boolean[] visited, ArrayList<Integer> pathList) {

		if (source == destination) {

			System.out.println(pathList);
		}
		/**
		 * if there is a cycle in the graph, visited array will keep track of all the
		 * nodes visited and in cycle we will hit one of the nodes earlier visited, in
		 * that case we will return as no path is identified.
		 */
		if (visited[source]) {

			return;
		}

		for (int i = 0; i < graph.adjList[source].size(); i++) {

			visited[source] = true;

			pathList.add(source);

			printPaths(graph, graph.adjList[source].get(i), destination, visited, pathList);

			visited[source] = false;

			pathList.remove(pathList.size() - 1);
		}
	}

	private int getPaths(Graph graph, int source, int destination, boolean[] visited, int[] paths) {

		if (source == destination) {

			return 1;
		}
		/**
		 * if there is a cycle in the graph, visited array will keep track of all the
		 * nodes visited and in cycle we will hit one of the nodes earlier visited, in
		 * that case we will return 0 as no path is identified.
		 */
		if (visited[source]) {

			return 0;
		} else if (paths[source] != 0) {

			return paths[source];
		}

		for (int i = 0; i < graph.adjList[source].size(); i++) {

			visited[source] = true;

			int path = getPaths(graph, graph.adjList[source].get(i), destination, visited, paths);

			visited[source] = false;

			paths[source] = paths[source] + path;
		}

		return paths[source];

	}
}
