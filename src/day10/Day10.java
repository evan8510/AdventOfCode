package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 {

	public static void main(String[] args) {
		
		int cycle = 1; 	// count the cycles
		int reg = 1; 	// keep track of register
		int sum = 0;	// add 20th, 60th, 100th, 140th, 180th, 220th signal strengths
		
		try {
			File f = new File("src/day10/d10input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data[] = s.nextLine().split(" "); // split on whitespace
				
				if(((cycle-20)%40)==0) // iff a relevant cycle add to sum
					sum+= (cycle*reg);
				if(data[0].equals("noop"));
					// no operation
				else {
					cycle++; // increment
					if(((cycle-20)%40)==0) // iff a relevant cycle add to sum
						sum+= (cycle*reg);
					reg += Integer.parseInt(data[1]); // update reg
				}
				cycle++; // increment
				
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
		System.out.println("Total: " + sum);
	}

}
