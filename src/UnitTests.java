/* Ru Ferguson
 * 12 October 2020
 * 
 * This class is used for the unit test methods to consolidate code more nicely. */

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;

import processing.core.PApplet;

public class UnitTests extends PApplet {
	
	MelodyPlayer player; //play a midi sequence
	MidiFileToNotes midiNotes; // read a midi file
	
	Tree<Integer> pitchTree;
	
	
	UnitTests() {
		String filePath = getPath("mid/MaryHadALittleLamb.mid");
		midiNotes = new MidiFileToNotes(filePath);
		midiNotes.setWhichLine(0);
		pitchTree = new Tree<Integer>();

	}
	
	void P5UnitTest1() {	// Project 1: Unit Test 1
		pitchTree.train(midiNotes.getPitchArray());
		System.out.println("trained");
		pitchTree.print();
	}
	
	void P5UnitTest2() {	// Project 1: Unit Test 2
		
	}
	
	void P5UnitTest3() {	// Project 1: Unit Test 3
		
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

