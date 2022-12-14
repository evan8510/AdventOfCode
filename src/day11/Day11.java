package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day11 {

	public static void main(String[] args) {
		
		Monkey monkeys[] = new Monkey[4];
		for(int i =0; i<monkeys.length; i++)
			monkeys[i] = new Monkey();
		
		int monkeyNum = 0;
		LinkedList<Integer> temp = new LinkedList<Integer>();
		try {
			File f = new File("src/day11/d11input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data[] = s.nextLine().trim().split(" "); // split on whitespace\
				if(data[0].equals("Monkey")) {
					monkeyNum = Integer.parseInt(data[1].trim().substring(0, 1));
				}
				if(data[0].equals("Starting")) {
					for(int i = 2;i<data.length;i++)
						temp.add(Integer.parseInt(data[i].replaceAll(",", "")));
					monkeys[monkeyNum].setItems(temp); // send in worry levels to monkey
					temp.clear();
				}
				if(data[0].equals("Operation:"))
					monkeys[monkeyNum].setOperation(data[4].charAt(0), data[5]);
				
				if(data[0].equals("Test:")) 
					monkeys[monkeyNum].setDivisor(Integer.parseInt(data[3]));
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
		
		for(int i=0;i<20;i++) {
			for(int j =0; j<monkeys.length;j++) {
					monkeys[j].pass();
			}
		}
		
		int m1=0, m2=0;
		for(int i=0; i<monkeys.length; i++) {
			if(monkeys[i].getSum()>m1) {
				m2 = m1;
				m1 = monkeys[i].getSum();
			}
			else if (monkeys[i].getSum()>m2)
				m2 = monkeys[i].getSum();
		}
		System.out.println("Monkey business level is: " + (m1*m2));
		int x = 11*7*13*5*3*17*2*19;
		System.out.println(x);

	}

}