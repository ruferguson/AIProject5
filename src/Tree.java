import java.util.ArrayList;
import java.util.List;

/* Ru Ferguson
 * 22 October 2020
 * 
 */

public class Tree<T> { 
	/* Tree: holds the root node and interacts with the tree via the root node. The tree does not
	   perform actions of adding nodes or traversing the tree beyond its calls to the root node. */
	
	Node<T> root; // Node root -- the root of the tree
	int L; // L – maximum token sequence length (or order length) –> chosen by artist/composer/engineer
	Node<T> newNode;
	
	Tree() {
		root = new Node<T>();
		L = 3;
	}
		
	// creates the unique token sequences from the input & using the root, adds them to the tree
	void train(ArrayList<T> input) {
		for(int i = 1; i <= L ; i++) { // i = order #
			for(int j = 0; j < input.size() - (i - 1); j++) { // j = index into input
				ArrayList<T> curSequence = new ArrayList<T>(input.subList(j, j + i));	//curSequence = find the current sequence of size i			
				newNode = new Node<T>(curSequence); //create a new node with the current sequence
				root.addNode(newNode);
				//System.out.println("children: " + root.getChildren());
			}
		}
	}

	// calls the root to print
	void print() {
		root.print();
	}
	
}
