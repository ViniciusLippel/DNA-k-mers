package k_mer_generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FastaReader {
	
	public String read(String file) throws FileNotFoundException {
		
		StringBuilder sb = new StringBuilder();
		
        try (Scanner sc = new Scanner(new File(file))) {
            
        	while (sc.hasNextLine()) {
               
        		String line = sc.nextLine().trim();
                
        		if (line.charAt(0) != '>' && line.charAt(0) != ' ') {
                	sb.append(line);
                }
            }
            
        	return sb.toString();
        }
        
	}
}
