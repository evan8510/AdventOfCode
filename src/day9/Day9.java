package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day9 {

	// Currently printing the 1 by 1 movements of head, next, compute movement of
	// tail
	// add nodes touched by tail to set touched

	public static void main(String[] args) {

		HashSet<String> touched = new HashSet<String>();
		String temp = "";
		char state; // L: left, R: right, U: up, D: down

		int hx = 0, hy = 0, tx = 0, ty = 0; // starting pos = [0,0]
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
						hx -= 1;
					else if (state == 'R')
						hx += 1;
					else if (state == 'U')
						hy += 1;
					else if (state == 'D')
						hy -= 1;

					if ((hx - tx) > 1 || (hx - tx) < -1) {
						if (hx > tx)
							tx++;
						if (hx < tx)
							tx--;
						if (hy > ty)
							ty++;
						if (hy < ty)
							ty--;
						temp = "" + tx + "," + ty;
						touched.add(temp);
					} else if ((hy - ty) > 1 || (hy - ty) < -1) {
						if (hx > tx)
							tx++;
						if (hx < tx)
							tx--;
						if (hy > ty)
							ty++;
						if (hy < ty)
							ty--;
						temp = "" + tx + "," + ty;
						touched.add(temp);
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
