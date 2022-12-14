package day11;

import java.util.LinkedList;

public class Monkey2 {

	private LinkedList<Long> items;
	
	private Monkey2 monkT, monkF;
	private boolean operator; // true if multiply, false if adding
	private long divisor;
	private long insp;
	private String var;
	static long multiple = 11*7*13*5*3*17*2*19;
	
	public Monkey2() {
		items = null;
		insp = 0;
		monkT = null;
		monkF = null;
		var = "";
		operator = false;
		divisor = 0;
	}
	
	public void setItems(LinkedList<Long> i) {
		items = new LinkedList<Long>(i);
	}
	
	public void setReceiverT(Monkey2 m) {
		monkT = m;
	}
	
	public void setReceiverF(Monkey2 m) {
		monkF = m;
	}
	
	public void setDivisor(long div) {
		divisor = div;
	}
	public void setOperation(char c, String str) {
		operator = (c=='*');
		var = str;
	}
	public boolean operation() {
		long old = items.remove();
		long n = 0;
		if(operator) {
			if(var.equals("old"))  
				n = (old*old);
			else
				n = (old*(Long.parseLong(var)));
		}
		else {
			if(var.equals("old"))  
				n = (old+old);
			else
				n = (old+Long.parseLong(var));
		}
		n%=multiple;
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
	
	public long getSum() {
		return insp;
	}
	
	public void receive(long i) {
		items.add(i);
	}
}