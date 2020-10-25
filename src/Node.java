import java.util.ArrayList;

/* Ru Ferguson
 * 22 October 2020
 * 
 */

public class Node<T> {

	/*  Node: one node in the prediction suffix tree
		Data:
			tokenSequence – the sequence at this node
			ArrayList<Node> children – an array of the child nodes
		Methods –
			addNode(Node node) – Adds a child node. Will only add a child node if the input node contains this node as a suffix.
			print() – prints the tokenSequence, then prints its children
			boolean amIaSuffix(Node node) – is the given node’s tokenSequence a suffix of the input sequence? */
	ArrayList<T> tokenSequence;
	ArrayList<Node> children; // an array of the child nodes
	
	Node() {
		children = new ArrayList<Node>();
		tokenSequence = new ArrayList<T>();
	}
	
	boolean addNode(Node node) {
		boolean found = false; // whether the node has been added or not yet
		
		if (node.equals(tokenSequence)) {	// the tokenSequence of this node is the same as the token sequence of the added node
		//found = true
		//do NOTHING else. You will do things here in the future. But not for now;
		} else if(amIASuffix(node) || (tokenSequence.size() == 0)) { //note that the empty sequence is always a suffix!
	
		//1. try to add the node to all the children nodes.
		//2. Did one your child nodes add the node? **keep track of this via the found variable**
			if (!found && children.size() < tokenSequence.size()) {
				children.add(node);
				found = true;
			}
		}
		return found;
	}
	
	
	void print() {
		
	}
	/* 
	Node::print() {
	If NOT found and the length of node’s tokenSequence is one less than this tokenSequence Add the node to our children array. Thus- found=true.
	children.add(node);
	print the token sequence
	for each node in the children {
	node.print(1) //see the next function }
	}
	//this will produce nicely formatted trees that make sense Node::print(int numSpacesBefore)
	{
	for 1 to numSpacesBefore
	print(“ “) //note: don’t use println because you don’t want a line
	
	print an arrow (-->) //if you like, it will be clearer print the token
	for each node in the children {
	//each time you call this from the next child the number of spaces will increase by 1
	node.print(numSpacesBefore + 1); }
	}
 */
	
	
	boolean amIASuffix(Node node) {	// determines whether the tokenSequence of this node is a suffix of the tokenSequence of the input node
	
	// empty string is suffix of everything
	//Hint #1: using the sublist() method makes this much easier.
	//Hint #2: note the difference between .equals() and ==.
	//Hint #3: You MUST test this separately to make sure it works. That means calling it temporarily from the main class to make sure it works.
	//Nothing in your Node adding will work if this is incorrect and you cannot simply
	//assume it is correct if you haven’t tested it.
	}
	
}
