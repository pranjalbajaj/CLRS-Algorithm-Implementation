/**
 * RED BLACK TREE:
 * 
 * Terminology: -> Node consists of : key, left child, right child, parent and Color
 * 					
 * 
 * Explanation: AVL tree involves lot of rotation, hence increase the time for insertion and deletion
 * 				Red Black tree do not have strict rules like AVL tree, hence it involves at max
 * 				2 rotations.
 * 
 * 				Red Black tree has an extra bit preserved for Color.
 * 
 * 				Properties of Red Black tree.
 * 					-> Root node is always black.
 * 					-> No Red-Red parent child relationship
 * 					   (No Red-red adjacent nodes)
 * 					-> Every path from root to leaf node must have
 * 					   same number of black nodes. 
 * 
 *             
 * Complexity: Insertion: O(log(n)) = O(height of tree)
 * 			   Deletion: O(log(n)) = O(height of tree)						   
 * 							 	
 * @author pranjal
 *
 */

package tree;

public class RedBlackTree {
	
	private static RBNode root = RBNode.nilNode;
	
	public static void main(String[] args) {

		RedBlackTree redBlackTree = new RedBlackTree();

		int[] arr = { 10, 20, -10, 15, 17, 40, 50, 60 };

		for (int i = 0; i < arr.length; i++) {

			redBlackTree.RBInsert(arr[i]);
		}

		redBlackTree.inorder(root);

		redBlackTree.delete(20);
		
		System.out.println();
		
		redBlackTree.inorder(root);
		
		System.out.println("\n" + redBlackTree.getElementAtRank(root, 5));
		
		System.out.println("\n" + redBlackTree.getRankofElement(15));

	}

	public RBNode BSTinsert(RBNode root, RBNode nodeTOInsert) {

		if (root == RBNode.nilNode) {

			return nodeTOInsert;
		}

		if (root.getValue() > nodeTOInsert.getValue()) {

			root.setLeft(BSTinsert(root.getLeft(), nodeTOInsert));

			root.getLeft().setParent(root);

		}

		else if (root.getValue() < nodeTOInsert.getValue()) {

			root.setRight(BSTinsert(root.getRight(), nodeTOInsert));

			root.getRight().setParent(root);

		}
		
		root.incrementSize();
		
		return root;

	}

	public void RBInsert(int num) {

		RBNode nodeToInsert = new RBNode(num);

		root = BSTinsert(root, nodeToInsert);

		insertFixup(nodeToInsert);
	}

	/**
	 * Fix the violation of red black tree property due to insertion.
	 * 
	 * @param parent
	 * @param node
	 */
	private void insertFixup(RBNode node) {

		RBNode parent = node.getParent();

		while (parent != RBNode.nilNode && parent.getColor() == Color.RED) {
			RBNode grandParent = parent.getParent();

			if (parent == grandParent.getLeft()) {

				RBNode uncle = grandParent.getRight();

				if (uncle != RBNode.nilNode && uncle.getColor() == Color.RED) {

					parent.setColor(Color.BLACK);

					uncle.setColor(Color.BLACK);

					if (grandParent != root) {

						grandParent.setColor(Color.RED);
					}
					node = grandParent;
				} else {

					if (node == parent.getRight()) {

						node = parent;

						leftRotate(node);
					}
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					rightRotate(node.getParent().getParent());
				}
			} else {

				RBNode uncle = grandParent.getLeft();

				if (uncle != RBNode.nilNode && uncle.getColor() == Color.RED) {

					parent.setColor(Color.BLACK);

					uncle.setColor(Color.BLACK);

					if (grandParent != root) {

						grandParent.setColor(Color.RED);
					}
					node = grandParent;
				} else {

					if (node == parent.getLeft()) {

						node = parent;

						rightRotate(node);
					}
					node.getParent().setColor(Color.BLACK);
					node.getParent().getParent().setColor(Color.RED);
					leftRotate(node.getParent().getParent());
				}

			}

			parent = node.getParent();
		}

		root.setColor(Color.BLACK);
	}

	public RBNode getMinimum(RBNode node) {

		if (node != RBNode.nilNode) {

			while (node.getLeft() != RBNode.nilNode) {

				node = node.getLeft();
			}
		}
		return node;
	}

	public RBNode searchRecord(RBNode node, int num) {

		if (node == RBNode.nilNode || node.getValue() == num) {

			return node;
		}

		if (node.getValue() > num) {

			return searchRecord(node.getLeft(), num);
		}

		return searchRecord(node.getRight(), num);
	}

