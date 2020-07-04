package sorting;

import java.util.Arrays;

public abstract class Sort implements SortInterface {
	
	public void printSortedResult(int[] resultArr) {
		
		System.out.println(Arrays.toString(resultArr));
	}
	
	public void printSortedResult(String[] resultArr) {
		
		System.out.println(Arrays.toString(resultArr));
	}
	
	public void printSortedResult(double[] resultArr) {
		
		System.out.println(Arrays.toString(resultArr));
	}

}
