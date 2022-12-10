package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day9p2 {

	// add (x,y) locations touched by tail to set touched in a String
	// size of touched will be how many different positions it touched,
	// set will not store duplicates

	public static void main(String[] args) {

		HashSet<String> touched = new HashSet<String>();
		String temp = "";
		char state; // L: left, R: right, U: up, D: down

		int xNodes[] = new int[10];
		int yNodes[] = new int[10];

		for (int i = 0; i < 10; i++) {
			xNodes[i] = 0;
			yNodes[i] = 0;
		}

		int dist = 0;
		try {
			File f = new File("src/day9/d9input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String[] data = s.nextLine().split(" "); // split every letter

				dist = Integer.parseInt(data[1]); // distance head will move
				state = data[0].charAt(0); // direction head will move

				for (int i = 0; i < dist; i++) {
					if (state == 'L')
						xNodes[0] -= 1;
					else if (state == 'R')
						xNodes[0] += 1;
					else if (state == 'U')
						yNodes[0] += 1;
					else if (state == 'D')
						yNodes[0] -= 1;

					for (int j = 1; j < 10; j++) { // shuffle each section of rope based on the section of rope in front of it
						if ((xNodes[j - 1] - xNodes[j]) > 1 || (xNodes[j - 1] - xNodes[j]) < -1 ||
								((yNodes[j - 1] - yNodes[j]) > 1 || (yNodes[j - 1] - yNodes[j]) < -1)) { // check if section needs to move
							if (xNodes[j - 1] > xNodes[j])
								xNodes[j]++;
							if (xNodes[j - 1] < xNodes[j])
								xNodes[j]--;
							if (yNodes[j - 1] > yNodes[j])
								yNodes[j]++;
							if (yNodes[j - 1] < yNodes[j])
								yNodes[j]--;
							temp = "" + xNodes[9] + "," + yNodes[9]; 
							touched.add(temp); // add new position to set
						}
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
		System.out.println("Tail touched " + touched.size() + " positions.");
	}

}