	/*
	 * 
	 * @param pivotRBNode
	 * 
	 * @return
	 */
	private RBNode rightRotate(RBNode pivotRBNode) {

		RBNode leftOfPivot = pivotRBNode.getLeft();

		pivotRBNode.setLeft(leftOfPivot.getRight());

		if (leftOfPivot.getRight() != RBNode.nilNode) {

			leftOfPivot.getRight().setParent(pivotRBNode);
		}

		leftOfPivot.setParent(pivotRBNode.getParent());

		if (pivotRBNode.getParent() == RBNode.nilNode) {

			root = leftOfPivot;
		} else if (pivotRBNode == pivotRBNode.getParent().getLeft()) {

			pivotRBNode.getParent().setLeft(leftOfPivot);
		} else {

			pivotRBNode.getParent().setRight(leftOfPivot);
		}

		leftOfPivot.setRight(pivotRBNode);

		pivotRBNode.setParent(leftOfPivot);
		
		leftOfPivot.setSize(pivotRBNode.getSize());
		
		pivotRBNode.setSize(pivotRBNode.getLeft().getSize() + pivotRBNode.getRight().getSize() + 1);

		return leftOfPivot;

	}

	private RBNode leftRotate(RBNode pivotRBNode) {

		RBNode rightOfPivot = pivotRBNode.getRight();

		pivotRBNode.setRight(rightOfPivot.getLeft());

		if (rightOfPivot.getLeft() != RBNode.nilNode) {

			rightOfPivot.getLeft().setParent(pivotRBNode);
		}

		rightOfPivot.setParent(pivotRBNode.getParent());

		if (pivotRBNode.getParent() == RBNode.nilNode) {

			root = rightOfPivot;
		} else if (pivotRBNode == pivotRBNode.getParent().getLeft()) {

			pivotRBNode.getParent().setLeft(rightOfPivot);
		} else {

			pivotRBNode.getParent().setRight(rightOfPivot);
		}

		rightOfPivot.setLeft(pivotRBNode);

		pivotRBNode.setParent(rightOfPivot);
		
		rightOfPivot.setSize(pivotRBNode.getSize());
		
		pivotRBNode.setSize(pivotRBNode.getLeft().getSize() + pivotRBNode.getRight().getSize() + 1);

		return rightOfPivot;
	}

	public void inorder(RBNode node) {

		if (node != RBNode.nilNode) {

			inorder(node.getLeft());

			System.out.print(node.getValue() + " ");

			inorder(node.getRight());
		}
	}

	public void RBTransplant(RBNode node1, RBNode node2) {

		if (node1.getParent() == RBNode.nilNode) {

			root = node2;
		} else if (node1 == node1.getParent().getLeft()) {

			node1.getParent().setLeft(node2);
		} else {
			node1.getParent().setRight(node2);
		}
		node2.setParent(node1.getParent());
		
		if (node2 != RBNode.nilNode)
			node2.setSize(node1.getSize());
	}

	public void delete(int num) {

		RBNode deleteNode = searchRecord(root, num);

		RBNode temp = deleteNode;
		
		Color tempColor = temp.getColor();
		
		// x denotes double balck node
		RBNode x = RBNode.nilNode;
		
		RBNode replaceNode = x;

		if (deleteNode.getLeft() == RBNode.nilNode) {

			x = deleteNode.getRight();
			
			if (x == RBNode.nilNode)
				x.setParent(deleteNode);
			
			updateSize(x);

			RBTransplant(deleteNode, deleteNode.getRight());
		} else if (deleteNode.getRight() == RBNode.nilNode) {

			x = deleteNode.getLeft();
			
			if (x == RBNode.nilNode)
				x.setParent(deleteNode);
			
			updateSize(x);

			RBTransplant(deleteNode, deleteNode.getLeft());
		} else {

			temp = getMinimum(deleteNode.getRight());
			
			updateSize(temp);

			tempColor = temp.getColor();

			x = temp.getRight();
			
			/**
			 *  if x is the sentinel node (nilNode) then its parent is the last node to be inserted
			 *  as we use a static common nilNode.
			 *  
			 *  To set its correct parent for any node, below if condition is used.
			 */
			if (temp.getParent() == deleteNode) {

				x.setParent(temp);
			} else {

				RBTransplant(temp, temp.getRight());

				temp.setRight(deleteNode.getRight());

				temp.getRight().setParent(temp);
			}
			RBTransplant(deleteNode, temp);

			temp.setLeft(deleteNode.getLeft());

			temp.getLeft().setParent(temp);

			temp.setColor(deleteNode.getColor());
		}
		
		if (tempColor == Color.BLACK) {

			RBDeleteFixup(x);
		}
	}

