package k_mer_generator;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main (String[] args) throws FileNotFoundException, InterruptedException {
		
		String dna = new FastaReader().read("dna_files/test9.fa");
		
		runMultiThread(dna);
		
		runSerial(dna);
	}
	
	public static void runMultiThread(String dna) throws InterruptedException {
		
		KmerGenerator kmer = new KmerGenerator(dna, 3);
		
		long startTime = System.nanoTime();
		kmer.start();
		kmer.getExecutorService().awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		long endTime = System.nanoTime();
		
		System.out.println("\nMultithread time: " + ((endTime-startTime)/1000000) + "ms");
		
		//kmer.printKmers();
	}
	
	public static void runSerial(String dna) {
		
		SerialKmerGenerator skmer = new SerialKmerGenerator(dna, 5);
		
		long startTime = System.nanoTime();
		skmer.start();
		long endTime = System.nanoTime();
		
		System.out.println("\nSerial time: " + ((endTime-startTime)/1000000) + "ms");
	}
}
