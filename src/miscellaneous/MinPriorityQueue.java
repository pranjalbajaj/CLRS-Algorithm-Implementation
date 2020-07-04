package miscellaneous;

import java.util.Comparator;

public class MinPriorityQueue<T> {

	private int heapSize = 0;

	private T[] arr = null;

	private Comparator<T> comparator = null;
	
	private int size = 0;

	@SuppressWarnings("unchecked")
	public MinPriorityQueue(int heapSize) {

		this.heapSize = heapSize;

		this.arr = (T[]) new Object[heapSize];
	}

	@SuppressWarnings("unchecked")
	public MinPriorityQueue(int heapSize, Comparator<T> comparator) {

		this.heapSize = heapSize;

		this.arr = (T[]) new Object[heapSize];

		this.comparator = comparator;
	}

	public int getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}

	public T[] getArr() {
		return arr;
	}

	public void setArr(T[] arr) {
		this.arr = arr;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void minHeapify(int rootIdx) {

		int smallest = rootIdx;

		int leftIdx = 2 * rootIdx + 1;

		int rightIdx = 2 * rootIdx + 2;

		if (leftIdx < size && this.comparator.compare(arr[leftIdx], arr[smallest]) == -1) {

			smallest = leftIdx;

		}

		if (rightIdx < size && this.comparator.compare(arr[rightIdx], arr[smallest]) == -1) {

			smallest = rightIdx;
		}

		if (smallest != rootIdx) {

			T temp = arr[smallest];

			arr[smallest] = arr[rootIdx];

			arr[rootIdx] = temp;

			minHeapify(smallest);

		}
	}

	public void insert(T element) {
		
		if (size >= heapSize)
			return;
		
		size++;
		
		int index = size - 1;

		int parent = (index - 1) / 2;

		while (index > 0 && this.comparator.compare(element, arr[parent]) == -1) {

			arr[index] = arr[parent];

			index = parent;

			parent = (index - 1)/ 2;
		}

		arr[index] = element;

	}
	
	public T poll() {
		
		T minElement = peek();
		
		T temp = arr[0];
		
		arr[0] = arr[size - 1];
		
		arr[size - 1] = temp;
		
		size--;
		
		minHeapify(0);
		
		return minElement;
	}

	public T peek() {
		
		return arr[0];
		
	}
}
