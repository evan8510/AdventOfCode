package day11;

import java.util.LinkedList;

public class Monkey {

	private LinkedList<Integer> items;
	
	private Monkey monkT, monkF;
	private boolean operator; // true if multiply, false if adding
	private int divisor;
	private int insp;
	private String var;
	
	public Monkey() {
		items = null;
		insp = 0;
		monkT = null;
		monkF = null;
		var = "";
		operator = false;
		divisor = 0;
	}
	
	public void setItems(LinkedList<Integer> i) {
		items = new LinkedList<Integer>(i);
	}
	
	public void setReceiverT(Monkey m) {
		monkT = m;
	}
	
	public void setReceiverF(Monkey m) {
		monkF = m;
	}
	
	public void setDivisor(int div) {
		divisor = div;
	}
	public void setOperation(char c, String str) {
		operator = (c=='*');
		var = str;
	}
	public boolean operation() {
		int old = items.remove();
		int n = 0;
		if(operator) {
			if(var.equals("old"))  
				n = (old*old)/3;
			else
				n = (old*(Integer.parseInt(var)))/3;
		}
		else {
			if(var.equals("old"))  
				n = (old+old)/3;
			else
				n = (old+Integer.parseInt(var))/3;
		}
		
		items.addFirst(n);
		return (n%divisor)==0;
	}

	
	public void pass() {
		while(!items.isEmpty()) {
			insp +=1;
			if(operation()) 
				monkT.receive(items.remove());
			else 
				monkF.receive(items.remove());
		}
	}
	
	public int getSum() {
		return insp;
	}
	
	public void receive(int i) {
		items.add(i);
	}
}
