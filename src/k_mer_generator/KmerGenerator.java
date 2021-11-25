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
		
		System.out.println("DNA length: " + dna.length());
		
		for (int i=0; i<numThreads; i++) {
			int begin = indexDif*i;
			int end = indexDif*(i+1)-1;
			
			
			
			//System.out.println(i);
			
			if(i != numThreads-1) {
				executorService.execute(generate(begin, end));
				//System.out.println("Thread " + i + " begin " + begin + ", end " + end);
			}
			else {
				executorService.execute(generate(begin, dna.length()-k));
				//System.out.println("Thread " + i + " begin " + begin + ", end " + (dna.length()-k));
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
				
				for(int i=begin; i<=end; i++) {
					
					try {
						k_mers.add(dna.substring(i, (i+k)));
					} catch(ArrayIndexOutOfBoundsException exception) {
						//System.out.println(exception);
						k_mers.add(dna.substring(i, (i+k))); 
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
