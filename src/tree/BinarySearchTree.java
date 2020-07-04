/**
 * BINARY SEARCH TREE: (BST)
 * 
 * Terminology: -> Node consists of : key, left child, right child.
 * 					
 * 
 * Explanation: 
 *             #Binary Search Tree Property : left sub tree < root < right sub tree (for all nodes)
 *             
 *             #Successor :  If all keys are distinct, the successor of a node x is 
 *             				 the node with the smallest key greater than x.key.
 *             
 * 			   #Operations Supported :
 * 
 * 				1. Insertion -> Insertion in search tree by maintaining the binary search tree property.
 * 				2. Traversal : -> Inorder = (left, root, right)
 * 							   -> Preorder = (root, left, right)
 * 							   -> Postorder = (left, right, root)
 * 				3. Successor : Inorder, preorder, postorder successor.
 * 				4. Deletion -> Deletion in search tree by maintaining the binary search tree property.
 * 							   
 * 
 * Complexity: Average case: Tree is almost balanced
 * 			   				Insertion -> O(log(n)) = O(height of binary search tree)
 *             				Traversal -> O(n)
 *             				Successor -> O(log(n)) = O(height of binary search tree)
 *             				Deletion -> O(log(n)) = O(height of binary search tree)
 * 			   Worst case: Tree is right or left skewed.
 * 							Insertion -> O(n)
 *             				Traversal -> O(n)
 *             				Successor -> O(n)
 *             				Deletion -> O(n)
 * 							 	
 * @author pranjal
 *
 */
package tree;

import java.util.Stack;

public class BinarySearchTree {

	private static Node root = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BinarySearchTree bst = new BinarySearchTree();

		int[] arr = { 15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9 };

		for (int i = 0; i < arr.length; i++) {

			bst.insert(arr[i]);
		}

		System.out.println("Inorder");
		bst.inorder(root);

		System.out.println("\nIterative Inorder");
		bst.iterativeInorder(root);

		System.out.println("\nPreorder");
		bst.preorder(root);

		System.out.println("\nPostorder");
		bst.postorder(root);

		Node node = bst.search(4);
		if (node != null) {
			System.out.println("\n" + node.getKey());
		} else {
			System.out.println("\n element not found");
		}

		node = bst.getMinimum(root);
		if (node != null)
			System.out.println("\nMinimum:" + node.getKey());
		else
			System.out.println("\nTree is empty");

		node = bst.getMinimumRecursive(root);
		if (node != null)
			System.out.println("\nMinimum:" + node.getKey());
		else
			System.out.println("\nTree is empty");

		node = bst.getMaximum(root);
		if (node != null)
			System.out.println("\nMaximum:" + node.getKey());
		else
			System.out.println("\nTree is empty");
		
		bst.delete(15);
		
		System.out.println("Inorder");
		bst.inorder(root);
		
		bst.delete2(root, 15);
		
