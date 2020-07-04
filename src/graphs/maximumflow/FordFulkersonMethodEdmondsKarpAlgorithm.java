package graphs.maximumflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FordFulkersonMethodEdmondsKarpAlgorithm {
	
	private List<List<Integer>> augmentedPaths = null;
	
	private double maxFlow = 0;
	
	public FordFulkersonMethodEdmondsKarpAlgorithm() {
		
		this.augmentedPaths = new ArrayList<List<Integer>>();
	}
	
	/**
	 * O(VE^2)
	 * @param capacity
	 * @param source
	 * @param sink
	 * @return
	 */
	public double getMaxFlow(double[][] capacity, int source, int sink) {
		
		double[][] residualCapacity = new double[capacity.length][capacity.length];
		
		for (int i = 0; i < capacity.length; i++) {
			
			residualCapacity[i] = Arrays.copyOf(capacity[i], capacity[i].length);
		}
		
		int augmentedPathIndex = 0;
		
		/**
		 * Get augmented path using BFS
		 * 
		 * Edmond Karp Algo
		 * 
		 */
		while (breadthFirstSearch(residualCapacity, source, sink)) {
			
			ArrayList<Integer> augmentedPath = (ArrayList<Integer>) augmentedPaths.get(augmentedPathIndex);
			
			double flow = Integer.MAX_VALUE;
			
			for (int i = 0; i < augmentedPath.size() - 1; i++) {
				
				int u = augmentedPath.get(i);
				
				int v = augmentedPath.get(i + 1);
				
				if (flow > residualCapacity[u][v]) {
					
					flow = residualCapacity[u][v];
				}
			}
			
			maxFlow+=flow;
			
			for (int i = 0; i < augmentedPath.size() - 1; i++) {
				
				int u = augmentedPath.get(i);
				
				int v = augmentedPath.get(i + 1);
				
				residualCapacity[u][v]-= flow;
				
				residualCapacity[v][u]+= flow;
			}
			
			augmentedPathIndex++;
		}
		
		return maxFlow;
	}

	/**
	 * returns true if we get any augmented path otherwise return false
	 */
	private boolean breadthFirstSearch(double[][] residualCapacity, int source, int sink) {

		boolean visited[] = new boolean[residualCapacity.length];

		int[] parent = new int[residualCapacity.length];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		queue.add(source);

		visited[source] = true;

		boolean augmentedPathPresent = false;

		while (queue.size() > 0) {

			int u = queue.remove();

			for (int i = 0; i < residualCapacity.length; i++) {

				int v = i;

				if (!visited[v] && residualCapacity[u][v] > 0) {

					queue.add(v);

					parent[v] = u;

					visited[v] = true;
					
					if (v == sink) {
						
						augmentedPathPresent = true;
						
						ArrayList<Integer> augmentedPath = new ArrayList<Integer>();
						
						prepareAugmentedPath(parent, source, sink, augmentedPath);
						
						augmentedPaths.add(augmentedPath);
						
						break;
					}

				}
			}
		}

		return augmentedPathPresent;
	}
	
	public void prepareAugmentedPath(int[] parent, int source, int sink, ArrayList<Integer> augmentedPath) {
		
		if (sink != source) {

			prepareAugmentedPath(parent, source, parent[sink], augmentedPath);
		}
		
		augmentedPath.add(sink);
		
	}
	
	public void printaugmentedPaths() {
		
		for (List<Integer> augmentedPath : augmentedPaths) {
			
			System.out.println(augmentedPath);
		}
	}

}
