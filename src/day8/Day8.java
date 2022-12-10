package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8 {

	/*
	 * grid is 99x99
	 */
	
	
	/*
	 * NOTE: 	for p2, could be done with a stack instead of an array of 10 elements
	 * 			bottom of stack would be most recent largest element, on top of it
	 * 			are the closer, smaller elements, pop off nodes from stack if current node
	 * 			being looked at is larger than them, until either stack is empty, or the top node,
	 * 			is of equal or greater size 
	 */

	public static void main(String[] args) {

		XmasTree[][] trees = new XmasTree[99][99];
		int lineCount = 0;

		int largest = -1;
		int size = -1;
		boolean vis = false;
		try {
			File f = new File("src/day8/d8input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String[] data = s.nextLine().split(""); // split based on whitespace
				for (int i = 0; i < data.length; i++) {
					size = Integer.parseInt(data[i]);
					vis = (size > largest);
					trees[lineCount][i] = new XmasTree(size, vis); // construct trees and set vis from left

					if (vis)
						largest = size;
				}
				lineCount++;
				largest = -1;
				vis = false;
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
		// set vis from right
		for (int i = 0; i < 99; i++) {
			for (int j = 98; j > -1; j--) {
				size = trees[i][j].getHeight();
				vis = (size > largest);
				trees[i][j].setRight(vis);
				if (vis)
					largest = size;
			}
			largest = -1;
		}
		// set vis from top
		for (int i = 0; i < 99; i++) {
			for (int j = 0; j < 99; j++) {
				size = trees[j][i].getHeight();
				if (size > largest) {
					largest = size;
					trees[j][i].setTop(true);
				}
			}
			largest = -1;
		}
		// set vis from bot
		for (int i = 0; i < 99; i++) {
			for (int j = 98; j > -1; j--) {
				size = trees[j][i].getHeight();
				if (size > largest) {
					largest = size;
					trees[j][i].setBot(true);
				}
			}
			largest = -1;
		}

		int lrgs[] = new int[10];

		int cblock = 0;

		// set the left scores
		for (int i = 0; i < 99; i++) {
			for (int j = 0; j < 99; j++) {

				for (int k = (trees[i][j].getHeight()); k < 10; k++) {
					if (cblock < lrgs[k]) {
						cblock = lrgs[k]; // cblock is now the closest location i, of our height or higher
					}
				}

				trees[i][j].setScrLeft(j - cblock);

				lrgs[trees[i][j].getHeight()] = j;
				cblock = 0;
			}

			for (int k = 0; k < 10; k++) {
				lrgs[k] = 0;
			}

		}

		// set the Top scores
		
			for (int j = 0; j < 99; j++) {
				for (int i = 0; i < 99; i++) {
					
				for (int k = (trees[i][j].getHeight()); k < 10; k++) {
					if (cblock < lrgs[k]) {
						cblock = lrgs[k]; // cblock is now the closest location i, of our height or higher
					}
				}

				trees[i][j].setScrTop(i - cblock);

				lrgs[trees[i][j].getHeight()] = i;
				cblock = 0;
			}

			for (int k = 0; k < 10; k++) {
				lrgs[k] = 0;
			}

		}
		cblock = 98;
		// set the right scores
		for (int i = 0; i < 99; i++) {
			for (int j = 98; j >= 0; j--) {

				for (int k = (trees[i][j].getHeight()); k < 10; k++) {
					if (cblock > lrgs[k]) {
						cblock = lrgs[k]; // cblock is now the closest location i, of our height or higher
					}
				}

				trees[i][j].setScrRight(cblock - j);

				lrgs[trees[i][j].getHeight()] = j;
				cblock = 98;
			}

			for (int k = 0; k < 10; k++) {
				lrgs[k] = 98;
			}

		}

		// set the bottom scores
		cblock = 98;
		// cblock is distance between i and 
		for (int j = 0; j < 99; j++) {
			for (int i = 98; i >= 0; i--) {
				for (int k = (trees[i][j].getHeight()); k < 10; k++) {
					if (cblock > lrgs[k]) {
						cblock = lrgs[k]; // cblock is now the closest location i, of our height or higher
					}
				}

				trees[i][j].setScrBottom(cblock - i);

				lrgs[trees[i][j].getHeight()] = i;
				cblock = 98;
			}

			for (int k = 0; k < 10; k++) {
				lrgs[k] = 98;
			}

		}

		int bestScore = 0;
		for (int i = 0; i < trees.length; i++) {
			for (int j = 0; j < 99; j++) {
				if (trees[i][j].getScore() > bestScore) {
					bestScore = trees[i][j].getScore();
					System.out.println("i: " + i +" j: " + j);
					System.out.println("HEIGHT: " + trees[i][j].getHeight());
					System.out.println("Left: " + trees[i][j].getLeftScore() + "\nRight: "+ trees[i][j].getRightScore()+ "\nTop: "+ trees[i][j].getTopScore()+ "\nBot: "+ trees[i][j].getBotScore());
				}
			}

		}
		System.out.println("bestScore: " + bestScore);

		int sum = 0;
		for (int i = 0; i < trees.length; i++) {
			for (int j = 0; j < 99; j++) {
				if (trees[i][j].getVis())
					sum++;
			}

		}
		System.out.println("Sum is: " + sum);

	}

}
