package advancedDS;

public class BTree {

	// order of tree t >= 2
	private int order;
	// root of B Tree
	private BTreeNode root = null;

	public BTree(int order) {

		this.order = order;

		root = new BTreeNode(order);
	}
	
	public BTreeNode getRoot() {
		
		return root;
	}

	/**
	 * insert key into B - Tree
	 * 
	 * @param key
	 */
	public void insert(int key) {

		BTreeNode r = root;

		if (r.getKeyCount() == 2 * order - 1) {

			BTreeNode s = new BTreeNode(order);

			root = s;

			s.setLeaf(false);

			s.setChild(0, r);

			r.setParent(s);

			splitChild(s, 0);

			nonFullInsert(s, key);

		} else {

			nonFullInsert(r, key);
		}
	}

	private void nonFullInsert(BTreeNode x, int key) {

		int i = x.getKeyCount();

		if (x.isLeaf()) {

			// Shift elements place the key at appropriate position
			while (i >= 1 && key < x.getKey(i - 1)) {

				x.setKey(i, x.getKey(i - 1));

				i--;
			}

			x.setKey(i, key);

			x.incrementKeyCount();
		} else {

			while (i >= 1 && key < x.getKey(i - 1)) {

				i--;
			}

			if (x.getChild(i).getKeyCount() == 2 * order - 1) {

				splitChild(x, i);

				if (key > x.getKey(i)) {

					i++;
				}
			}

			nonFullInsert(x.getChild(i), key);
		}
	}

	private void splitChild(BTreeNode x, int i) {

		BTreeNode z = new BTreeNode(order);

		BTreeNode y = x.getChild(i);

		z.setLeaf(y.isLeaf());

		z.setKeyCount(order - 1);

		for (int j = 0; j < order - 1; j++) {

			z.setKey(j, y.getKey(j + order));

			y.setKey(j + order, 0);
		}

		if (!y.isLeaf()) {

			for (int j = 0; j < order; j++) {

				z.setChild(j, y.getChild(j + order));

				y.getChild(j + order).setParent(z);

				y.setChild(j + order, null);
			}
		}

		y.setKeyCount(order - 1);

		// shift the children of x
		for (int j = x.getKeyCount(); j > i; j--) {

			x.setChild(j + 1, x.getChild(j));
		}

		x.setChild(i + 1, z);

		z.setParent(x);

		// Shift the keys of x
		for (int j = x.getKeyCount(); j > i; j--) {

			x.setKey(j, x.getKey(j - 1));
		}

		x.setKey(i, y.getKey(order - 1));

		y.setKey(order - 1, 0);

		x.incrementKeyCount();
	}

	public NamedNode search(BTreeNode node, int key) {

		int i = 0;

		while (i < node.getKeyCount() && key > node.getKey(i)) {

			i++;
		}

		if (i < node.getKeyCount() && key == node.getKey(i)) {

			NamedNode namedNode = new NamedNode(node, i);

			return namedNode;
		} else if (node.isLeaf()) {

			return null;
		} else {

			return search(node.getChild(i), key);
		}
	}

	private int searchKeyInNode(BTreeNode node, int key) {
		
		int idx = 0;
		
		while (idx < node.getKeyCount() && key > node.getKey(idx)) {
			
			idx++;
		}
		
		return idx;
	}
	/**
	 * INCOMPLETE PROCEDURE (TO DO LATER)
	 * 
	 * @param key
	 */
	private void delete(BTreeNode node, int key) {
		
		int idx = searchKeyInNode(node, key);
		
		// key is present in the node passed as parameter
		if (idx < node.getKeyCount() && node.getKey(idx) == key) {
			
			if (node.isLeaf()) {
				
				removeFromLeafNode(node, idx);
			}
			else {
				
				removeFromInternalNode(node, idx);
			}
		}
		else {
			
			if (node.isLeaf()) {
				
				System.out.println("The key to be deleted is not present in the tree");
				
				return;
			}
			
			if (node.getChild(idx).getKeyCount() < order) {
				
				resolveMinimumOrderIssue(node.getChild(idx), idx);
			}
			
			if (idx > node.getKeyCount()) {
				
				delete(node.getChild(idx - 1),key);
			}
			else {
				
				delete(node.getChild(idx), key);
			}
		}
	}
	
