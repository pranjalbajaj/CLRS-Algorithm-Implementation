package advancedDS.ObjOrientedFibHeap;

public class GenericFibNode<T> {
	
	T obj;
	
	// sort key
	int key;
	
	int degree;
	
	boolean mark;
	
	GenericFibNode<T> parent;
	
	GenericFibNode<T> child;
	
	GenericFibNode<T> left;
	
	GenericFibNode<T> right;
	
	public GenericFibNode(T obj, int key) {
		
		this.obj = obj;
		
		this.key = key;
	}

	public T getObj() {
		return obj;
	}

	public int getKey() {
		
		return key;
	}
}