package day12;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Step {

    private int height;
    private boolean seen;
    private Step left, up, right, down;
    private int r,c;
    private Blob parent;
    private boolean sig;
    private HashMap<Step,Integer> nextSigs;
    public Step(int h, int row, int column){
        height = h;
        r = row;
        c = column;
        left = null;
        up = null;
        right = null;
        down = null;
        seen = false;
        sig = false;
        parent = new Blob();
    }

    public int getHeight(){
        return height;
    }

    public int getRow(){
        return r;
    }
    public int getCol(){
        return c;
    }
    public void setLeft(Step l){
        left = l;
    }
    public void setUp(Step u){
        up = u;
    }
    public void setRight(Step r){
        right = r;
    }
    public void setDown(Step d){
        down = d;
    }

    public Step getLeft(){
        return left;
    }
    public Step getUp(){
        return up;
    }
    public Step getRight(){
        return right;
    }
    public Step getDown(){
        return down;
    }
    public void setSeen(){
        seen = true;
    }
    public boolean isSeen(){
        return seen;
    }
    public void setSig(){
        nextSigs = new HashMap<Step,Integer>();
        sig = true;
    }
    public boolean isSig(){
        return sig;
    }
    public Blob getParent(){
        return parent;
    }
    public void setParent(Blob par){
        parent = par;
    }
    public boolean hasParent(){
        return parent.contains(this);
    }
    public boolean isLegal(Step s){
        int h = height;
        
        if(h==0) h =1;
        
        return (s.getHeight()<=h+1);
    }

    public int getSigDist(Step s){
        int x = 100000000;
        if(nextSigs.containsKey(s))
            return nextSigs.get(s);
        return x;
    }

    public HashMap<Step,Integer> getNexts(){
        return nextSigs;
    }
    public HashSet<Step> getKeys(){
        HashSet<Step> temp = new HashSet<Step>(nextSigs.keySet());
        return temp;
    }
    public void addNextSig(Step s, int x){
        if(sig){
            if(nextSigs.containsKey(s)){
                if(s.getSigDist(this)<x){
                    nextSigs.put(s, s.getSigDist(this));
                    return;
                }
                if(nextSigs.get(s)>x){
                    nextSigs.put(s, x);
                }
                return;
            }
            else{
                nextSigs.put(s, x);
                return;
            }
        }
    }

    public String toString(){
        return String.format("row: %d column: %d ", r,c);

    }
    
    
}
