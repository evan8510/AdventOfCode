package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7 {

	public static void main(String[] args) {
		Node temp = new Node(0, "/", null);
		Node head = temp;
		// hard coded first line of input

		try {
			File f = new File("src/day7/inputd7.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String[] data = s.nextLine().split(" "); // split based on whitespace

				if (data[0].charAt(0) == '$') { // when a command is entered
					
					if (data[1].charAt(0) == 'l') // ignore
						;
					else if (data[2].charAt(0) == '.') // go outward once in the tree
						head = head.getParent();
					else if (data[2].charAt(0) == '/') // return to the root node
						head = temp;
					else // move to the specified directory
						head = head.getChild(data[2]);

				} else if (data[0].charAt(0) == 'd') // add a child that starts with dir
					head.addChildren(new Node(0, data[1], head), data[1]);

				else // add a child that starts with size
					head.addChildren(new Node(Integer.parseInt(data[0]), data[1], head), data[1]);

			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
		System.out.println("Size of all: " + temp.evalSizes());
		// System.out.println(temp.printAll());
		System.out.println("Sum of all dirs under 100000: " + temp.sumSub100000());
		System.out.println("Smallest dir for removal that will allow update: " + temp.findDir(48008081));

		/*
		 * Size of all: 48008081 Sum of all dirs under 100000: 1141028 Smallest dir for
		 * removal that will allow update: 8278005
		 */

	}

}
