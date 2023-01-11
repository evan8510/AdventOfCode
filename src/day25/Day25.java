package day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day25 {
    public static void main(String args[]){
        int[] snafu = new int[30];
        for(int i =0;i<snafu.length;i++){
            snafu[i]=0;
        }
        try {
			File f = new File("src/day25/d25input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String[] data = s.nextLine().split("");
                int temp=0;
                for(int i=0; i<data.length;i++){
                    if(data[i].charAt(0)=='-'){
                        temp =-1;
                    }
                    else if(data[i].charAt(0)=='='){
                        temp =-2;
                    }
                    else{
                        temp = Integer.parseInt(data[i]);
                    }
                    snafu[data.length-i-1] += temp;
                }
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}
        System.out.println("Snafu input is: " + toSnafu(snafu));
    }
    public static String toSnafu(int[] snafu){
        boolean valid = false;
        while(!valid){
            valid = true;
            for(int i=0;i<snafu.length;i++){
                if(snafu[i]>5){
                    valid = false;
                    snafu[i+1] += snafu[i]/5;
                    snafu[i] %=5;
                }
                if(snafu[i]>2){
                    valid = false;
                    snafu[i+1] +=1;
                    snafu[i]-=5;
                }
                if(snafu[i]<-2){
                    valid = false;
                    snafu[i]+=5;
                    snafu[i+1]-=1;
                }
            }
        }
        String temp = "";
        boolean rdy = false;
        for(int i=snafu.length-1;i>=0;i--){
            if(!rdy){
                rdy = snafu[i]!=0;
            }
            if(rdy){
                if(snafu[i]==-2)
                    temp += "=";
                else if(snafu[i]==-1)
                    temp += "-";
                else
                    temp += snafu[i];
            }
        }
        return temp;
    }  
}
