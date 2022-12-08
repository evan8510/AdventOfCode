import java.util.HashMap;
import java.util.Map;

public class Node {
	
	private int size;
	private String name;
	private Node parent;
	
	private Map<String, Node> children;
	
	public Node(int s, String n, Node p) {
		size = s;
		name = n;
		parent = p;
		children = new HashMap<String, Node>();
	}
	
	public int getSize() {
		return size;
	}
	
	public String getName() {
		return name;
	}
	
	public void setParent(Node p) {
		parent = p;
	}
	
	public String xddd() {
		return name + "" + size;
	}
	
	public void addChildren(Node c, String name) {
		//System.out.println("added " + name);
		children.put(name, c);
	}
	
	public Node pickUpSonFromDayCare(String name) {
		return children.get(name);
	}
	
	public Node callMom() {
		return parent;
	}
	
	public String famStr() {
		String str = "\nName: " + name + "\tSize:" + size;
		if(children.isEmpty())
			return str;
		str+= "\n======THESE ARE MY JITS YAHHHHHHH BOYYYYY======";
		for(Node n : children.values()) {
			str+= n.famStr();
		}
		return str;
	}
	
	public int sumUpDaSizes() {
		if(children.isEmpty())
			return size;
		for(Node n : children.values()) {
			size+= n.sumUpDaSizes();
		}
		return size;
	}
	
	public int sumUpDaSizesP2000() {
		int x =0;
		if(size<100001) {
			x+=size;
		}
		if(children.isEmpty())
			return 0;
		for(Node n : children.values()) {
			x+=n.sumUpDaSizesP2000();
		}
		return x;
	}
	
	// not 9725484 or 14629551
	// not 9725484
	// not 9725484
	
	public int findTHEFUCKINGDIR(int smallest) {
		if(size<8008081) {
			System.out.println("SIZE TOO small " +  size);
			return smallest;
		}
		
		smallest = size;
		for(Node n : children.values()) {
			if(smallest>= n.findTHEFUCKINGDIR(smallest)) {
				smallest = n.findTHEFUCKINGDIR(smallest);
			}
			
		}
		return smallest;
	}
	
	
}
