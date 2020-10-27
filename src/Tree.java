/* Ru Ferguson
 * 27 October 2020
 * 
 * This class holds the root node and interacts with the tree via the root node. The tree does not
 * perform actions of adding nodes or traversing the tree beyond its calls to the root node. */

import java.util.ArrayList;
import java.util.List;

public class Tree<T> { 
	
	Node<T> root; // the root of the tree
	int L; // maximum token sequence length (or order length) –> chosen by artist/composer/engineer
	Node<T> newNode;
	
	Tree() {
		root = new Node<T>();
		L = 3; // 3 is used for unit tests
	}
	
	Tree(int i) {
		root = new Node<T>();
		L = i; // input parameter chooses L
	}
		
	// creates the unique token sequences from the input & using the root, adds them to the tree
	void train(ArrayList<T> input) {
		for(int i = 1; i <= L ; i++) { // i = order #
			for(int j = 0; j < input.size() - (i - 1); j++) { // j = index into input
				ArrayList<T> curSequence = new ArrayList<T>(input.subList(j, j + i));	// curSequence = find the current sequence of size i			
				newNode = new Node<T>(curSequence); // create a new node with the current sequence
				root.addNode(newNode);
			}
		}
	}

	// calls the root to print
	void print() {
		root.print();
	}
	
}
