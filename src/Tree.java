import java.util.ArrayList;

/* Ru Ferguson
 * 22 October 2020
 * 
 */

public class Tree<T> { 
	/* Tree: holds the root node and interacts with the tree via the root node. The tree does not
	   perform actions of adding nodes or traversing the tree beyond its calls to the root node. */
	
	// Data:
	Node<T> root; // Node root -- the root of the tree
	int L; // L – maximum token sequence length (or order length) –> chosen by artist/composer/engineer
	
	Tree() {
		
	}

	//Methods:
		
	//void train() – creates the unique token sequences from the input & using the root, adds
	//them to the tree
	void train(ArrayList<T> input) {
		for(int i = 1; i<=L ; i++) {
			for(int j = 0; j < input.size()-(i-1); j++) {
				//curSequence = find the current sequence of size i
				//create a new node with the current sequence, theNewNode root.addNode(theNewNode)
			}
		}
	}

	// void print() – calls the root to print
	void print() {
		root.print();
	}
	
}
