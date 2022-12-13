package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10p2 {

	public static void main(String[] args) {

		int pixel = 0; // count the pixels
		int reg = 1; // keep track of register

		try {
			File f = new File("src/day10/d10input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data[] = s.nextLine().split(" "); // split on whitespace

				draw(reg, pixel % 40);

				if (data[0].equals("noop"));
				// no operation
				else {
					pixel++; // increment
					draw(reg, pixel % 40);
					reg += Integer.parseInt(data[1]); // update reg
				}
				pixel++; // increment

			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
	}

	static void draw(int r, int p) {
		if (p == r || p == r - 1 || p == r + 1) // if within 1 pixel of sprite pixel is lit
			System.out.print("#");
		else
			System.out.print(".");
		if (p == 39) // on 40th pixel start new line
			System.out.println("");
	}

}