/**
 * AVL TREE:
 * 
 * Terminology: -> Node consists of : key, left child, right child, parent and height
 * 				   Balance Factor of a Node= ((Left Height of Node) - (Right height of Node)) = {-1, 0 ,1}
 * 					
 * 
 * Explanation: Most of the BST operations (e.g., search, max, min, insert, delete.. etc) take O(h) time 
 * 			 	where h is the height of the BST. The cost of these operations may become O(n) for a 
 * 			 	skewed Binary tree. If we make sure that height of the tree remains O(Log(n)) after every 
 * 			 	insertion and deletion, then we can guarantee an upper bound of O(Log(n)) for all these operations. 
 * 			 	The height of an AVL tree is always O(Log(n)) where n is the number of nodes in the tree.
 * 
 * 				1. It is a self balancing Binary Search Tree
 * 				2. Rotations happen only on 3 nodes.
 *             
 * Complexity: Insertion: O(log(n)) = O(height of tree)
 * 			   Deletion: O(log(n)) = O(height of tree)						   
 * 							 	
 * @author pranjal
 *
 */

package tree;

public class AVLTrees {

	private static AVLNode root = null;

	public static void main(String[] args) {

		AVLTrees avlTrees = new AVLTrees();

		int[] arr = { 9, 5, 10, 0, 6, 11, -1, 1, 2 };

		for (int i = 0; i < arr.length; i++) {

			root = avlTrees.insert(root, arr[i]);
		}

		avlTrees.inorder(root);
		
		root = avlTrees.delete(root, 10);
		
		System.out.println();
		
		avlTrees.inorder(root);

	}

	public AVLNode insert(AVLNode avlNode, int num) {

		// Step 1: Insert element in tree similar to insertion in BST
		if (avlNode == null) {

			avlNode = new AVLNode(num);

			return avlNode;
		}

		if (avlNode.getValue() > num) {

			avlNode.setLeft(insert(avlNode.getLeft(), num));

			avlNode.getLeft().setParent(avlNode);
		}

		else if (avlNode.getValue() < num) {

			avlNode.setRight(insert(avlNode.getRight(), num));

			avlNode.getRight().setParent(avlNode);
		}

		// Step 2 : Set the height of the parent node.
		avlNode.setHeight(max(getAVLNodeHeight(avlNode.getLeft()), getAVLNodeHeight(avlNode.getRight())));

		// Step 3 : Get the balance factor
		int balanceFactor = getBalanceFactor(avlNode);

		// Step 4 : Based on the value of balance factor, perform rotations to balance
		// the tree.
		if (balanceFactor > 1) {

			/**
			 * Two case are possible for balance factor greater than 1 
			 * -> Left Left 
			 * -> Left Right
			 */
			if (avlNode.getLeft().getValue() > num) {

				return rightRotate(avlNode);
			} else {

				avlNode.setLeft(leftRotate(avlNode.getLeft()));

				return rightRotate(avlNode);
			}

		} else if (balanceFactor < -1) {

			/**
			 * Two case are possible for balance factor lesser than -1 
			 * -> Right Right 
			 * -> Right Left
			 */
			if (avlNode.getRight().getValue() < num) {

				return leftRotate(avlNode);
			} else {

				avlNode.setRight(rightRotate(avlNode.getRight()));

				return leftRotate(avlNode);
			}

		}

		return avlNode;

	}

	public AVLNode delete(AVLNode avlNode, int num) {

		// Step 1 : Delete the node similar to BST
		if (avlNode == null)
			return null;

		if (avlNode.getValue() > num) {

			avlNode.setLeft(delete(avlNode.getLeft(), num));
		} else if (avlNode.getValue() < num) {

			avlNode.setRight(delete(avlNode.getRight(), num));
		} else {

			// deleting node with no child or one child
			if (avlNode.getLeft() == null)
				return avlNode.getRight();
			else if (avlNode.getRight() == null)
				return avlNode.getLeft();

			// deleting node with 2 children
			else {

				AVLNode replaceNode = getMinimum(avlNode.getRight());

				avlNode.setValue(replaceNode.getValue());

				delete(avlNode.getRight(), replaceNode.getValue());
			}
		}

		// Step 2 : Set the height of the parent node.
		avlNode.setHeight(max(getAVLNodeHeight(avlNode.getLeft()), getAVLNodeHeight(avlNode.getRight())));

		// Step 3 : Get the balance factor
		int balanceFactor = getBalanceFactor(avlNode);

		// Step 4 : Based on the value of balance factor, perform rotations to balance
		// the tree.
		if (balanceFactor > 1) {

			/**
			 * Two case are possible for balance factor greater than 1 
			 * -> Left Left 
			 * -> Left Right
			 */
			if (getBalanceFactor(avlNode.getLeft()) >= 0) {

				return rightRotate(avlNode);
			} else {

				avlNode.setLeft(leftRotate(avlNode.getLeft()));

				return rightRotate(avlNode);
			}

		} else if (balanceFactor < -1) {

			/**
			 * Two case are possible for balance factor lesser than -1 
			 * -> Right Right 
			 * -> Right Left
			 */
			if (getBalanceFactor(avlNode.getRight()) <= 0) {

				return leftRotate(avlNode);
			} else {

				avlNode.setRight(rightRotate(avlNode.getRight()));

				return leftRotate(avlNode);
			}

		}

		return avlNode;

	}

