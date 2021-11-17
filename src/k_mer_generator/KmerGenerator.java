package k_mer_generator;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KmerGenerator{

	private String dna;
	private int k;
	private ArrayList<String> k_mers = new ArrayList<String>();
	private int numThreads = 4;
	
	public KmerGenerator(String dna, int k) {
		this.dna = dna;
		this.k = k;
	}
	
	//A geração de k-mers já está dividida pelo número de Threads
	public void start() {
		
		int indexDif = this.dna.length()/numThreads;
		System.out.println(indexDif);
		
		for (int i=0; i<numThreads; i++) {
			int begin = indexDif*i;
			int end = indexDif*(i+1);
			
			System.out.println(i);
			if(i != numThreads-1) {
				generate(begin, end);
			}
			else {
				generate(begin, dna.length()-(k-1));
			}
		}
		
		System.out.println(k_mers.toString());
	}
	
	//Gera os k-mers
	public void generate(int begin, int end) {
		for(int i=begin; i<end; i++) {
			System.out.println(dna.substring(i, i+(k)));
			this.k_mers.add(dna.substring(i, i+(k)));
		}
	}
	
}
