package advancedDS;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	private TrieNode root = null;
	
	public Trie() {
		
		root = new TrieNode();
	}
	
	class TrieNode {
		
		Map<Character, TrieNode> map = null;
		
		boolean end;
		
		public TrieNode() {
			
			map = new HashMap<Character, Trie.TrieNode>();
			
			end = false;
		}
	}
	
	public void insert(String str) {
		
		TrieNode curr = this.root;
		
		for (int i =0; i < str.length(); i++) {
			
			char ch = str.charAt(i);
			
			TrieNode nxt = curr.map.get(ch);
			
			if (nxt == null) {
				
				nxt = new TrieNode();
				
				curr.map.put(ch, nxt);
			}
			
			curr = nxt;
		}
		
		curr.end = true;
	}
	
	public boolean search(String str) {
		
		TrieNode curr = this.root;
		
		for (int i = 0; i < str.length(); i++) {
			
			char ch = str.charAt(i);
			
			TrieNode node = curr.map.get(ch);
			
			if (node == null) {
				
				return false;
			}
			else {
				
				curr = node;
			}
		}
		
		return curr.end;
	}
	
	public boolean delete(String str) {
		
		return delete(this.root, str, 0);
	}
	
	private boolean delete (TrieNode node, String str, int idx) {
		
		if (idx == str.length())
		{
			if (!node.end) {
				
				return false;
			}
			
			node.end = false;
			
			return node.map.size() == 0;
				
		}
		
		char ch = str.charAt(idx);
		
		TrieNode nxt = node.map.get(ch);
		
		if (nxt == null) {
			
			return false;
		}
		
		boolean toDelNode = delete(nxt, str, idx + 1);
		
		if (toDelNode) {
			
			node.map.remove(ch);
			
			return node.map.size() == 0;
		}
		
		return false;
	}
}