	public AVLNode getMinimum(AVLNode node) {

		if (node != null) {

			while (node.getLeft() != null) {

				node = node.getLeft();
			}
		}
		return node;
	}

	public AVLNode searchRecord(AVLNode node, int num) {

		if (node == null || node.getValue() == num) {

			return node;
		}

		if (node.getValue() > num) {

			return searchRecord(node.getLeft(), num);
		}

		return searchRecord(node.getRight(), num);
	}

	/*
	 * 
	 * @param pivotAVLNode
	 * 
	 * @return
	 */
	private AVLNode rightRotate(AVLNode pivotAVLNode) {

		AVLNode leftNodeOfPivot = pivotAVLNode.getLeft();

		AVLNode rightNodeOfLeftOfPivot = leftNodeOfPivot.getRight();

		leftNodeOfPivot.setRight(pivotAVLNode);

		pivotAVLNode.setLeft(rightNodeOfLeftOfPivot);

		leftNodeOfPivot.setParent(pivotAVLNode.getParent());

		pivotAVLNode.setParent(leftNodeOfPivot);

		if (rightNodeOfLeftOfPivot != null)
			rightNodeOfLeftOfPivot.setParent(pivotAVLNode);

		pivotAVLNode
				.setHeight(max(getAVLNodeHeight(pivotAVLNode.getLeft()), getAVLNodeHeight(pivotAVLNode.getRight())));

		leftNodeOfPivot.setHeight(
				max(getAVLNodeHeight(leftNodeOfPivot.getLeft()), getAVLNodeHeight(leftNodeOfPivot.getRight())));

		return leftNodeOfPivot;

	}

	private AVLNode leftRotate(AVLNode pivotAVLNode) {

		AVLNode rightNodeOfPivot = pivotAVLNode.getRight();

		AVLNode leftNodeOfRightOfPivot = rightNodeOfPivot.getLeft();

		rightNodeOfPivot.setLeft(pivotAVLNode);

		pivotAVLNode.setRight(leftNodeOfRightOfPivot);

		rightNodeOfPivot.setParent(pivotAVLNode.getParent());

		pivotAVLNode.setParent(rightNodeOfPivot);

		if (leftNodeOfRightOfPivot != null)
			leftNodeOfRightOfPivot.setParent(pivotAVLNode);

		pivotAVLNode
				.setHeight(max(getAVLNodeHeight(pivotAVLNode.getLeft()), getAVLNodeHeight(pivotAVLNode.getRight())));

		rightNodeOfPivot.setHeight(
				max(getAVLNodeHeight(rightNodeOfPivot.getLeft()), getAVLNodeHeight(rightNodeOfPivot.getRight())));

		return rightNodeOfPivot;

	}

	private int getBalanceFactor(AVLNode avlNode) {

		return (getAVLNodeHeight(avlNode.getLeft()) - getAVLNodeHeight(avlNode.getRight()));
	}

	private int getAVLNodeHeight(AVLNode avlNode) {

		if (avlNode == null)
			return 0;

		return 1 + avlNode.getHeight();
	}

	private int max(int a, int b) {

		return a > b ? a : b;
	}

	public void inorder(AVLNode node) {

		if (node != null) {

			inorder(node.getLeft());

			System.out.print(node.getValue() + " ");

			inorder(node.getRight());
		}
	}
}

class AVLNode {

	private int value;

	private AVLNode left;

	private AVLNode right;

	private AVLNode parent;

	private int height;

	public AVLNode(int value) {

		this.value = value;

		this.left = this.right = this.parent = null;

		this.height = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public AVLNode getLeft() {
		return left;
	}

	public void setLeft(AVLNode left) {
		this.left = left;
	}

	public AVLNode getRight() {
		return right;
	}

	public void setRight(AVLNode right) {
		this.right = right;
	}

	public AVLNode getParent() {
		return parent;
	}

	public void setParent(AVLNode parent) {
		this.parent = parent;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
