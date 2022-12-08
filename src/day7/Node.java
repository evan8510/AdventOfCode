package day7;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private int size;
	private String name;
	private Node parent;

	private Map<String, Node> children; // map of children, key is name of child

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

	public String toString() {
		return name + "" + size;
	}

	public void addChildren(Node c, String name) {
		children.put(name, c);
	}

	public Node getChild(String name) {
		return children.get(name);
	}

	public Node getParent() {
		return parent;
	}

	public String printAll() {
		String str = "\nName: " + name + "\tSize:" + size;
		if (children.isEmpty())
			return str;
		str += ("\n======Children of " + name + " Below======");
		for (Node n : children.values()) {
			str += n.printAll();
		}
		return str;
	}

	// move through the tree and set the size of all directories
	public int evalSizes() {
		if (children.isEmpty())
			return size;
		for (Node n : children.values()) {
			size += n.evalSizes();
		}
		return size;
	}

	// add size of all directories with size <=100000 together and return
	public int sumSub100000() {
		int x = 0;
		if (size < 100001) {
			x += size;
		}
		if (children.isEmpty())
			return 0;
		for (Node n : children.values()) {
			x += n.sumSub100000();
		}
		return x;
	}

	// return the smallest dir that is still greater than 8008081
	public int findDir(int smallest) {
		if (size < 8008081) {
			return smallest;
		}
		smallest = size;
		for (Node n : children.values()) {
			if (smallest >= n.findDir(smallest)) {
				smallest = n.findDir(smallest);
			}

		}
		return smallest;
	}

}
