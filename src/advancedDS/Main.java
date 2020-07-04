package advancedDS;

public class Main {

	public static void main(String[] args) {
		
		//bTreeTest();
		
		//binomialHeapTest();
		
		fibonacciHeapTest();
		
		//disjointSetTest();
	}
	
	private static void disjointSetTest() {
		
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		
		DisjointSet ds = new DisjointSet();
		
		for (int i = 0; i < arr.length; i++) {
			
			ds.makeSet(arr[i]);
		}
		
		ds.union(1, 2);
		
		ds.union(2, 3);
		
		ds.union(4, 5);
		
		ds.union(6, 7);
		
		ds.union(5, 6);
		
		ds.union(3, 7);
		
		ds.findSet(1);
		
		ds.findSet(2);
		
		ds.findSet(3);
		
		ds.findSet(4);
		
		ds.findSet(5);
	}

	public static void fibonacciHeapTest() {
		
		int[] input = { 10, 3, 7, 1, 11, 13, 14, 15, 18 };
		
		FibonacciHeap fh = new FibonacciHeap();
		
		for (int i = 0; i < input.length; i++) {
			
			fh.insert(input[i]);
		}
		
		FibNode min = fh.extractMin();
		
		System.out.println(min.key);
		
		fh.decreaseKey(14, 12);
		
		fh.decreaseKey(19, 9);
		
		fh.deleteKey(11);
		
	}
	
	public static void binomialHeapTest() {
		
		BinomialHeap bh = new BinomialHeap();
		
		for (int i = 1; i <= 13; i++) {
			
			bh.insert(i);
		}
		
		BinomialHeapNode node = bh.getMinimum();
		
		System.out.println(node.key);
		
		bh.extractMinimun();
		
		System.out.println(node.key);
		
		bh.decreaseKey(6, -2);
		
		bh.delete(11);
	}
	
	public static void bTreeTest() {
	
		int[] input = { 1, 3, 7, 10, 11, 13, 14, 15, 18, 16, 19, 24, 25, 26, 21, 4, 5, 20, 22, 2, 17, 12, 6};
		
		int order = 3;
		
		BTree btree = new BTree(order);
		
		for (int i = 0; i < input.length; i++) {
			
			btree.insert(input[i]);
		}
		
		btree.inorderTraversal(btree.getRoot());
		
		btree.delete(6);
		System.out.println("\ndelete 6");
		btree.inorderTraversal(btree.getRoot());
		
		btree.delete(13);
		System.out.println("\ndelete 13");
		btree.inorderTraversal(btree.getRoot());
		
		btree.delete(7);
		System.out.println("\ndelete 7");
		btree.inorderTraversal(btree.getRoot());
		
		btree.delete(4);
		System.out.println("\ndelete 4");
		btree.inorderTraversal(btree.getRoot());
		
		btree.delete(2);
		System.out.println("\ndelete 2");
		btree.inorderTraversal(btree.getRoot());

		btree.delete(16);
		System.out.println("\ndelete 16");
		btree.inorderTraversal(btree.getRoot());
	
	}

}
