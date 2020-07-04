package sorting;

public class SortMain {

	public static void main(String[] args) {

		int[] arr = { 38, 27, 43, 3, 9, 82, 10 };

		/*
		 * InsertionSort insertionSort = new InsertionSort();
		 * 
		 * insertionSort.sortAscending(arr);
		 * 
		 * //insertionSort.sortDescending(arr);
		 * 
		 * insertionSort.printSortedResult(arr);
		 */

		/*
		 * MergeSort mergeSort = new MergeSort();
		 * 
		 * mergeSort.sortAscending(arr);
		 * 
		 * mergeSort.printSortedResult(arr);
		 */

		/*
		 * RecursiveInsertionSort recursiveInsertionSort = new RecursiveInsertionSort();
		 * 
		 * recursiveInsertionSort.sort(arr, 1);
		 * 
		 * recursiveInsertionSort.printSortedResult(arr);
		 */

		/*
		 * BubbleSort bubbleSort = new BubbleSort();
		 * 
		 * bubbleSort.sortAscending(arr);
		 * 
		 * //bubbleSort.sortDescending(arr);
		 * 
		 * bubbleSort.printSortedResult(arr);
		 */

		/*
		 * HeapSort heapSort = new HeapSort();
		 * 
		 * int heapSize = arr.length;
		 * 
		 * heapSort.sort(arr, heapSize);
		 * 
		 * heapSort.printSortedResult(arr);
		 */

		/*
		 * QuickSort quickSort = new QuickSort();
		 * 
		 * quickSort.randomizedQuickSort(arr, 0, arr.length - 1);
		 * 
		 * quickSort.printSortedResult(arr);
		 */

		/*
		 * int[] A = { 2, 5, 3, 0, 2, 3, 0, 3 };
		 * 
		 * CountingSort countingSort = new CountingSort();
		 * 
		 * int[] B = countingSort.sortStableForPositiveNumbers(A, 5);
		 * 
		 * countingSort.printSortedResult(B);
		 */

		// int[] A = { 170, 45, 75, 90, 802, 24, 2, 66 };
		
		  
		/*
		 * int[] A = { -5, -10, 0, -3, 8, 5, -1, 10 };
		 * 
		 * CountingSort countingSort = new CountingSort();
		 * 
		 * int[] B = countingSort.sortStableForAllNumbers(A);
		 * 
		 * countingSort.printSortedResult(B);
		 */
		 

		/*
		 * String[] A = {"COW", "DOG", "SEA", "RUG", "ROW", "MOB", "BOX", "TAB", "BAR",
		 * "EAR", "TAR", "DIG", "BIG", "TEA", "NOW", "FOX"};
		 * 
		 * RadixSortForWords radixSort = new RadixSortForWords();
		 * 
		 * radixSort.sort(A);
		 * 
		 * radixSort.printSortedResult(A);
		 */

		/*
		 * String[] A = { "APPLE", "AUSTRALIA", "ALGORITHM", "SELL", "OLYMPIC", "JACK",
		 * "SLEEP" };
		 * 
		 * RadixSortForWords radixSort = new RadixSortForWords();
		 * 
		 * radixSort.sort(A);
		 * 
		 * radixSort.printSortedResult(A);
		 */
		
		
		  double[] A = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
		  
		  BucketSort bucketSort = new BucketSort();
		  
		  bucketSort.sort(A);
		  
		  bucketSort.printSortedResult(A);
		 

	}

}
