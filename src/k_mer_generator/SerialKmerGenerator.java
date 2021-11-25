package k_mer_generator;

import java.util.ArrayList;

public class SerialKmerGenerator {
	
	private String dna;
	private int k;
	private ArrayList<String> k_mers = new ArrayList<String>();
	
	public SerialKmerGenerator(String dna, int k) {
		this.dna = dna;
		this.k = k;
	}
	
	public ArrayList<String> getK_mers() {
		return k_mers;
	}
	
	public void setK_mers(ArrayList<String> k_mers) {
		this.k_mers = k_mers;
	}
	
	public void start() {
		
		for(int i=0; i<=dna.length()-k; i++) {
			k_mers.add(dna.substring(i, i+(k)));
		}
	}
	
	public void printKmers() {
		for(int i=0; i<k_mers.size(); i++) {
			System.out.println((k_mers.get(i)));
		}
	}
}
