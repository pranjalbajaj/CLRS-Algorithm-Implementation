package advancedDS.ObjOrientedFibHeap;

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

public class GenericFibonacciHeap<T> {
	
	GenericFibNode<T> minNode;
	
	int heapSize;
	
	public GenericFibonacciHeap() {
	
	}
	
	/**
	 * insert a new node to the left of min node.
	 * @param key
	 */
	public void insert(T obj, int key) {
		
		GenericFibNode<T> node = new GenericFibNode<T>(obj, key);
		
		insertNode(node);
		
		if (minNode.key > node.key) {
			
			minNode = node;
		}
		
		heapSize++;
	}
	
	private void insertNode(GenericFibNode<T> node) {

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
	
	public GenericFibNode<T> extractMin() {
		
		GenericFibNode<T> temp = minNode;

		GenericFibNode<T> child = minNode.child;

		while (minNode.degree != 0) {
			
			GenericFibNode<T> tempNode = child;
			
			child = child.left;

			insertNode(tempNode);

			minNode.degree--;
		}

		if (minNode.right == minNode) {

			minNode = null;
			
			heapSize--;
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
		
		int arraySize = ((int) Math.ceil(Math.log10(heapSize) / Math.log10(goldenRatio))) + 1;
		
		GenericFibNode<T>[] auxArray = new GenericFibNode[arraySize];
		
		int rootListSize = getRootListSize();
		
		for (int i = 0; i < rootListSize; i++) {
			
			GenericFibNode<T> x = minNode;
			
			minNode = minNode.right;
			
			int d = x.degree;
			
			while (auxArray[d] != null) {
				
				GenericFibNode<T> y = auxArray[d];
				
				if (x.key > y.key) {
					
					GenericFibNode<T> t = x;
					
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
	
	private GenericFibNode<T> fibHeapLink(GenericFibNode<T> y, GenericFibNode<T> x) {
		
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
			
			GenericFibNode<T> x = minNode;
			
			size++;
			
			x = x.right;
			
			while (x != minNode) {
				
				size++;
				
				x = x.right;
			}
		}
		
		return size;
	}

	public void decreaseKey(T obj, int newValue) {
		
		GenericFibNode<T> node = getNode(minNode, obj, 0);
		
		node.key = newValue;
		
		GenericFibNode<T> parent = node.parent;
		
		if (parent != null && parent.key > node.key) {
			
			cut(parent, node);
			
			cascadingCut(parent);
		}
		
		if (minNode.key > node.key) {
			
			minNode = node;
		}
	}
	
	public void decreaseKey(GenericFibNode<T> node, int newValue) {
		
		node.key = newValue;
		
		GenericFibNode<T> parent = node.parent;
		
		if (parent != null && parent.key > node.key) {
			
			cut(parent, node);
			
			cascadingCut(parent);
		}
		
		if (minNode.key > node.key) {
			
			minNode = node;
		}
	}
	
	private void cut(GenericFibNode<T> parent, GenericFibNode<T> node) {
		
		GenericFibNode<T> child = parent.child;
		
		while (child != node) {
			
			child = child.left;
		}
		
		if (child == node) {
			
			GenericFibNode<T> temp = child;
			
			if (parent.degree > 1) {

				child = child.left;

				child.right = temp.right;
				
				if (child.right.left == node) {
					
					child.right.left = child;
				}
				
			}
			else {
				
				child = null;
			}
			
			insertNode(temp);

			parent.child = child;

			parent.degree--;

			temp.mark = false;
		}
	}
	
	private void cascadingCut(GenericFibNode<T> node) {
		
		GenericFibNode<T> parent = node.parent;
		
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
	
	public void deleteKey(T obj) {
		
		decreaseKey(obj, Integer.MIN_VALUE);
		
		extractMin();
	}
	
	public GenericFibNode<T> getNode(T obj) {

		if (minNode != null)
			return getNode(minNode, obj, 0);

		return null;
	}
	
	private GenericFibNode<T> getNode(GenericFibNode<T> node, T obj, int nodeCount) {
		
		GenericFibNode<T> nodeToReturn = null;

		nodeCount++;

		if (nodeCount <= heapSize) {

			if (node.obj.equals(obj)) {

				return node;
			}
			if (node.child != null) {

				nodeToReturn = getNode(node.child, obj, nodeCount);
			}
			if (nodeToReturn == null && node.right != node) {

				nodeToReturn = getNode(node.right, obj, nodeCount);
			}

		}

		return nodeToReturn;
	}
	
	public boolean isEmpty() {
		
		if (this.heapSize <= 0)
			return true;
		
		return false;
	}
}

