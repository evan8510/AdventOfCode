package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.SortedSet;

public class Day12 {
    public static void main(String args[]){
        // 41 rows, 64 columns
        Node[][] nodes = new Node[41][64];
        Node head = new Node(0);
        Node end = new Node(0);
        int lineCount = 0;
        // read in data
		try {
			File f = new File("src/day12/d12input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data[] = s.nextLine().trim().split(""); // split on whitespace
                for(int i = 0; i<data.length;i++){
                    if(data[i].charAt(0) == 'S'){
                        nodes[lineCount][i] = new Node(1);
                        head = nodes[lineCount][i];
                    }
                    else if (data[i].charAt(0) == 'E'){
                        nodes[lineCount][i] = new Node(27);
                        end = nodes[lineCount][i];
                    }
                    else
                        nodes[lineCount][i] = new Node((int)(data[i].charAt(0)) - 96);
                }
				lineCount++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
        for(int i=0; i< nodes.length; i++){
            for(int j =0; j<nodes[i].length;j++){
                System.out.print(String.format("%2d",nodes[i][j].getHeight()));
            }
            System.out.println();
        }
        // alloc dirs 
        for(int i=0; i< nodes.length; i++){
            for(int j =0; j<nodes[i].length;j++){
                if(i>0) nodes[i][j].setUp(nodes[i-1][j]);
                if(i<nodes.length-1) nodes[i][j].setDown(nodes[i+1][j]);
                if(j>0) nodes[i][j].setLeft(nodes[i][j-1]);
                if(j<nodes[i].length-1) nodes[i][j].setRight(nodes[i][j+1]);
            }
        }
        head.setDist(0);
        // prio q sort of set up where the prios are the dists
        HashSet<Node> set = new HashSet<Node>();
        for(int i=0; i< nodes.length; i++){
            for(int j =0; j<nodes[i].length;j++){
                set.add(nodes[i][j]);
            }
        }
        Node f = new Node(100);
        f.setDist(100000);
        while(set.size()>0){
            Node smallest = f;
            for(Node x : set){
                if(x.getDist() < smallest.getDist()){
                    smallest = x;
                }
            }
            if(! (set.contains(smallest)) )
                break;
        }

    }

}

