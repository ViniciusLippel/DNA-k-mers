package k_mer_generator;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KmerGenerator{

	private String dna;
	private int k;
	private ArrayList<String> k_mers = new ArrayList<String>();
	private final int numThreads = 4;
	private ExecutorService executorService;
	
	public KmerGenerator(String dna, int k) {
		this.dna = dna;
		this.k = k;
	}
	
	public ArrayList<String> getK_mers() {
		return k_mers;
	}
	
	public void setK_mers(ArrayList<String> k_mers) {
		this.k_mers = k_mers;
	}
	
	

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void start() {
		
		executorService = Executors.newFixedThreadPool(numThreads);
		
		int indexDif = this.dna.length()/numThreads;
		
		for (int i=0; i<numThreads; i++) {
			int begin = indexDif*i;
			int end = indexDif*(i+1);
			
			//System.out.println(i);
			
			if(i != numThreads-1) {
				executorService.execute(generate(begin, end));
			}
			else {
				executorService.execute(generate(begin, dna.length()-(k-1)));
			}
		}
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			System.out.println(k_mers.toString());
		}
		
	}
	
	//Gera os k-mers
	public Runnable generate(int begin, int end) {
		return new Runnable() {
			public void run() {
				for(int i=begin; i<end; i++) {
					//System.out.println(Thread.currentThread().getName() + " " + dna.substring(i, (i+k)));
					try {
						k_mers.add(dna.substring(i, (i+k)));
					} catch(ArrayIndexOutOfBoundsException exception) {
						System.out.println(i + ", " + (i+k));
					}
					
				}
			}
		};
	}
	
	public void printKmers() {
		for(int i=0; i<k_mers.size(); i++) {
			System.out.println((k_mers.get(i)));
		}
	}
	
}
