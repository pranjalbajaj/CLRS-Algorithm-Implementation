package miscellaneous;

/**
 * HEAP AS PRIORITY QUEUE:
 * 
 * Terminology: -> heapSize = heap size of an array (may or may not be equal to array length)
 * 				   peek = fetch root element of queue
 * 				   poll = retrieve and delete the first element of queue
 * 
 * Explanation: It's an application of heap data structure. Following operations can be performed.
 * 				1. Inserting an element in queue (at last position (queue data structure))
 * 				2. Retrieve the first/root element which obviously will have the highest 
 * 				   priority.
 * 				3. Retrieve as well as delete the root element
 * 				4. Increase the priority of any element with the heap.
 * 
 * NOTE: The numbers in the array are their own priority.
 *  
 * Complexity: Any operation in priority queue can be performed in O(log n) time
 * 
 * 
 * @author pranjal
 *
 */

import java.util.Arrays;

public class HeapAsPriorityQueue {

	static int heapSize = -1;
	
	public static void main(String[] args) {
		
		int[] arr = { 38, 27, 43, 3, 9, 82, 10, -1};
		
		heapSize = arr.length - 1;
		
		buildMaxHeap(arr, heapSize);
		
		System.out.println(Arrays.toString(arr));
		
		// 1. Insert operation
		insert(arr, 78);
		
		//2. peek operation
		int peeked = peek(arr);
		
		System.out.println(peeked);
		
		// 3. poll operation
		int polled = poll(arr);
		
		System.out.println(polled);
		
		System.out.println(Arrays.toString(arr));
		
		// 4. Increase priority operation
		increasePriorityOfElement(arr, 3, 67);
		
		System.out.println(Arrays.toString(arr));

	}

	private static int poll(int[] arr) {
		
		int maxElement = peek(arr);
		
		int temp = arr[0];
		
		arr[0] = arr[heapSize - 1];
		
		arr[heapSize - 1] = temp;
		
		heapSize--;
		
		maxHeapify(arr, 0, heapSize);
		
		return maxElement;
	}

	private static int peek(int[] arr) {
		
		return arr[0];
		
	}

	private static void insert(int[] arr, int element) {
		
		heapSize++;
		
		increasePriorityOfElement(arr, heapSize - 1, element);
		
		System.out.println(Arrays.toString(arr));
		
	}
	
	private static void increasePriorityOfElement(int[] arr, int index, int newPriority) {
		
		int parent = (index - 1) / 2;
		
		while (index > 0 && arr[parent] < newPriority) {
			
			arr[index] = arr[parent];
			
			index = parent;
			
			parent = index / 2;
		}
		
		arr[index]= newPriority;
	}
	
	public static void maxHeapify(int[] arr, int rootIdx, int heapSize) {

		int largest = rootIdx;

		int leftIdx = 2 * rootIdx + 1;

		int rightIdx = 2 * rootIdx + 2;

		if (leftIdx < heapSize && arr[leftIdx] > arr[largest]) {

			largest = leftIdx;

		}

		if (rightIdx < heapSize && arr[rightIdx] > arr[largest]) {

			largest = rightIdx;
		}

		if (largest != rootIdx) {

			int temp = arr[largest];

			arr[largest] = arr[rootIdx];

			arr[rootIdx] = temp;

			maxHeapify(arr, largest, heapSize);

		}
	}
	
	public static void buildMaxHeap(int[] arr, int heapSize) {

		for (int i = ((heapSize / 2) - 1); i >= 0; i--)
			maxHeapify(arr, i, heapSize);
	}

}
