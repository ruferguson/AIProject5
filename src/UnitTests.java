/* Ru Ferguson
 * 12 October 2020
 * 
 * This class is used for the unit test methods to consolidate code more nicely. */

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;

public class UnitTests extends PApplet {
	
	MelodyPlayer player; //play a midi sequence
	MidiFileToNotes midiNotes; // read a midi file
	
	Tree<Integer> pitchTree;
	Tree<Character> charTree;
	
	
	UnitTests() {
		String filePath = getPath("mid/MaryHadALittleLamb.mid");
		midiNotes = new MidiFileToNotes(filePath);
		midiNotes.setWhichLine(0);
		pitchTree = new Tree<Integer>();
		charTree = new Tree<Character>();
	}
	/*	1.Suffix tree output for:  abracadabra
		2.Suffix tree output for:  acadaacbda
		3.Suffix tree output for:  abcccdaadcdaabcadad
		4. Suffix tree output for: Mary Had a Little Lamb (the midi file -- only the pitches are required)  */
	
	void P5UnitTest1() {	// Project 5: Unit Test 1
		// 1. Suffix tree output for:  abracadabra
		Character[] myList = {'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a'};
		ArrayList<Character> testList = new ArrayList(Arrays.asList(myList));
		charTree.train(testList);
		charTree.print();
	}
	
	void P5UnitTest2() {	// Project 5: Unit Test 2
		// 2. Suffix tree output for:  acadaacbda
		Character[] myList = {'a', 'c', 'a', 'd', 'a', 'a', 'c', 'b', 'd', 'a'};
		ArrayList<Character> testList = new ArrayList(Arrays.asList(myList));
		charTree.train(testList);
		charTree.print();
	}
	
	void P5UnitTest3() {	// Project 5: Unit Test 3
		// 3. Suffix tree output for:  abcccdaadcdaabcadad
		Character[] myList = {'a', 'b', 'c', 'c', 'c', 'd', 'a', 'a', 'd', 'c', 'd', 'a', 'a', 'b', 'c', 'a', 'd', 'a', 'd'};
		ArrayList<Character> testList = new ArrayList(Arrays.asList(myList));
		charTree.train(testList);
		charTree.print();
	}
	
	void P5UnitTest4() {	// Project 5: Unit Test 4
		// 4. Suffix tree output for: Mary Had a Little Lamb (the midi file -- only the pitches are required)
		pitchTree.train(midiNotes.getPitchArray());
		pitchTree.print();
	}

	void trainP5() {

	}

	void newSong() {
		ArrayList<Integer> newSongPitches = new ArrayList<Integer>();
		ArrayList<Double> newSongRhythms = new ArrayList<Double>();	
	}
	
	void makeSpace(int i) {
		for (int k = 0; k < i; k++) {
			System.out.print("    ");
		}
	}
	
	String getPath(String path) {
		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

}

