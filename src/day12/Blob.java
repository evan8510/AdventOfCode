package day12;

import java.util.HashSet;

public class Blob extends HashSet<Step>{

    private HashSet<Blob> nbrs;
    private HashSet<Step> sigs;
    public Blob(){
        nbrs = new HashSet<Blob>();
        sigs = new HashSet<Step>();
    }

    public void addNbr(Blob nbr){
        nbrs.add(nbr);
    }

    public boolean isNbr(Blob nbr){
        return nbrs.contains(nbr);
    }
    public HashSet<Blob> getNbrs(){
        return nbrs;
    }

    // recurse this
    public HashSet<Step> getAnyPath(){
        HashSet<Step> temp = new HashSet<Step>();
        temp.addAll(this);
        if(this.size()==1){
            for (Step s : this) {
                if(s.getHeight()==27)
                    return temp;
            }
        }
        for (Blob n : nbrs) {
            temp.addAll(n.getAnyPath());
            for (Step step : temp) {
                if(step.getHeight()==27) return temp;
            }
        }
        
        return temp;
    }

    public HashSet<Step> getSigSteps(){
        
        for (Step step : this) {
            if(step.isSig()){
                sigs.add(step);
            }
        }
        return sigs;
    }


}
