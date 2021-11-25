package k_mer_generator;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main (String[] args) throws FileNotFoundException, InterruptedException {
		
		String dna = new FastaReader().read("src/k_mer_generator/test8.fa");
		
		KmerGenerator kmer = new KmerGenerator(dna, 5);
		long startTime = System.nanoTime();
		kmer.start();
		//kmer.getExecutorService().awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		long endTime = System.nanoTime();
		//kmer.printKmers();
		System.out.println("Multithread time: " + (endTime-startTime));
		System.out.println("end");
		
		SerialKmerGenerator skmer = new SerialKmerGenerator(dna, 5);
		startTime = System.nanoTime();
		skmer.start();
		endTime = System.nanoTime();
		System.out.println("Serial time: " + (endTime-startTime));
	}
}
