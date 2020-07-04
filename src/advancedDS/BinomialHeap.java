package advancedDS;

/**
 * 
 * MAKE HEAP -> O(1)
 * INSERT -> O(log(n))
 * GET MINIMUM -> O(log(n))
 * EXTRACT MINIMUM -> O(log(n))
 * UNION -> O(log(n))
 * DECREASE KEY -> O(log(n))
 * DELETE -> O(log(n))
 */

/**
 * each binomial tree within a binomial heap is stored
 * in the left-child, right-sibling representation. 
 * @author pranjal
 *
 */
public class BinomialHeap {

	BinomialHeapNode heap = null;
	
	public BinomialHeap() {
		
	}
	
	/**
	 * O(log(n))
	 * @return
	 */
	public BinomialHeapNode getMinimum() {
		
		BinomialHeapNode y = null;
		
		BinomialHeapNode x = this.heap;
		
		int min = Integer.MAX_VALUE;
		
		while (x != null) {
			
			if (x.key < min) {
				
				min = x.key;
				
				y = x;
			}
			
			x = x.sibling;
		}
		
		return y;
	}
	
	/**
	 * Link two binomial trees with same degree.
	 * This makes y the child of z
	 * @param y
	 * @param z
	 */
	public void binomialLink(BinomialHeapNode y, BinomialHeapNode z) {
		
		y.parent = z;
		
		y.sibling = z.child;
		
		z.child = y;
		
		z.degree++;
	}
	
	public void merge(BinomialHeapNode t1, BinomialHeapNode t2) {
		
		while (t1 != null && t2 != null) {
			
			//case 1: when degrees are equal, merge the node of t2 into t1 and update t1 and t2
			//to point to their siblings.
			if (t1.degree == t2.degree) {
				
				BinomialHeapNode tmp = t2;
				
				t2 = t2.sibling;
				
				tmp.sibling = t1.sibling;
				
				t1.sibling = tmp;
				
				t1 = tmp.sibling;
				
			}
			// case 2: when degree(t1) < degree(t2)
			else if (t1.degree < t2.degree) {
				
				if (t1.sibling == null || t1.sibling.degree > t2.degree) {
					
					BinomialHeapNode tmp = t2;
					
					t2 = t2.sibling;
					
					tmp.sibling = t1.sibling;
					
					t1.sibling = tmp;
					
					t1 = tmp.sibling;
					
				}
				else {
					
					t1 = t1.sibling;
				}
			}
			// case 3: when degree(t1) > degree(t2)
			else {
				
				BinomialHeapNode tmp = t1;
				
                t1 = t2;
                
                t2 = t2.sibling;
                
                t1.sibling = tmp;
                
                if (tmp == heap) 
                {
                    heap = t1;
                }
                else
                {	
                	BinomialHeapNode pt = heap;
                	
                	while (pt.sibling != tmp) {
                		
                		pt = pt.sibling;
                	}
                	
                	pt.sibling = t1;
                }
			}
		}
		
		if (t1 == null && t2 != null) {
			
			t1 = heap;
			
			while (t1.sibling != null) {
				
				t1 = t1.sibling;
			}
			
			t1.sibling = t2;
		}
	}
	
	/**
	 * Merge the equal degree nodes.
	 * t2 is merged into t1.
	 * @param t1
	 * @param t2
	 */
	public void mergeNodes(BinomialHeapNode t1, BinomialHeapNode t2) {
		
		BinomialHeapNode tmp = t2;
		
		t2 = t2.sibling;
		
		tmp.sibling = t1.sibling;
		
		t1.sibling = tmp;
		
		t1 = tmp.sibling;
	}
	
	public void union(BinomialHeapNode t1, BinomialHeapNode t2) {
		
		merge(t1, t2);
		
		BinomialHeapNode prev = null;
		
		BinomialHeapNode curr = heap;
		
		BinomialHeapNode next = heap.sibling;
		
		while (next != null) {
			
			// if degrees of curr and next are not equal or the first three nodes have same degree
			if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
				
				prev = curr;
				
				curr = next;
			}
			else {
				
				if (next.key >= curr.key) {
					
					curr.sibling = next.sibling;
					
					binomialLink(next, curr);
							
				}
				else {
					
					if (prev == null) {
						
						heap = next;
					}
					else {
						
						prev.sibling = next;
					}
					
					binomialLink(curr, next);
					
					curr = next;
				}
			}
			
			next = curr.sibling;
		}
	}
	
	public void insert(int key) {
		
		BinomialHeapNode x = new BinomialHeapNode(key);
		
		if (heap == null) {
			
			heap = x;
		}
		else {
			
			union(heap, x);
		}
	}
	
	public BinomialHeapNode extractMinimun() {
		
		BinomialHeapNode minNode = getMinimum();
		
		BinomialHeapNode prev = null;
		
		BinomialHeapNode temp = heap;
		
		// get the previous node of min node
		//after this loop minNode and temp will point to the same node
		while (temp.key != minNode.key) {
			
			prev = temp;
			
			temp = temp.sibling;
			
		}
		
		if (prev == null) {
			
			heap = heap.sibling;
		}
		else {
			
			prev.sibling = temp.sibling;
		}
		
		temp = temp.child;
		
		// call reverse, to reverse the order of siblings in temp
		temp = reverse(temp);
		
		union(heap, temp);
		
		return minNode;
	}

	private BinomialHeapNode reverse(BinomialHeapNode temp) {
		
		BinomialHeapNode prev = null;
		
		BinomialHeapNode curr = temp;
		
		BinomialHeapNode next = null;
		
		while (curr != null) {
			
			curr.parent = null;
			
			next = curr.sibling;
			
			curr.sibling = prev;
			
			prev = curr;
			
			curr = next;
		}
		
		temp = prev;
		
		return temp;
	}
	
	private BinomialHeapNode getNodeWithKey(BinomialHeapNode node, int key) {
		
		BinomialHeapNode temp = node;
		
		BinomialHeapNode nodeToReturn = null;
		
		while (temp != null) {
			
			if (temp.key == key) {
				
				nodeToReturn = temp;
				
				break;
			}
			
			if (temp.child == null) {
				
				temp = temp.sibling;
			}
			else {
				
				nodeToReturn = getNodeWithKey(temp.child, key);
				
				if (nodeToReturn == null) {
					
					temp = temp.sibling;
				}
				else {
					
					break;
				}
			}
		}
		
		return nodeToReturn;
	}
	
	
	public void decreaseKey(int oldValue, int newValue) {
		
		if (newValue > oldValue) {
			
			System.out.println("Given value is greater than old value, OPERATION NOT ALLOWED");
			
			return;
		}
		
		BinomialHeapNode node = getNodeWithKey(heap, oldValue);
		
		node.key = newValue;
		
		BinomialHeapNode parent = node.parent;
		
		// same procedure as in case of binary min heap.
		while (parent != null && parent.key > node.key) {
			
			int temp = parent.key;
			
			parent.key = node.key;
			
			node.key = temp;
			
			node = parent;
			
			parent = parent.parent;
		}
	}
	
	public void delete(int key) {
		
		decreaseKey(key, Integer.MIN_VALUE);
		
		extractMinimun();
	}
}


class BinomialHeapNode {
	
	int key;
	
	// number of children
	int degree;
	
	BinomialHeapNode parent;
	
	// pointer to leftmost child
	BinomialHeapNode child;
	
	// pointer to right sibling
	BinomialHeapNode sibling;
	
	public BinomialHeapNode(int key) {
		
		this.key = key;
		
		this.degree = 0;
		
		this.parent = null;
		
		this.child = null;
		
		this.sibling = null;
	}
}