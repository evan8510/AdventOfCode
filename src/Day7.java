import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7 {

	
	public static void main(String[] args) {
		Node temp = new Node(0,"/", null);
		Node head = temp;
		Node[] arr = new Node[1000];
		int st = 0; // st = 0 ->  we lsed and are chuckin stuff in
		try {
			File f= new File("C:\\Users\\YUNG DEAG\\eclipse-workspace\\AdventOfCode\\src\\inputd7.txt");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String[] data = s.nextLine().split(" ");
				
				if(data[0].charAt(0)=='$') {
					if(data[1].charAt(0)=='l') {
						st = 0;
					}
					else if(data[2].charAt(0)=='.'){
						st = 1;
						head = head.callMom();
					}
					else if(data[2].charAt(0)=='/') {
						st = 1;
						head = temp;
					}
					else {
						st = 1;
						//System.out.println("old head: " +  head.getName());
						head = head.pickUpSonFromDayCare(data[2]);
						//System.out.println("new head: " +  head.getName());
					}
				}
				else if(data[0].charAt(0)=='d'){
						if(st==0) {
							head.addChildren(new Node(0,data[1], head), data[1]);
						}
				}
				else {
					if(st==0)
						head.addChildren(new Node(Integer.parseInt(data[0]), data[1], head), data[1]);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
		
		//System.out.println(temp.famStr());
		System.out.println("" + temp.sumUpDaSizes());
		System.out.println(temp.famStr());
		System.out.println(temp.sumUpDaSizesP2000());
		System.out.println(30000000-(70000000-48008081));
		//inputMuneebTransform(Master input)
		System.out.println(temp.findTHEFUCKINGDIR(48008081));
		

	}

}
