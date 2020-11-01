/* Ru Ferguson
 * 27 October 2020
 * 
 * This class creates Node objects which each represent one node in the prediction suffix tree.
 */

import java.util.ArrayList;

public class Node<T> {
	
	ArrayList<T> tokenSequence; // the sequence at this node
	ArrayList<Node> children; // an array of the child nodes
	int count; // count – the number of times the node appears in the input, initially set to 1
	
	Node() {
		children = new ArrayList<Node>();
		tokenSequence = new ArrayList<T>();
		count = 1;
	}
	
	Node(ArrayList<T> curSequence) {
		children = new ArrayList<Node>();
		tokenSequence = curSequence;
		count = 1;
	}
	
	ArrayList<T> getTokenSeq() {
		return tokenSequence;
	}
	
	ArrayList<Node> getChildren() {
		return children;
	}
	
	
	// Adds a child node. Will only add a child node if the input node contains this node as a suffix.
	boolean addNode(Node node) {
		boolean found = false; // whether the node has been added or not yet
		
		if ((node.getTokenSeq()).equals(tokenSequence)) {	// the tokenSequence of this node is the same as the token sequence of the added node
			found = true;
			count++; // add one to count 
		} else if(amIASuffix(node) || (tokenSequence.size() == 0)) { 
			// try to add the node to all the children nodes
			for (int i = 0; i < children.size(); i++) {
				if ((children.get(i)).amIASuffix(node)) {
					(children.get(i)).addNode(node);
					found = true;
				}
			}
			// Did one your child nodes add the node?
			if (!found && (node.getTokenSeq()).size() - 1 == tokenSequence.size()) { //	 If NOT found and the length of node’s tokenSequence is one less than this tokenSequence
				children.add(node);	//	Add the node to our children array.
				found = true;
			}
		}
		return found;
	}
	
	
	// prints the tokenSequence, then prints its children
	void print() {
		System.out.println(tokenSequence);	// print the token sequence
		for (int i = 0; i < children.size(); i ++) {	// for each node in the children
			(children.get(i)).print(1);	//see the next function
		}
	}
	
	//this will produce nicely formatted trees that make sense
	void print(int numSpacesBefore) {
		for (int i = 1; i < numSpacesBefore; i++) {	//for 1 to numSpacesBefore
			System.out.print("   "); 
		}
		System.out.print("  ––> "); //print an arrow (-->)
		System.out.println(this.getTokenSeq());	// print the token
		for (int i = 0; i < children.size(); i++) {	//for each node in the children
			// each time you call this from the next child the number of spaces will increase by 1
			(children.get(i)).print(numSpacesBefore + 1);
		}
	}
	
	
	// determines whether the tokenSequence of this node is a suffix of the tokenSequence of the input node
	boolean amIASuffix(Node node) {	
		ArrayList<T> input = node.getTokenSeq();
		boolean isSuffix = false;
		if (tokenSequence.isEmpty()) {	// empty string is suffix of everything
			isSuffix = true;
		} else {
			for (int i = 0; i < input.size(); i++) {
				ArrayList<T> checkSublist = new ArrayList<T>(input.subList(i, input.size()));	//curSequence = find the current sequence of size i		

				int inputIsInTokenSeq = tokenSequence.indexOf(checkSublist); // find checkSublist in tokenSequence			
				if (inputIsInTokenSeq != -1) {
					isSuffix = true;
				} else if (checkSublist.equals(tokenSequence)) { // are they the same?
					isSuffix = true;
				}
			}
		}
		return isSuffix;
	}
	
	
	//  – performs elimination based on an empirical probability threshold PMin.
	// Returns whether to delete this node or not. The parent node performs the deletion.
	boolean pMinElimination(int totalTokens, double pMin) {
		// 1. find the number of times that the sequence could have occurred ( dependent on tokenSequence.size() )
		double empProb = (double) this.count / (totalTokens - (tokenSequence.size() - 1));
		//System.out.println(this.count + "   "  + totalTokens + "   " + empProb + "   this is: " + this.getTokenSeq());

		// 2. shouldRemove = empirical probability of the token sequence < pMin (note: handle the empty sequence / root )
		boolean shouldRemove = (empProb < pMin) && (!this.getTokenSeq().isEmpty());
		//System.out.println((empProb < pMin) + "   "  + (!this.getTokenSeq().isEmpty()) + "   ");

		if (!shouldRemove) {	// 3. if we should NOT remove this node
			for (int i = children.size() - 1; i >= 0; i--) {	//for each node (start from the end & go to the front of each array):
				boolean shouldRemoveChild = (children.get(i)).pMinElimination(totalTokens, pMin); //call pMinElimination on all the children nodes
				//System.out.println("child: " + shouldRemoveChild);
				if (shouldRemoveChild) {	//if they return true (ie, we should remove the node) {
					children.remove(i);	//then remove the entire node (which incl. its children)
					//you may use the ArrayList method .remove()
				}	
			}
		}
		return shouldRemove;	//4. return shouldRemove
	}
	 
	// Note: Another implementation strategy would be to determine the number of times that
	// the sequence could have occurred by subtracting from the totalTokens parameter when
	// sending to children rather than calculating it separately in each node. (Similar to
	// the print(int beforeSpaces) algorithm). This has the advantage of requiring less
	// operations but it is not required.  
}
