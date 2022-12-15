package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day11p2 {

	public static void main(String[] args) {
		
		Monkey2 monkeys[] = new Monkey2[8];
		for(int i =0; i<monkeys.length; i++)
			monkeys[i] = new Monkey2();
		
		int monkeyNum = 0;
		LinkedList<Long> temp = new LinkedList<Long>();
		
		// read in data
		try {
			File f = new File("src/day11/d11input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data[] = s.nextLine().trim().split(" "); // split on whitespace\
				if(data[0].equals("Monkey")) {
					monkeyNum = Integer.parseInt(data[1].trim().substring(0, 1));
				}
				if(data[0].equals("Starting")) { // add items to monkey
					for(int i = 2;i<data.length;i++)
						temp.add(Long.parseLong(data[i].replaceAll(",", "")));
					monkeys[monkeyNum].setItems(temp); // send in worry levels to monkey
					temp.clear();
				}
				if(data[0].equals("Operation:")) // add operation to monkey
					monkeys[monkeyNum].setOperation(data[4].charAt(0), data[5]);
				
				if(data[0].equals("Test:")) 
					monkeys[monkeyNum].setDivisor(Long.parseLong(data[3]));
				if(data.length>1) {
					if(data[1].equals("true:")) 
						monkeys[monkeyNum].setReceiverT(monkeys[Integer.parseInt(data[5])]);
					if(data[1].equals("false:")) 
						monkeys[monkeyNum].setReceiverF(monkeys[Integer.parseInt(data[5])]);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
		
		for(int i=0;i<10000;i++) { // run the passing 10000 times
			for(int j =0; j<monkeys.length;j++) {
					monkeys[j].pass();
			}
		}
		
		// get monkey business level
		
		long m1=0, m2=0;
		for(int i=0; i<monkeys.length; i++) {
			if(monkeys[i].getSum()>m1) {
				m2 = m1;
				m1 = monkeys[i].getSum();
			}
			else if (monkeys[i].getSum()>m2)
				m2 = monkeys[i].getSum();
		}
		System.out.println("Monkey business level is: " + (m1*m2));
		

	}

}
