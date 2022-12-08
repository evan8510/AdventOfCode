package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
	/*
	 * Rock = 1 Paper = 2 Scissors= 3
	 * 
	 * 
	 */

	public static void main(String[] args) {
		ArrayList<Character> moves;
		moves = new ArrayList<Character>();
		try {
			File f = new File("src/day2/day2input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data = s.nextLine();
				moves.add(data.charAt(0));
				moves.add(data.charAt(2));
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}

		int score = 0;
		int myMove = 0;
		int opMove = 0;
		
		/* for loop for P1
		for (int i = 0; i < moves.size(); i += 2) { 
			// cast character to int and make it fall between 1&3
			myMove = ((int) (moves.get(i + 1))) - 87;
			opMove = ((int) moves.get(i)) - 64;

			score += myMove;

			if (myMove == opMove) {
				score += 3;
			} else if ((myMove == 1) && (opMove == 3)) {
				score += 6;
			} else if ((myMove == 2) && (opMove == 1)) {
				score += 6;
			} else if ((myMove == 3) && (opMove == 2)) {
				score += 6;
			}
		}
		*/
		
		// for loop for P2
		for (int i = 0; i < moves.size(); i += 2) {
			// cast character to int and make it fall between 1&3
			myMove = ((int) (moves.get(i + 1))) - 87;
			opMove = ((int) moves.get(i)) - 64;

			if (myMove == 3) { // determine move if win
				score += 6;
				if (opMove == 1)
					score += 2;
				if (opMove == 2)
					score += 3;
				if (opMove == 3)
					score += 1;
			}
			if (myMove == 2) { // determine move if tie
				score += 3 + opMove;
			}
			if (myMove == 1) { // determine move if loss
				if (opMove == 1)
					score += 3;
				if (opMove == 2)
					score += 1;
				if (opMove == 3)
					score += 2;
			}

		}
		System.out.print("Final score is: " + score);

	}

}
