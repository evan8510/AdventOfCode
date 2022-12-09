package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {

	public static void main(String[] args) {
		HashSet<Character> items = new HashSet<Character>();
		int sum = 0;
		int p = 2; 		// 1 -> p1 solution
						// 2 -> p2 solution

		if (p == 1) { 	// Solution for p1

			try {
				File f = new File("src/day3/d3input.txt");
				Scanner s = new Scanner(f);
				while (s.hasNextLine()) {
					String[] data = s.nextLine().split(""); 					// split every letter
					for (int i = 0; i < (data.length) / 2; i++) 
						items.add(data[i].charAt(0)); 							// fill set with half the characters in line
					for (int i = (data.length) / 2; i < data.length; i++) {
						if (items.contains(data[i].charAt(0))) { 				// if the second half of the line is in the set add to sum
																				// otherwise keep looking for a duplicate
							if (data[i].charAt(0) > 90) 						
								sum += ((int) (data[i].charAt(0)) - 96);
							else
								sum += ((int) (data[i].charAt(0) - 38));
							break;
						}
					}
					items.clear(); // empty items
				}
				s.close();
			} catch (FileNotFoundException e) {
				System.out.println("couldnt load file.\n");
				e.printStackTrace();
			}
			System.out.println("Sum is: " + sum);
		}

		if (p == 2) { // Solution for p2
			HashSet<Character> badges = new HashSet<Character>();	// temp set to fill with chars in first 2 lines
			int counter = 0; // when counter%3=0 new group of 3
			try {
				File f = new File("src/day3/d3input.txt");
				Scanner s = new Scanner(f);
				while (s.hasNextLine()) {
					String data = s.nextLine();
					if (counter % 3 == 0) {
						for (int i = 0; i < data.length(); i++) 	// fill items with first line
							items.add(data.charAt(i));
					} else if (counter % 3 == 1) {					// fill badges with chars in items & line 2
						for (int i = 0; i < data.length(); i++) {
							char temp = data.charAt(i);
							if (items.contains(temp)) 
								badges.add(temp);
						}
						items.clear();								// empty items, badges now holds the set to check against
					} else if (counter % 3 == 2) {
						for (int i = 0; i < data.length(); i++) {
							char temp = data.charAt(i);
							if (badges.contains(temp)) {			// add to sum priotiry of first match in line 3 and badges
								if ((int) temp > 90)
									sum += ((int) (temp) - 96);
								else
									sum += ((int) (temp) - 38);
								badges.clear();						// clear sets
								items.clear();
							}
						}
					}
					counter++; 										// increment counter
				}
				s.close();
			} catch (FileNotFoundException e) {
				System.out.println("couldnt load file.\n");
				e.printStackTrace();
			}

			System.out.println("Sum is: " + sum);
		}
	}

}
