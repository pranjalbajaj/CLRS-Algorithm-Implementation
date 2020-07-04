package sorting;

public class BubbleSort extends Sort {

	@Override
	public void sortAscending(int[] inputArr) {
		
		for (int i = 0; i < inputArr.length - 1; i++) {
			
			boolean swapped = false;
			
			for (int j = 0; j < inputArr.length - 1 - i; j++) {
				
				if (inputArr[j + 1] < inputArr[j]) {
					
					int temp = inputArr[j];
					
					inputArr[j] = inputArr[j + 1];
					
					inputArr[j + 1] = temp;
					
					swapped = true;
				}
				
			}
			
			if (!swapped) {
				
				break;
			}
		}
		
	}

	@Override
	public void sortDescending(int[] inputArr) {
		
		for (int i = 0; i < inputArr.length - 1; i++) {
			
			boolean swapped = false;
			
			for (int j = 0; j < inputArr.length - 1 - i; j++) {
				
				if (inputArr[j + 1] > inputArr[j]) {
					
					int temp = inputArr[j];
					
					inputArr[j] = inputArr[j + 1];
					
					inputArr[j + 1] = temp;
					
					swapped = true;
				}
				
			}
			
			if (!swapped) {
				
				break;
			}
		}
		
	}

}
