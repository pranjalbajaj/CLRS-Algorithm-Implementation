package advancedDS;

import java.util.HashMap;

/**
 * Disjoint set implementation using "union by rank" and "path compression"
 * @author pranjal
 *
 */
public class DisjointSet {
	
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();

	public void makeSet(int key) {
		
		Node node = new Node(key);
		
		map.put(key, node);
	}
	
	/**
	 * returns the representative of the set.
	 * @param key
	 * @return
	 */
	public int findSet(int key) {
		
		Node node = map.get(key);
		
		return findSet(node).key;
	}
	
	private Node findSet(Node node) {

		if (node.parent != node) {

			// by setting node.parent, we are performing path compression.
			node.parent = findSet(node.parent);
		}
		
		return node.parent;
	}
	
	public void union(int key1, int key2) {
		
		Node node1 = map.get(key1);
		
		Node node2 = map.get(key2);
		
		Node parent1 = findSet(node1);
		
		Node parent2 = findSet(node2);
		
		// already part of same set
		if (parent1 == parent2) {
			
			return;
		}
		
		// higher rank parent becomes parent of other 
		if (parent1.rank >= parent2.rank) {
			
			if (parent1.rank == parent2.rank) {
				
				parent1.rank++;
			}
			
			parent2.parent = parent1;
				
		}
		else {
			
			parent1.parent = parent2;
		}
	}
}

class Node {
	
	int key;
	
	int rank;
	
	Node parent;
	
	public Node(int key) {
		
		this.key = key;
		
		this.rank = 0;
		
		this.parent = this;
	}
}
