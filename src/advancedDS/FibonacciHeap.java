package advancedDS;

/**
 * 
 * MAKE HEAP -> O(1)
 * INSERT -> O(1)
 * GET MINIMUM -> O(1)
 * EXTRACT MINIMUM -> O(log(n))
 * UNION -> O(1)
 * DECREASE KEY -> O(1)
 * DELETE -> O(log(n))
 */

public class FibonacciHeap {
	
	FibNode minNode;
	
	int heapSize;
	
	public FibonacciHeap() {
	
	}
	
	/**
	 * insert a new node to the left of min node.
	 * @param key
	 */
	public void insert(int key) {
		
		FibNode node = new FibNode(key);
		
		insertNode(node);
		
		if (minNode.key > node.key) {
			
			minNode = node;
		}
		
		heapSize++;
	}
	
	private void insertNode(FibNode node) {

		if (minNode == null) {

			minNode = node;

			minNode.left = minNode;

			minNode.right = minNode;
			
		} else {

			minNode.left.right = node;

			node.left = minNode.left;

			node.right = minNode;

			minNode.left = node;
			
			node.parent = null;
		}
	}
	
	public FibNode extractMin() {
		
		FibNode temp = minNode;

		FibNode child = minNode.child;

		while (minNode.degree != 0) {
			
			FibNode tempNode = child;
			
			child = child.left;

			insertNode(tempNode);

			minNode.degree--;
		}

		if (minNode.right == minNode) {

			minNode = null;
		} else {

			minNode.left.right = minNode.right;

			minNode.right.left = minNode.left;

			minNode = minNode.right;
			
			heapSize--;

			consolidate();
		}

		return temp;
	}

	private void consolidate() {
		
		double goldenRatio = 1.618;
		
		int arraySize = (int) Math.ceil(Math.log10(heapSize) / Math.log10(goldenRatio));
		
		FibNode[] auxArray = new FibNode[arraySize];
		
		int rootListSize = getRootListSize();
		
		for (int i = 0; i < rootListSize; i++) {
			
			FibNode x = minNode;
			
			minNode = minNode.right;
			
			int d = x.degree;
			
			while (auxArray[d] != null) {
				
				FibNode y = auxArray[d];
				
				if (x.key > y.key) {
					
					FibNode t = x;
					
					x = y;
					
					y = t;
				}
				
				fibHeapLink(y, x);
					
				auxArray[d] = null;
				
				d++;
			}
			
			auxArray[d] = x;
			
		}
		
		minNode = null;
		
		for (int i = 0; i < arraySize; i++) {
			
			if (auxArray[i] != null) {
				
				if (minNode == null) {
					
					minNode = auxArray[i];
				}
				else {
					
					if (minNode.key > auxArray[i].key) {
						
						minNode = auxArray[i];
					}
				}
					
			}
		}
		
	}
	
	private FibNode fibHeapLink(FibNode y, FibNode x) {
		
		y.left.right = y.right;
		
		y.right.left = y.left;
		
		y.parent = x;
		
		if (x.child == null) {
			
			x.child = y;
			
			y.left = y;
			
			y.right = y;
		}
		//insert to the left of existing child
		else {
			
			y.right = x.child;
			
			y.left = x.child.left;
			
			x.child.left = y;
			
			y.left.right = y;
		}
		
		x.degree++;
		
		y.mark = false;
		
		return x;
	}
	
	private int getRootListSize() {
		
		int size = 0;
		
		if (minNode != null) {
			
			FibNode x = minNode;
			
			size++;
			
			x = x.right;
			
			while (x != minNode) {
				
				size++;
				
				x = x.right;
			}
		}
		
		return size;
	}
	
	public void decreaseKey(int oldValue, int newValue) {
		
		FibNode node = getNodeWithKey(minNode, oldValue, 0);
		
		node.key = newValue;
		
		FibNode parent = node.parent;
		
		if (parent != null && parent.key > node.key) {
			
			cut(parent, node);
			
			cascadingCut(parent);
		}
		
		if (minNode.key > node.key) {
			
			minNode = node;
		}
	}
	
	private void cut(FibNode parent, FibNode node) {
		
		FibNode child = parent.child;
		
		while (child != node) {
			
			child = child.left;
		}
		
		if (child == node) {
			
			FibNode temp = child;
			
			child = child.left;
			
			child.right = temp.right;
			
			insertNode(temp);
			
			parent.child = child;
			
			parent.degree--;
			
			temp.mark = false;
		}
	}
	
	private void cascadingCut(FibNode node) {
		
		FibNode parent = node.parent;
		
		if (parent != null) {
			
			if (!node.mark) {
				
				node.mark = true;
			}
			else {
				
				cut(parent, node);
				
				cascadingCut(parent);
			}
		}
	}
	
	public void deleteKey(int key) {
		
		decreaseKey(key, Integer.MIN_VALUE);
		
		extractMin();
	}

	private FibNode getNodeWithKey(FibNode node, int key, int nodeCount) {

		FibNode nodeToReturn = null;

		nodeCount++;

		if (nodeCount != heapSize) {

			if (node.key == key) {

				return node;
			}
			if (node.child != null) {

				nodeToReturn = getNodeWithKey(node.child, key, nodeCount);
			}
			if (nodeToReturn == null && node.right != node) {

				nodeToReturn = getNodeWithKey(node.right, key, nodeCount);
			}

		}

		return nodeToReturn;

		/*
		 * if (node.right == node) {
		 * 
		 * if (node.key == key) {
		 * 
		 * nodeToReturn = node;
		 * 
		 * return nodeToReturn;
		 * 
		 * } else {
		 * 
		 * if (node.child != null) {
		 * 
		 * node = node.child; } } }
		 * 
		 * while (node.right != node) {
		 * 
		 * if (node.key == key) {
		 * 
		 * nodeToReturn = node;
		 * 
		 * break; }
		 * 
		 * if (node.child == null) {
		 * 
		 * node = node.right; } else {
		 * 
		 * nodeToReturn = getNodeWithKey(node.child, key);
		 * 
		 * if (nodeToReturn == null) {
		 * 
		 * node = node.right; } else {
		 * 
		 * break; } } }
		 * 
		 * return nodeToReturn;
		 */

	}
}

class FibNode {
	
	int key;
	
	int degree;
	
	boolean mark;
	
	FibNode parent;
	
	FibNode child;
	
	FibNode left;
	
	FibNode right;
	
	public FibNode(int key) {
		
		this.key = key;
	}
}