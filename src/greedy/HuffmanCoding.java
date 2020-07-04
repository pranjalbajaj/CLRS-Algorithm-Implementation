package greedy;

import java.util.Comparator;

import miscellaneous.MinPriorityQueue;

public class HuffmanCoding {

	public static void main(String[] args) {
		
		int n = 6;
		
        char[] charArray = {'c', 'a', 'd', 'e', 'f', 'b' };
        
        int[] charfreq = { 12, 5, 13, 16, 45, 9 } ;
		
		MinPriorityQueue<HuffmanNode> pq = new MinPriorityQueue<HuffmanNode>(n, new HuffmanNodeComparator());
		
		for (int i = 0; i < n; i++) { 
			  
            // creating a Huffman node object 
            // and add it to the priority queue. 
            HuffmanNode hn = new HuffmanNode(charArray[i], charfreq[i]); 
  
            pq.insert(hn); 
        }
		
		createHuffmanTree(pq);
		
		printCodes(pq.peek(), " ");
		
	}

	private static void printCodes(HuffmanNode root, String code) {
		
		if (root.getLeft() == null && root.getRight() == null) {
			
			System.out.println(root.getC() + ": " + code);
			
			return;
		}
		
		printCodes(root.getLeft(), code + "0");
		
		printCodes(root.getRight(), code + "1");
		
	}

	private static void createHuffmanTree(MinPriorityQueue<HuffmanNode> pq) {
		
		while (pq.getSize() > 1) {
			
			HuffmanNode x = pq.poll();
			
			HuffmanNode y = pq.poll();
			
			HuffmanNode combinationNode = new HuffmanNode('-', x.getCount() + y.getCount());
			
			combinationNode.setLeft(x);
			
			combinationNode.setRight(y);
			
			pq.insert(combinationNode);
			
		}
		
	}

}

class HuffmanNode {
	
	private char c;
	
	private Integer count;
	
	private HuffmanNode left = null;
	
	private HuffmanNode right = null;
	
	public HuffmanNode(char c, int count) {
		
		this.c = c;
		
		this.count = count;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public HuffmanNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanNode left) {
		this.left = left;
	}

	public HuffmanNode getRight() {
		return right;
	}

	public void setRight(HuffmanNode right) {
		this.right = right;
	}

}

class HuffmanNodeComparator implements Comparator<HuffmanNode> {

	@Override
	public int compare(HuffmanNode o1, HuffmanNode o2) {
		
		return (o1.getCount()).compareTo(o2.getCount());
	}
}
