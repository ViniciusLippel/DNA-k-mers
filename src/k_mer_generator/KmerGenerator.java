package k_mer_generator;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KmerGenerator{

	private String dna;
	private ArrayList<String> k_mers = new ArrayList<String>();
	private int numThreads = 4;
	
	public KmerGenerator(String dna) {
		this.dna = dna;
	}
	
	public void start() {
		ExecutorService executorService = Executors.newFixedThreadPool(this.numThreads);
		
		int indexDif = this.dna.length()/numThreads;
		
		for (int i=0; i<numThreads; i++) {
			int begin = indexDif*i;
			int end = indexDif*(i+1);
			
			if(i != numThreads-1) {
				generate()
			}
		}
	}
	
	public Runnable generate(int begin, int end) {
		
	}
	
}
