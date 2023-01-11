package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day12 {

    static Step[][] steps = new Step[41][64];
    public static void main(String args[]){
        // 41 rows, 64 columns
        
        int lineCount = 0;
        // read in data
		try {
			File f = new File("src/day12/d12input.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String data[] = s.nextLine().trim().split(""); // split on whitespace
                for(int i = 0; i<data.length;i++){
                    if(data[i].charAt(0) == 'S')
                        steps[lineCount][i] = new Step(0,lineCount,i);
                    else if (data[i].charAt(0) == 'E')
                        steps[lineCount][i] = new Step(27,lineCount,i);
                    else
                        steps[lineCount][i] = new Step((int)(data[i].charAt(0)) - 96,lineCount,i);
                    System.out.print(String.format("%2d",steps[lineCount][i].getHeight()));
                }
                System.out.println();
				lineCount++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("couldnt load file.\n");
			e.printStackTrace();
		}

        // parse data into subsets, each subset containing duplicate touching elements or island elements, ie. 1s touching, 

        Blob first = new Blob();

        //first = getBlob(20,0,first);
        System.out.println("blob2in");
        first = getBlob2(20,0,first);

        HashSet<Blob> allBlobs = new HashSet<Blob>();

        allBlobs.add(first);
            
        //System.out.println(numBlobs);
        System.out.println("setpathin");
        

        Step start = steps[20][0];
        findEnd(start, steps[20][40]);

        System.out.println("\n\n");
        for(int i =0; i<41; i++){
            for(int j = 0; j<64; j++){
                String str = "  ";
                if(steps[i][j].isSig()){
                    str = String.format("%2d", steps[i][j].getHeight());
                    
                }
                System.out.print(str);
            }
            System.out.println();
        }

        for (Step sig : first.getSigSteps()) {
                System.out.println(sig.getNexts());
        }
        System.out.println(steps[20][0].getSigDist(steps[20][40]));
        // put subsets into a tree, such that loops are dissalowed, if end of tree is a subset of 27 don't add branch
        // this is to eliminate large patches of 1s, 2s, 3s, and avoid stack overflow for larger datasets


        
    }

    // finds fastest path between each sigstep in b and each sigstep in each neighboring blob
    // currently stack overflowing, change so that it sets the path number relative to [20][0],
    // then the only key value pairs needing to be stored are in [20][0] instead of in each sigStep.
    public static void setPths(Step s){
        for(Blob nbr : s.getParent().getNbrs()){
            for(Step nxt : nbr.getSigSteps()){
                s.addNextSig(nxt, getFastest(s, nxt, new HashSet<Step>()));
                 // fastest path from s to nxt
            }
        }
        for(Blob nbr: s.getParent().getNbrs()){
            for(Step nxt : nbr.getSigSteps()){
                setPths(nxt);
            }
        }
    }

    public static int findEnd(Step s, Step goal){
        HashSet<Step> temp = new HashSet<Step>();
        temp.add(s);
        while(!s.getKeys().contains(goal)){
            for(Step curr : s.getKeys()){
                int x=100000000;
                for(Blob nbr : curr.getParent().getNbrs()){
                    for(Step nxt : nbr.getSigSteps()){
                        x = getFastest(curr, nxt, temp);
                        if(s.getKeys().contains(nxt)){
                            if(s.getSigDist(nxt)>x);
                            else s.addNextSig(nxt, x);
                        }
                    }
                    temp.addAll(nbr);
                }
                temp.addAll(curr.getParent());
            }
            for (Step step : s.getKeys()) {
                if(step.getCol()==goal.getCol() && step.getRow() == goal.getRow())
                    return step.getSigDist(step);
            }
        }
        return s.getSigDist(goal);
    }

    public static int getFastest(Step s, Step goal, HashSet<Step> temp){
        
        if(!temp.add(s))
            return 10000; // found loop
        int l = 10001;
        int r= 10002;
        int d =10003;
        int u=10004;
        if(s.getCol()==goal.getCol()&& s.getRow()== goal.getRow()){
            return 0;
        } 

        if(s.getRow()>0){
            if(s.isLegal(steps[s.getRow()-1][s.getCol()]) && steps[s.getRow()-1][s.getCol()].getParent() == goal.getParent()){
                u = 1 + getFastest(steps[s.getRow()-1][s.getCol()], goal, temp);
            }
        }
        if(s.getRow()<40){
            if(s.isLegal(steps[s.getRow()+1][s.getCol()]) && steps[s.getRow()+1][s.getCol()].getParent() == goal.getParent()){
                d = 1 + getFastest(steps[s.getRow()+1][s.getCol()], goal, temp);
            }
        }
        if(s.getCol()>0){
            if(s.isLegal(steps[s.getRow()][s.getCol()-1]) && steps[s.getRow()][s.getCol()-1].getParent() == goal.getParent()){
                l = 1 + getFastest(steps[s.getRow()][s.getCol()-1], goal, temp);
            }
        }
        if(s.getCol()<63){
            if(s.isLegal(steps[s.getRow()][s.getCol()+1]) && steps[s.getRow()][s.getCol()+1].getParent() == goal.getParent()){
                r = 1 + getFastest(steps[s.getRow()][s.getCol()+1], goal, temp);
            }
        }
        
        if(l<=r && l<=d && l<=u)
            return l;
        if(r<=d && r<= u)
            return r;
        if(d<=u)
            return d;
        return u;
    }

    public static HashSet<Step> getBigSet(HashSet<Step> path, Blob temp, Step goal){
        if(path.contains(goal))
            return path;
        for (Blob b : temp.getNbrs()) {
            path.addAll(b);
            if(path.contains(goal))
                return path;
            path.addAll(getBigSet(path, b, goal));
            if(path.contains(goal))
                return path;
        }
        return path;
    }

    /*
     * Adjust so that It only creates new blobs for valid moves...? 
     */
    public static Blob getBlob2(int r, int c, Blob temp){
        int height = steps[r][c].getHeight();
        steps[r][c].setParent(temp);
        //if(height == 0) height = 1;
        if(temp.add(steps[r][c])){
            // check above
            if(r>0){
                if(steps[r-1][c].getHeight() == height){
                    temp.addAll(getBlob2(r-1, c,temp));
                }
                    
                else if(!steps[r-1][c].hasParent()){
                    if(steps[r][c].isLegal(steps[r-1][c])){
                        temp.addNbr(getBlob2(r-1, c, new Blob()));
                        steps[r][c].setSig();
                    }
                }
                else{
                    if(steps[r][c].isLegal(steps[r-1][c])){
                        steps[r][c].setSig();
                        temp.addNbr(steps[r-1][c].getParent());
                        // add to nbrs
                    }
                        
                }
                    
            }
            // check under
            if(r<40){
                if(steps[r+1][c].getHeight() == height){
                    temp.addAll(getBlob2(r+1, c,temp));
                }
                    
                else if(!steps[r+1][c].hasParent()){
                    if(steps[r][c].isLegal(steps[r+1][c])){
                        temp.addNbr(getBlob2(r+1, c, new Blob()));
                        steps[r][c].setSig();
                    }
                }
                else{
                    if(steps[r][c].isLegal(steps[r+1][c])){
                        steps[r][c].setSig();
                        temp.addNbr(steps[r+1][c].getParent());
                    }
                        
                }
            }
            // check left
            
            if(c>0){
                if(steps[r][c-1].getHeight() == height){
                    temp.addAll(getBlob2(r, c-1,temp));
                }
                    
                else if(!steps[r][c-1].hasParent()){
                    if(steps[r][c].isLegal(steps[r][c-1])){
                        temp.addNbr(getBlob2(r, c-1, new Blob()));
                        steps[r][c].setSig();
                    }
                }
                else{
                    if(steps[r][c].isLegal(steps[r][c-1])){
                        steps[r][c].setSig();
                        temp.addNbr(steps[r][c-1].getParent());
                    }
                        
                }
            }
            
            // check right
            if(c<63){
                if(steps[r][c+1].getHeight() == height){
                    temp.addAll(getBlob2(r, c+1,temp));
                }
                    
                else if(!steps[r][c+1].hasParent()){
                    if(steps[r][c].isLegal(steps[r][c+1])){
                        temp.addNbr(getBlob2(r, c+1, new Blob()));
                        steps[r][c].setSig();
                    }
                }
                else{
                    if(steps[r][c].isLegal(steps[r][c+1])){
                        steps[r][c].setSig();
                        temp.addNbr(steps[r][c+1].getParent());
                    }
                        
                }
            }
        }
        return temp;
    }
    // puts the data into a Blob, containing all neighboring steps of equal height, expanding outward
    // until it reaches the edges
    public static Blob getBlob(int r, int c,Blob temp){
        if(steps[r][c].getParent() == null){
            Blob par = new Blob();
            steps[r][c].setParent(par);
            steps[r][c].getParent().addNbr(temp);
            temp = par;
        }
        int height = steps[r][c].getHeight();
        // to test first loc
        //if(height == 0) height =1;
        // check if already in set, if not already in set, dive deeper
        steps[r][c].setSeen();
        if(temp.add(steps[r][c])){
            // check above
            if(r>0){
                if(steps[r-1][c].getHeight() == height){
                    steps[r-1][c].setParent(temp);
                    temp.addAll(getBlob(r-1, c,temp));
                }
                    
                else if(!steps[r-1][c].isSeen()){
                    temp.addNbr(getBlob(r-1, c, temp));
                }
                    
            }
            // check under
            if(r<40){
                if(steps[r+1][c].getHeight() == height){
                    steps[r+1][c].setParent(temp);
                    temp.addAll(getBlob(r+1, c, temp));
                }
                else if(!steps[r+1][c].isSeen()){
                        temp.addNbr(getBlob(r+1, c, temp));
                }
            }
            // check left
            if(c>0){
                if(steps[r][c-1].getHeight() == height){
                    steps[r][c-1].setParent(temp);
                    temp.addAll(getBlob(r, c-1, temp));
                }
                else if(!steps[r][c-1].isSeen()){
                        temp.addNbr(getBlob(r, c-1, temp));
                }
            }
            // check right
            if(c<63){
                if(steps[r][c+1].getHeight() == height){
                    steps[r][c+1].setParent(temp);
                    temp.addAll(getBlob(r, c+1, temp));
                }
                else if(!steps[r][c+1].isSeen()){
                        temp.addNbr(getBlob(r, c+1, temp));
                    }
            }
        }
        return temp;
    }

}

