import java.util.ArrayList;

/* Ru Ferguson
 * 22 October 2020
 * 
 */

public class Node<T> {

	/*  Node: one node in the prediction suffix tree */
	
	ArrayList<T> tokenSequence; // tokenSequence – the sequence at this node
	ArrayList<Node> children; // an array of the child nodes
	
	Node() {
		children = new ArrayList<Node>();
		tokenSequence = new ArrayList<T>();
	}
	
	Node(ArrayList<T> curSequence) {
		children = new ArrayList<Node>();
		tokenSequence = curSequence;
	}
	
	ArrayList<T> getTokenSeq() {
		return tokenSequence;
	}
	
	ArrayList<Node> getChildren() {
		return children;
	}
	
	//	addNode(Node node) – Adds a child node. Will only add a child node if the input node contains this node as a suffix.
	boolean addNode(Node node) {
		boolean found = false; // whether the node has been added or not yet
		
		System.out.println("node token seq: " + node.getTokenSeq() + "   this token seq:" + tokenSequence + ".");

		if ((node.getTokenSeq()).equals(tokenSequence)) {	// the tokenSequence of this node is the same as the token sequence of the added node
			System.out.println("in here");
			found = true;
			//	do NOTHING else. You will do things here in the future. But not for now;
		} else if(amIASuffix(node) || (tokenSequence.size() == 0)) { //note that the empty sequence is always a suffix!
			//1. try to add the node to all the children nodes.
			children.add(node);
			//2. Did one your child nodes add the node? **keep track of this via the found variable**
			//	 If NOT found and the length of node’s tokenSequence is one less than this tokenSequence
			//   Add the node to our children array. Thus- found=true.
			if (!found && children.size() < tokenSequence.size()) {
				children.add(node);
				found = true;
			}
		}
		return found;
	}
	
	
	// print() – prints the tokenSequence, then prints its children
	void print() {
		// print the token sequence
		System.out.println(tokenSequence);
		for (int i = 0; i < children.size(); i ++) {	// for each node in the children {
			(children.get(i)).print(1); //see the next function
		}
	}
	
	//this will produce nicely formatted trees that make sense
	void print(int numSpacesBefore) {
		for (int i = 1; i < numSpacesBefore; i++) {	//for 1 to numSpacesBefore
			System.out.print(" "); //note: don’t use println because you don’t want a line
		}
		System.out.print("––>"); //print an arrow (-->) //if you like, it will be clearer print the token
		for (int i = 0; i < children.size(); i++) {	//for each node in the children {
			// each time you call this from the next child the number of spaces will increase by 1
			(children.get(i)).print(numSpacesBefore + 1);
		}
	}
	
	
	// boolean amIaSuffix(Node node) – is the given node’s tokenSequence a suffix of the input sequence?
	boolean amIASuffix(Node node) {	// determines whether the tokenSequence of this node is a suffix of the tokenSequence of the input node
		ArrayList<T> input = node.getTokenSeq();
		boolean isSuffix = false;
		if (tokenSequence.isEmpty()) {	// empty string is suffix of everything
			isSuffix = true;
			System.out.println("this was true");
		} else {
			for (int i = 0; i < input.size(); i++) {
				ArrayList<T> checkSublist = new ArrayList<T>(input.subList(i, input.size()));	//curSequence = find the current sequence of size i		
				System.out.println("sublist is: " + checkSublist);
				System.out.println("token seq is: " + tokenSequence);

				int inputIsInTokenSeq = tokenSequence.indexOf(checkSublist);	// find checkSublist in tokenSequence			
				if (inputIsInTokenSeq == -1) {
					System.out.println("found");
					isSuffix = true;
				}
			}
		}
	//Hint #1: using the sublist() method makes this much easier.
	//Hint #2: note the difference between .equals() and ==.
	//Hint #3: You MUST test this separately to make sure it works. That means calling it temporarily from the main class to make sure it works.
	//Nothing in your Node adding will work if this is incorrect and you cannot simply
	//assume it is correct if you haven’t tested it.
		return isSuffix;
	}
	
}