	private void updateSize(RBNode node) {
			
		while (node != root) {
			
			node.getParent().decrementSize();
			
			node = node.getParent();
		}
		
	}

	private void RBDeleteFixup(RBNode x) {

		while (x != root && x.getColor() == Color.BLACK) {

			if (x == x.getParent().getLeft()) {

				RBNode w = x.getParent().getRight();

				if (w.getColor() == Color.RED) {

					w.setColor(Color.BLACK);

					x.getParent().setColor(Color.RED);

					leftRotate(x.getParent());

					w = x.getParent().getRight();
				}

				if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {

					w.setColor(Color.RED);

					x = x.getParent();
				} else {

					if (w.getRight().getColor() == Color.BLACK) {

						w.getLeft().setColor(Color.BLACK);

						w.setColor(Color.RED);

						rightRotate(w);

						w = x.getParent().getRight();
					}

					w.setColor(x.getParent().getColor());

					x.getParent().setColor(Color.BLACK);

					w.getRight().setColor(Color.BLACK);

					leftRotate(x.getParent());

					x = root;

				}

			} else {

				RBNode w = x.getParent().getLeft();

				if (w.getColor() == Color.RED) {

					w.setColor(Color.BLACK);

					x.getParent().setColor(Color.RED);

					rightRotate(x.getParent());

					w = x.getParent().getLeft();
				}

				if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {

					w.setColor(Color.RED);

					x = x.getParent();
				} else {

					if (w.getLeft().getColor() == Color.BLACK) {

						w.getRight().setColor(Color.BLACK);

						w.setColor(Color.RED);

						leftRotate(w);

						w = x.getParent().getLeft();
					}

					w.setColor(x.getParent().getColor());

					x.getParent().setColor(Color.BLACK);

					w.getLeft().setColor(Color.BLACK);

					rightRotate(x.getParent());

					x = root;

				}

			}
		}

		x.setColor(Color.BLACK);
	}
	
	public int getElementAtRank(RBNode node, int rank) {
		
		int r = node.getLeft().getSize() + 1;
		
		if (rank == r) {
			return node.getValue();
		}
		else if (rank < r) {
			
			return getElementAtRank(node.getLeft(), rank);
		}
		else {
			
			return getElementAtRank(node.getRight(), rank - r);
		}
	}
	
	public int getRankofElement(int num) {
		
		RBNode node = searchRecord(root, num);
		
		int r = node.getLeft().getSize() + 1;
		
		RBNode temp = node;
		
		while (temp != root) {
			
			if (temp == temp.getParent().getRight()) {
				
				r = r + temp.getParent().getLeft().getSize() + 1;
				
			}
			
			temp = temp.getParent();
		}
		
		return r;
	}
}

enum Color {
	RED, BLACK;
}

class RBNode {

	private int value;

	private RBNode left;

	private RBNode right;

	private RBNode parent;

	private Color Color;
	
	private int size;
	
	public static RBNode nilNode = new RBNode(); 

	public RBNode(int value) {

		this.value = value;

		this.left = this.right = this.parent = nilNode;
		
		this.size = 1;
		
		nilNode.parent = this;

		this.Color = Color.RED;
	}
	
	public RBNode() {
		
		this.left = this.right = null;
		
		this.parent = null;
		
		this.Color = Color.BLACK;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RBNode getLeft() {
		return left;
	}

	public void setLeft(RBNode left) {
		this.left = left;
	}

	public RBNode getRight() {
		return right;
	}

	public void setRight(RBNode right) {
		this.right = right;
	}

	public RBNode getParent() {
		return parent;
	}

	public void setParent(RBNode parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return Color;
	}

	public void setColor(Color Color) {
		this.Color = Color;
	}
	
	public void incrementSize() {
		
		this.size++;
	}
	
	public void decrementSize() {
		
		this.size--;
	}
	
	public void setSize(int size) {
		
		this.size = size;
	}
	
	public int getSize() {
		
		return this.size;
	}
}
