package miscellaneous;

import java.util.Arrays;

public class MergingSortedArraysUsingHeap {

	public static void main(String[] args) {
		
		int[] arr1 = { 12, 34, 55, 67, 79, 80 };
		
		int[] arr2 = { 13, 16, 23, 56 };
		
		int[] arr3 = { 11, 22, 43, 84, 95 };
		
		int[] arr4 = mergeArray(arr1, arr2, arr3);
		
		System.out.println(Arrays.toString(arr4));

	}

	private static int[] mergeArray(int[]... arr) {
		
		int size = 0;
		
		MinHeapNode[] tempArr = new MinHeapNode[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			
			size+=arr[i].length;
			
			MinHeapNode minHeapNode = new MinHeapNode(arr[i][0], i, 0);
			
			tempArr[i] = minHeapNode;
		}
		
		buildMinHeap(tempArr, tempArr.length);
		
		int[] resultArr = new int[size];
		
		resultArr[0] = tempArr[0].getElement();
		
		for (int i = 1; i < size; i++) {
			
			int arrayIndex = tempArr[0].getArrayNumber();
			
			int arrayElementIndex = tempArr[0].getIndexNumber();
			
			tempArr[0].setIndexNumber(++arrayElementIndex);
			
			int element;
			
			try {
				element = arr[arrayIndex][arrayElementIndex];
			} catch (ArrayIndexOutOfBoundsException exception) {

				element = Integer.MAX_VALUE;
			}
			
			tempArr[0].setElement(element);
			
			buildMinHeap(tempArr, tempArr.length);
			
			resultArr[i] = tempArr[0].getElement();
		}
		
		
		
		return resultArr;
	}
	
	public static void buildMinHeap(MinHeapNode[] arr, int heapSize) {

		for (int i = ((heapSize / 2) - 1); i >= 0; i--)
			minHeapify(arr, i, heapSize);
	}

	public static void minHeapify(MinHeapNode[] arr, int rootIdx, int heapSize) {

		int smallest = rootIdx;

		int leftIdx = 2 * rootIdx + 1;

		int rightIdx = 2 * rootIdx + 2;

		if (leftIdx < heapSize && arr[leftIdx].getElement() < arr[smallest].getElement()) {

			smallest = leftIdx;

		}

		if (rightIdx < heapSize && arr[rightIdx].getElement() < arr[smallest].getElement()) {

			smallest = rightIdx;
		}

		if (smallest != rootIdx) {

			MinHeapNode temp = arr[smallest];

			arr[smallest] = arr[rootIdx];

			arr[rootIdx] = temp;

			minHeapify(arr, smallest, heapSize);

		}
	}

}

class MinHeapNode {
	
	private int element;
	
	private int arrayNumber;
	
	private int indexNumber;
	
	public MinHeapNode(int element, int arrayNumber, int indexNumber) {
		
		this.element = element;
		
		this.arrayNumber = arrayNumber;
		
		this.indexNumber = indexNumber;
	}

	public int getElement() {
		return element;
	}

	public void setElement(int element) {
		this.element = element;
	}

	public int getArrayNumber() {
		return arrayNumber;
	}

	public void setArrayNumber(int arrayNumber) {
		this.arrayNumber = arrayNumber;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}
	
}