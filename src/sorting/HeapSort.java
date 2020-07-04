package sorting;

/**
 * HEAP SORT:
 * 
 * Terminology: -> if a node is at index i 
 * 				   left child = 2*i right child = 2*i + 1
 * 				   parent = [i / 2]
 * 
 * Explanation: -> It's an in place sorting algorithm, consists of two step.
 * 					1. Build a max heap i.e convert the given array to create a max heap.
 * 					2. Starting from the last element, swap the last and first element,
 * 					   and call the maxHeapify procedure to get the sorted array.
 * 
 * 
 * Complexity: -> O(n*log(n))
 * 
 * 
 * @author pranjal
 *
 */

public class HeapSort extends Sort {

	public void buildMaxHeap(int[] arr, int heapSize) {

		for (int i = ((heapSize / 2) - 1); i >= 0; i--)
			maxHeapify(arr, i, heapSize);
	}

	public void buildMinHeap(int[] arr, int heapSize) {

		for (int i = ((heapSize / 2) - 1); i >= 0; i--)
			minHeapify(arr, i, heapSize);
	}

	public void minHeapify(int[] arr, int rootIdx, int heapSize) {

		int smallest = rootIdx;

		int leftIdx = 2 * rootIdx + 1;

		int rightIdx = 2 * rootIdx + 2;

		if (leftIdx < heapSize && arr[leftIdx] < arr[smallest]) {

			smallest = leftIdx;

		}

		if (rightIdx < heapSize && arr[rightIdx] < arr[smallest]) {

			smallest = rightIdx;
		}

		if (smallest != rootIdx) {

			int temp = arr[smallest];

			arr[smallest] = arr[rootIdx];

			arr[rootIdx] = temp;

			minHeapify(arr, smallest, heapSize);

		}
	}

	public void maxHeapify(int[] arr, int rootIdx, int heapSize) {

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

	public void sort(int[] arr, int heapSize) {

		buildMinHeap(arr, heapSize);

		for (int i = heapSize - 1; i > 0; i--) {

			int temp = arr[0];

			arr[0] = arr[i];

			arr[i] = temp;

			minHeapify(arr, 0, i);

		}
	}

	@Override
	public void sortAscending(int[] inputArr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortDescending(int[] inputArr) {
		// TODO Auto-generated method stub

	}

}