		System.out.println("\nInorder");
		bst.inorder(root);

	}

	public void insert(int num) {

		root = insertRecord(root, num);

	}

	private Node insertRecord(Node node, int num) {

		if (node == null) {

			node = new Node(num);
			return node;
		}

		if (num < node.getKey()) {

			node.setLeft(insertRecord(node.getLeft(), num));

			node.getLeft().setParent(node);

		} else if (num > node.getKey()) {

			node.setRight(insertRecord(node.getRight(), num));

			node.getRight().setParent(node);
		}

		return node;
	}

	public Node search(int num) {

		// return searchRecord(root, num);

		return iterativeSearch(root, num);
	}

	public Node searchRecord(Node node, int num) {

		if (node == null || node.getKey() == num) {

			return node;
		}

		if (node.getKey() > num) {

			return searchRecord(node.getLeft(), num);
		}

		return searchRecord(node.getRight(), num);
	}

	public Node iterativeSearch(Node node, int num) {

		while (node != null && node.getKey() != num) {

			if (node.getKey() > num) {

				node = node.getLeft();
			} else {

				node = node.getRight();
			}

		}

		return node;
	}

	public Node getMinimum(Node node) {

		if (node != null) {

			while (node.getLeft() != null) {

				node = node.getLeft();
			}
		}
		return node;
	}

	public Node getMinimumRecursive(Node node) {

		if (node.getLeft() != null) {

			return getMinimumRecursive(node.getLeft());
		}

		return node;
	}

	public Node getMaximum(Node node) {

		if (node != null) {

			while (node.getRight() != null) {

				node = node.getRight();
			}
		}
		return node;
	}

	public Node getMaximumRecursive(Node node) {

		if (node.getRight() != null) {

			return getMaximumRecursive(node.getLeft());
		}

		return node;
	}

	public void inorder(Node node) {

		if (node != null) {

			inorder(node.getLeft());

			System.out.print(node.getKey() + " ");

			inorder(node.getRight());
		}
	}

	public void iterativeInorder(Node node) {

		Stack<Node> s = new Stack<Node>();

		while (node != null || s.size() > 0) {

			while (node != null) {

				s.push(node);

				node = node.getLeft();
			}

			node = s.pop();

			System.out.print(node.getKey() + " ");

			node = node.getRight();

		}
	}

	public void preorder(Node node) {

		if (node != null) {

			System.out.print(node.getKey() + " ");

			preorder(node.getLeft());

			preorder(node.getRight());
		}
	}

	public void postorder(Node node) {

		if (node != null) {

			postorder(node.getLeft());

			postorder(node.getRight());

			System.out.print(node.getKey() + " ");
		}
	}

	public Node inorderSuccessor(int num) {

		Node operationNode = search(num);

		if (operationNode.getRight() != null) {

			return getMinimum(operationNode.getRight());
		}

		Node parent = operationNode.getParent();

		while (parent != null && operationNode == parent.getRight()) {

			operationNode = parent;

			parent = parent.getParent();
		}

		return parent;
	}

	public Node inorderPredecessor(int num) {

		Node operationNode = search(num);

		if (operationNode.getLeft() != null) {

			return getMaximum(operationNode.getLeft());
		}

		Node parent = operationNode.getParent();

		while (parent != null && operationNode == parent.getLeft()) {

			operationNode = parent;

			parent = parent.getParent();
		}

		return parent;

	}

	public Node preorderSuccessor(int num) {

		Node operationNode = search(num);

		if (operationNode.getLeft() != null) {

			return operationNode.getLeft();
		}

		if (operationNode.getRight() != null) {

			return operationNode.getRight();
		}

		/**
		 * If left child does not exist and given node is left child of its parent, then
		 * its sibling is its preorder successor.
		 */

		/**
		 * if none of above conditions are satisfied (left child does not exist and
		 * given node is not left child of its parent), then we move up using parent
		 * pointers until one of the following happens. -> We reach root. In this case,
		 * preorder successor does not exist. -> Current node (one of the ancestors of
		 * given node) is left child of its parent, in this case preorder successor is
		 * sibling of current node.
		 */

		Node parent = operationNode.getParent();

		while (parent != null) {

			if (operationNode == parent.getLeft() && parent.getRight() != null) {

				return parent.getRight();
			}

			operationNode = parent;

			parent = parent.getParent();
		}

		return null;
	}

	public Node preorderPredecessor(int num) {

		Node operationNode = search(num);

		Node parent = operationNode.getParent();

		if (parent == null) {

			return null;
		}

		if (parent.getLeft() == null || operationNode == parent.getLeft()) {

			return parent;
		}

		parent = parent.getLeft();

		while (parent.getRight() != null) {

			parent = parent.getRight();
		}

		return parent;

	}

	public Node postorderSuccessor(int num) {

		Node operationNode = search(num);
		
		Node parent = operationNode.getParent();
		
		if (parent == null) {
			
			return null;
		}
		
		if (parent.getRight() == null || operationNode == parent.getRight()) {
			
			return parent;
		}
		
		if (parent.getRight() != null && operationNode == parent.getLeft()) {
			
			return getMinimum(parent.getRight());
		}
		
		return null;

	}
	
	public Node postorderPredecessor(int num) {
		
		Node operationNode = search(num);
		
		if (operationNode.getRight() != null) {
			
			return operationNode.getRight();
		}
		
		if (operationNode.getLeft() != null) {
			
			return operationNode.getLeft();
		}
		
		Node parent = operationNode.getParent();
		
		while (parent != null) {
			
			if (operationNode == parent.getLeft() || operationNode.getLeft() == null) {
				
				operationNode = parent;
				
				parent = parent.getParent();
			}
			else {
				
				return parent.getLeft();
			}
		}
		
		return parent;
		
	}
	
	public void delete(int num) {
		
		Node deleteNode = search(num);
		
		//case 1: Deleting leaf node
		if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
			
			if (deleteNode == deleteNode.getParent().getLeft()) {
				
				deleteNode.getParent().setLeft(null);
			}
			else if (deleteNode == deleteNode.getParent().getRight()) {
				
				deleteNode.getParent().setRight(null);
			}
		}
		
		//case 2: Deleting a node with one child
		
		else if (deleteNode.getLeft() == null) {
			
			if (deleteNode == deleteNode.getParent().getLeft()) {
				
				deleteNode.getParent().setLeft(deleteNode.getRight());
			}
			else {
				
				deleteNode.getParent().setRight(deleteNode.getRight());
			}
			
		}
		else if (deleteNode.getRight() == null) {
			
			if (deleteNode == deleteNode.getParent().getLeft()) {
				
				deleteNode.getParent().setLeft(deleteNode.getLeft());
			}
			else {
				
				deleteNode.getParent().setRight(deleteNode.getLeft());
			}
			
		}
		
		//case 3: Deleting a node with 2 children
		
		else {
			
			Node replaceNode = getMinimum(deleteNode.getRight());
			
			if (replaceNode == deleteNode.getRight()) {
				
				replaceNode.setLeft(deleteNode.getLeft());
				
				replaceNode.getLeft().setParent(replaceNode);
				
				deleteNode.setLeft(null);
				
				delete(num);
		
			}
			else {
				
				deleteNode.setKey(replaceNode.getKey());
				
				replaceNode.getParent().setLeft(replaceNode.getRight());
				
			}
			
		}
		
	}
	
	public Node delete2(Node node, int num) {
		
		if (node == null)
			return null;
		
		if (node.getKey() > num) {
			
			node.setLeft(delete2(node.getLeft(), num));
		}
		else if (node.getKey() < num) {
			
			node.setRight(delete2(node.getRight(), num));
		}
		else {
			
			// deleting node with no child or one child
			if (node.getLeft() == null)
				return node.getRight();
			else if (node.getRight() == null)
				return node.getLeft();
			
			// deleting node with 2 children
			else {
				
				Node replaceNode = getMinimum(node.getRight());
				
				node.setKey(replaceNode.getKey());
				
				delete2(node.getRight(), replaceNode.getKey());
			}
		}
		
		return node;
	}

}

class Node {

	private int key;

	private Node left;

	private Node right;

	private Node parent;

	public Node(int num) {

		this.key = num;

		this.left = this.right = null;

		this.parent = null;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

}
