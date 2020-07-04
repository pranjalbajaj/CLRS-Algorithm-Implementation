package greedy;

import java.util.ArrayList;

public class ActivitySelection {
		
	public static void main(String[] args) {
		
		int[][] activity = { {1, 4}, {3, 5}, {0, 6}, {5, 7}, {3, 9}, {5, 9}, {6, 10}, {8, 11}, {8, 12}, {2, 14}, {12, 16} };
		
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		//recursiveActivitySelector(activity, 0, list);
		
		iterativeActivitySelector(activity, list);
		
		for (int i = 0; i < list.size(); i++) {
			
			int[] arr = list.get(i);
			
			System.out.print(arr[0] + " " + arr[1] + "\n");		
		}

	}
	
	public static void recursiveActivitySelector(int[][] activityArr, int k, ArrayList<int[]> list) {
		
		int m = k + 1;
		
		while (m <= activityArr.length - 2 && activityArr[k][1] > activityArr[m][0]) {
			
			m++;
		}
		
		list.add(activityArr[k]);
		
		if (m < activityArr.length) {
			
			recursiveActivitySelector(activityArr, m, list);
		}
	}
	
	public static void iterativeActivitySelector(int[][] activityArr, ArrayList<int[]> list) {
		
		int k = 0;
		
		list.add(activityArr[k]);
		
		for (int m = 1; m < activityArr.length; m++) {
			
			if (activityArr[k][1] > activityArr[m][0]) {
				
				continue;
			}
			else {
				
				k = m;
				
				list.add(activityArr[k]);
			}
		}
	}
}