	private void resolveMinimumOrderIssue(BTreeNode node, int idx) {
		
		BTreeNode leftSibling = getLeftSibling(node, idx);
		
		BTreeNode rightSibling = getRightSibling(node, idx);
		
		if (leftSibling != null && leftSibling.getKeyCount() > order - 1) {
			
			borrowFromLeftSibling(node, leftSibling, idx);
		}
		else if (rightSibling != null && rightSibling.getKeyCount() > order - 1) {
			
			borrowFromRightSibling(node, rightSibling, idx);
		}
		else {
			
			if (rightSibling != null) {
				
				merge(node, rightSibling, idx);
			}
			else {
				
				merge(leftSibling, node, idx - 1);
			}
		}
		
	}
	/**
	 * Will always merge from right node in left node
	 * @param left 
	 * @param right
	 * @param idx - index at which parent key is stored
	 */
	private void merge(BTreeNode left, BTreeNode right, int idx) {
		
		left.setKey(order - 1, left.getParent().getKey(idx));
		
		left.incrementKeyCount();
		
		for (int i = 0; i < right.getKeyCount(); i++) {
			
			left.setKey(i + order, right.getKey(i));
			
			left.incrementKeyCount();
		}
		
		if (!left.isLeaf()) {
			
			for (int i = 0; i <= right.getKeyCount(); i++) {
				
				left.setChild(i + order, right.getChild(i));
			}
		}
		
		// shift the keys in parent node
		for (int i = idx; i < left.getParent().getKeyCount() - 1; i++) {
			
			left.getParent().setKey(i, left.getParent().getKey(i + 1));
		}
		
		left.getParent().setKey(left.getParent().getKeyCount() - 1, 0);
		
		//shift the children in parent node
		for (int i = idx + 1; i < left.getParent().getKeyCount(); i++) {
			
			left.getParent().setChild(i, left.getParent().getChild(i + 1));
		}
		
		left.getParent().setChild(left.getParent().getKeyCount(), null);
		
		left.getParent().decrementKeyCount();
		
		if (left.getParent().getKeyCount() == 0) {
			
			root = left;
		}
		
	}

	private void borrowFromRightSibling(BTreeNode node, BTreeNode rightSibling, int idx) {
		
		node.setKey(node.getKeyCount(), node.getParent().getKey(idx));
		
		//shift each child of the right sibling one step back
		if (!node.isLeaf()) {
			
			node.setChild(node.getKeyCount() + 1, rightSibling.getChild(0));
			
			for (int i = 0; i < rightSibling.getKeyCount(); i++) {
				
				rightSibling.setChild(i, rightSibling.getChild(i + 1));
			}
			
			rightSibling.setChild(node.getKeyCount(), null);
		}
	
		node.getParent().setKey(idx, rightSibling.getKey(0));
		
		//shift each element in right sibling one step back
		
		for (int i = 0; i < rightSibling.getKeyCount() - 1; i++) {
			
			rightSibling.setKey(i, rightSibling.getKey(i + 1));
		}
		
		rightSibling.setKey(rightSibling.getKeyCount() - 1, 0);
		
		rightSibling.decrementKeyCount();
		
		node.incrementKeyCount();
	
	}

	private void borrowFromLeftSibling(BTreeNode node, BTreeNode leftSibling, int idx) {
		
		//shift each key in node one step ahead, to make place for borrowed element.
		for (int i = node.getKeyCount(); i > 0; i--) {
			
			node.setKey(i, node.getKey(i - 1));
		}
		
		node.setKey(0, node.getParent().getKey(idx - 1));
		
		//shift each child of the node one step ahead, to make place for child (coming from borrowed key)
		if (!node.isLeaf()) {
			
			for (int i = node.getKeyCount() + 1; i > 0; i--) {

				node.setChild(i, node.getChild(i - 1));
			}
			
			node.setChild(0, leftSibling.getChild(leftSibling.getKeyCount()));
		}
	
		node.getParent().setKey(idx - 1, leftSibling.getKey(leftSibling.getKeyCount() - 1));
		
		leftSibling.setKey(leftSibling.getKeyCount() - 1, 0);
		
		leftSibling.setChild(leftSibling.getKeyCount(), null);
		
		leftSibling.decrementKeyCount();
		
		node.incrementKeyCount();
	}

