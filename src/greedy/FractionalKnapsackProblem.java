/**
 * 
 */
package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author pranjal
 *
 */
public class FractionalKnapsackProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] weights = { 10, 40, 20, 30 };
		
		int[] values = { 60, 40, 100, 120 };
		
		int capacity = 50;
		
		Item[] itmArr = new Item[weights.length]; 
		
		for (int i = 0; i < weights.length; i++) {
			
			Item itm = new Item(weights[i], values[i]);
			
			itmArr[i] = itm;
		}
		
		Arrays.sort(itmArr, new MyComparator());
		
		knapsackSolution(itmArr, capacity);
		
	}

	private static void knapsackSolution(Item[] itmArr, int capacity) {
		
		double totalVal = 0;
		
		for (Item item : itmArr) {
			
			if (capacity >= item.getWt()) {
				
				totalVal += item.getVal();
				
				capacity -= item.getWt();
				
				System.out.print(item.getWt() + "\t");
			}
			else {
				
				double fraction = capacity / item.getWt();
				
				totalVal += fraction*item.getVal();
				
				System.out.print(capacity + "\t");
				
				break;
			}
		}
		
		
		System.out.println("Net Value: " + totalVal);
	}

}

class Item {
	
	private double wt;
	
	private double val;
	
	private Double valByWt;
	
	public Item(double wt, double val) {
		
		this.wt = wt;
		
		this.val = val;
		
		this.valByWt = val/wt;
	}

	public double getWt() {
		return wt;
	}

	public void setWt(double wt) {
		this.wt = wt;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}

	public Double getValByWt() {
		return valByWt;
	}

	public void setValByWt(Double valByWt) {
		this.valByWt = valByWt;
	}
}

class MyComparator implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		
		return (item2.getValByWt()).compareTo(item1.getValByWt());
	}
	
}
