package tree;

import java.util.ArrayList;

public class TreeHeightAndDiameter {
	
	TNode[] treeNodesArray = null;
	
	int maxDepthNodeId = 0;
	
	public static void main(String[] args) {
		
		TreeHeightAndDiameter t = new TreeHeightAndDiameter();
		
		t.createTree(13);
		
		/*
		 * int h = t.getTreeHeight(0);
		 * 
		 * System.out.println(h);
		 */
		
		int d  = t.getTreeDiameter(2);
		
		System.out.println(d);
	}
	
	public void createTree(int noOfNodes) {
		
		treeNodesArray = new TNode[noOfNodes];
		
		addEdge(0, 1);
		
		addEdge(1, 2);
		
		addEdge(1, 3);
		
		addEdge(1, 4);
		
		addEdge(4, 5);
		
		addEdge(5, 7);
		
		addEdge(4, 6);
		
		addEdge(0, 8);
		
		addEdge(0, 9);
		
		addEdge(9, 10);
		
		addEdge(10, 12);
		
		addEdge(9, 11);
		
	}
	
	private void addEdge(int id1, int id2) {
		
		if (treeNodesArray[id1] == null) {
			
			treeNodesArray[id1] = new TNode(id1);
		}
		
		if (treeNodesArray[id2] == null) {
			
			treeNodesArray[id2] = new TNode(id2);
		}
		
		treeNodesArray[id1].adjList.add(treeNodesArray[id2]);
		
		treeNodesArray[id2].adjList.add(treeNodesArray[id1]);
	}
	
	public int getTreeHeight(int rootId) {
		
		return height(rootId, -1, new int[treeNodesArray.length]);
	}
	
	private int height(int nodeId, int parentId, int[] h) {
		
		TNode node = treeNodesArray[nodeId];
		
		for (TNode child : node.adjList) {
			
			if (child.id != parentId) {
			
				h[nodeId] = Math.max(h[nodeId], 1 + height(child.id, nodeId, h));
			}
		}
		
		return h[nodeId];
	}
	
	public int getTreeDiameter(int rootId) {
		
		maxDepthNodeId(rootId, -1, 0, 0);
		
		return height(maxDepthNodeId, -1, new int[treeNodesArray.length]);
	}

	private int maxDepthNodeId(int nodeId, int parentId, int maxLevel, int level) {
		
		TNode node = treeNodesArray[nodeId];
		
		for (TNode child : node.adjList) { 
			
			if (child.id != parentId) {
								
				maxLevel = maxDepthNodeId(child.id, nodeId, maxLevel, level + 1);
			}
		}
		
		if (level > maxLevel) {
			
			maxLevel = level;
			
			maxDepthNodeId = nodeId;
		}
		
		return maxLevel;
	}
}

class TNode {
	
	int id = -1;
	
	ArrayList<TNode> adjList = null;
	
	public TNode(int id) {
		
		this.id = id;
		
		this.adjList = new ArrayList<TNode>();
	}
}