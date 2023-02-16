package day12;

public class Node{
    private Node left, right, up, down;
    private int dist, height;

    public Node(int h){
        left = null;
        right = null;
        up = null;
        down = null;
        dist = 5;
        height = h;
    }

    public void setLeft(Node n){
        left = n;
    }
    public void setRight(Node n){
        right = n;
    }
    public void setUp(Node n){
        up = n;
    }
    public void setDown(Node n){
        down = n;
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    public Node getUp(){
        return up;
    }
    public Node getDown(){
        return down;
    }
    public int getDist(){
        return dist;
    }

    public void expand(){
        if(isLegal(left)) left.setDist(dist+1);
        if(isLegal(right)) right.setDist(dist+1);
        if(isLegal(up)) up.setDist(dist+1);
        if(isLegal(down)) down.setDist(dist+1);
    }

    public boolean isLegal(Node n){
        if(n==null) return false;
        return (n.getHeight()) <= height+1;
    }

    public boolean isEnd(){
        return (height ==27);
    }

    public int getHeight(){
        return height;
    }

    public boolean setDist(int d){
        if(dist>d){
            dist = d;
            return true;
        }
        return false;
    }

    
}
