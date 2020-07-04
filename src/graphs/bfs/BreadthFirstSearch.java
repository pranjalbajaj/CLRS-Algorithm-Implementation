package graphs.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import graphs.Graph;

public class BreadthFirstSearch {

	LinkedList<Integer> queue = null;

	// store distance of each vertex from source vertex;
	int[] distances = null;

	// stores the parent of each vertex
	int[] parent = null;

	public BreadthFirstSearch() {

		queue = new LinkedList<Integer>();
	}

	public void bfsTraversal(Graph graph, int source) {

		boolean visited[] = new boolean[graph.vertices];

		queue.add(source);

		visited[source] = true;

		while (queue.size() > 0) {

			int u = queue.remove();

			System.out.print(u + " ");

			for (int i = 0; i < graph.adjList[u].size(); i++) {

				int v = graph.adjList[u].get(i);

				if (!visited[v]) {

					queue.add(v);

					visited[v] = true;

				}
			}
		}

	}

	public void shortestPath(Graph graph, int source) {

		distances = new int[graph.vertices];

		parent = new int[graph.vertices];
		// initially before traversing, all distances are -1
		Arrays.fill(distances, -1);

		// initially before traversing, all parents are -1
		Arrays.fill(parent, -1);

		queue.add(source);

		distances[source] = 0;

		while (queue.size() > 0) {

			int u = queue.remove();

			for (int i = 0; i < graph.adjList[u].size(); i++) {

				int v = graph.adjList[u].get(i);

				if (distances[v] == -1) {

					queue.add(v);

					parent[v] = u;

					distances[v] = distances[u] + 1;

				}
			}
		}

		System.out.println();

		printShortestPathAndDistance(source);

	}

	private void printShortestPathAndDistance(int source) {

		for (int i = 0; i < distances.length; i++) {

			System.out.println("dest = " + i + " -> Shortest distance:" + distances[i]);

			printPath(source, i);

			System.out.println();
		}
	}

	public int printPath(int source, int dest) {

		if (source == dest) {

			System.out.print("path: " + source);

			return 0;
		}

		if (parent[dest] == -1) {

			System.out.print("no path exists from " + source + " to " + dest);

		} else {

			int x = printPath(source, parent[dest]);

			if (x != -1) {
				
				System.out.print(" -> " + dest);

				return 0;
			}
		}

		return -1;

	}

}