package executorsExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {
	
	public static void main (String[] args) {
		ExecutorService executorService = 
				Executors.newFixedThreadPool(4);
		
		executorService.execute(newRunnable("task 1"));
		executorService.execute(newRunnable("task 2"));
		executorService.execute(newRunnable("task 3"));
		executorService.execute(newRunnable("task 4"));
		
		executorService.shutdown();
	}
	
	private static Runnable newRunnable(String msg) {
		return new Runnable() {
			public void run() {
				String completeMsg =
						Thread.currentThread().getName() +
						": " + msg;
				System.out.println(completeMsg);
			}
		};
	}
}
