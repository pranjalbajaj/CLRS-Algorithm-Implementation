package miscellaneous;

import java.util.Arrays;

public class HeapDataStructure {

	public static void main(String[] args) {
		
		int[] arr = { 38, 27, 43, 3, 9, 82, 10};
		
		int heapSize = arr.length;
		
		buildMaxHeap(arr, arr.length);
		
		// 1. delete any index from heap
		delete(arr, 2, heapSize);
		
		System.out.println(Arrays.toString(arr));

	}
	
	private static void delete(int[] arr, int i, int heapSize) {
		
		heapIncreaseValue(arr, i, Integer.MAX_VALUE);
	
		arr[0] = arr[heapSize - 1];
		
		arr[heapSize - 1] = -1;
		
		heapSize--;
		
		maxHeapify(arr, 0, heapSize);
		
	}

	private static void heapIncreaseValue(int[] arr, int index, int newPriority) {
		
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
