package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {

	public static void main(String[] args) {
		
		try { // 1531
			File f = new File("src/day6/d6input.txt");
			Scanner s = new Scanner(f);
			String temp = "";
			while (s.hasNextLine()) {
				String str =s.nextLine();
				// start of packer marker 
				String data[] = ("abcd" + str).split(""); // add 4 extra distinct characters
					for(int i =0; i< data.length-3;i++) {
						temp = data[i] + data[i+1] + data[i+2] + data[i+3];
						// each new character added after "abcd" check if in string, if so jump forward in string
						if(temp.charAt(3)==temp.charAt(0));
						else if(temp.charAt(3)==temp.charAt(1) || temp.charAt(2)==temp.charAt(0)) 
							i++;
						else if(temp.charAt(3)==temp.charAt(2) || temp.charAt(2)==temp.charAt(1)
								|| temp.charAt(1)==temp.charAt(0)) 
							i+=2;
						else if(i>3){
							System.out.println(temp + " at: " + i);
							break;
						}
					}
					data = str.split(""); // split on whitespace
					int incr = 0;
					for(int i =0; i< data.length-13; i++) {
						temp = str.substring(i,i+14);	// substring of length 14
						incr = checkStr(temp);			// how much of the string can be skipped
						if(incr ==0) {
							System.out.println(temp + " at: " + (i+14));
							break;
						}
						else 
							i += incr-1;				// skip to new location 
					}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
	}
	
	static int checkStr(String s) {	// return difference between the matches
		for(int i=0;i<s.length();i++) {
			for(int j =i+1; j<s.length();j++) {
				if(s.charAt(i)==s.charAt(j))
					return j-i;
			}
		}
		return 0;
	}

	

}
