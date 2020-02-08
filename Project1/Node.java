/*
 * A class to define the node
 * object.
 * @author Prabhat Bhootra
 * @version 1.00 02/27/2019
 */

public class Node{
	String state;
	Node Parent;
	String action;
	int pathCost;
	public Node(String s, Node p, String a, int pc){
		state = s;
		Parent = p;
		action = a;
		pathCost = pc;
	}
	public void setParent(Node n1){
		Parent = n1;
	}
	public void setAction(String act){
		action = act;
	}
	public void setPathCost(int path){
		pathCost = path;
	}
}