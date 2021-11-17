package k_mer_generator;

public class Main {

	public static void main (String[] args) {
		KmerGenerator kmer = new KmerGenerator("AAGATTGGTATTA", 3);
		
		kmer.start();
	}
}
