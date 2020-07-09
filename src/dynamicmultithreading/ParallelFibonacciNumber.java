package dynamicmultithreading;

class FibThread extends Thread{
	
	int n;
	
	int fibNum;
	
	public FibThread(int n) {
		
		this.n = n;
	}
	
	public void run() {
		
		try {
			fibNum = fibNum(n);
			
			System.out.println(Thread.currentThread().getName() + "---->" + fibNum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int fibNum(int n) throws InterruptedException {
		
		if (n <= 1)
			return n;
		else {
			
			FibThread t1 = new FibThread(n -1);
			
			t1.start();
			
			int x = fibNum(n-2);
			
			t1.join();
			
			return t1.fibNum + x;
			
		}
	}
}

public class ParallelFibonacciNumber {
	
	public static void main(String[] args) throws InterruptedException {
		
		FibThread ft = new FibThread(3);
		
		ft.start();
		
		ft.join();
		
		System.out.println(ft.fibNum);
	}
}