	private BTreeNode getRightSibling(BTreeNode node, int idx) {
		
		if (idx == node.getKeyCount())
			return null;
		
		return node.getParent().getChild(idx + 1);
	}

	private BTreeNode getLeftSibling(BTreeNode node, int idx) {
		
		if (idx == 0)
			return null;
		
		return node.getParent().getChild(idx - 1);
	}

	private void removeFromInternalNode(BTreeNode node, int idx) {
		
		if (node.getChild(idx).getKeyCount() > order - 1) {
			
			int p = getPredecessor(node.getChild(idx));
			
			node.setKey(idx, p);
			
			delete(node.getChild(idx), p);
		}
		else if (node.getChild(idx + 1).getKeyCount() > order - 1) {
			
			int s = getSuccessor(node.getChild(idx + 1));
			
			node.setKey(idx, s);
			
			delete(node.getChild(idx + 1), s);
		}
		else {
			
			merge(node.getChild(idx), node.getChild(idx + 1), idx);
			
			delete(node.getChild(idx), node.getChild(idx).getKey(order - 1));
		}
		
	}

	private void removeFromLeafNode(BTreeNode node, int idx) {
		
		for (int i = idx; i < node.getKeyCount() - 1; i++) {
			
			node.setKey(i, node.getKey(i + 1));
		}
		
		node.setKey(node.getKeyCount() - 1, 0);
		
		node.decrementKeyCount();
	}

	public void delete(int key) {
		
		delete(root, key);
	}

	private int getPredecessor(BTreeNode node) {

		while (!node.isLeaf()) {

			node = node.getChild(node.getKeyCount());
		}

		return node.getKey(node.getKeyCount() - 1);
	}

	public int getPredecessor(int key) {

		NamedNode namedNode = search(root, key);

		int i = namedNode.getIndex();

		BTreeNode node = namedNode.getNode().getChild(i);

		return getPredecessor(node);
	}

	private int getSuccessor(BTreeNode node) {

		while (!node.isLeaf()) {

			node = node.getChild(0);
		}

		return node.getKey(0);
	}

	public int getSuccessor(int key) {

		NamedNode namedNode = search(root, key);

		int i = namedNode.getIndex();

		BTreeNode node = namedNode.getNode().getChild(i + 1);

		return getSuccessor(node);
	}
	
	public void inorderTraversal(BTreeNode node) {
		
		int i;
		
		for (i = 0; i < node.getKeyCount(); i++) {
			
			if (!node.isLeaf())
				inorderTraversal(node.getChild(i));
			
			System.out.print(node.getKey(i) + " ");
				
		}
		
		if (!node.isLeaf())
			inorderTraversal(node.getChild(i));
			
	}
}

/**
 * Class representing node of B - Tree
 * 
 * @author pranjal
 *
 */
class BTreeNode {

	/**
	 * count of keys in a node Non root node = t - 1 <= keys <= 2t - 1 root node = 1
	 * <= keys <= 2t - 1
	 */

	private int keyCount = -1;

	// array to store keys
	private int[] keys = null;

	private boolean leaf = false;

	private BTreeNode parent;

	/**
	 * Array to store child nodes
	 * 
	 * Root node = 2 <= children <= 2t Non root node = t <= children <= 2t leaf node
	 * = 0
	 */
	private BTreeNode[] children = null;

	public BTreeNode(int order) {

		keys = new int[2 * order - 1];

		children = new BTreeNode[2 * order];

		leaf = true;

		keyCount = 0;

		parent = null;
	}

	public int getKey(int index) {

		return keys[index];
	}

	public BTreeNode getChild(int index) {

		return children[index];
	}

	public int getKeyCount() {
		return keyCount;
	}

	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public void setChild(int index, BTreeNode node) {

		children[index] = node;
	}

	public void setKey(int index, int key) {

		keys[index] = key;
	}

	public void incrementKeyCount() {

		keyCount++;
	}

	public void decrementKeyCount() {

		keyCount--;
	}

	public BTreeNode getParent() {
		return parent;
	}

	public void setParent(BTreeNode parent) {
		this.parent = parent;
	}
}

class NamedNode {

	private BTreeNode node;

	private int index;

	public NamedNode(BTreeNode node, int index) {

		this.node = node;

		this.index = index;
	}

	public BTreeNode getNode() {
		return node;
	}

	public void setNode(BTreeNode node) {
		this.node = node;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
