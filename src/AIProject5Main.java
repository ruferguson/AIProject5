/* Ru Ferguson
 * 22 October 2020
 * 
 */

import processing.core.*;

import java.util.*; 

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

//import javax.sound.midi.*;

//make sure this class name matches your file name, if not fix.
public class AIProject5Main extends PApplet {

	MelodyPlayer player; //play a midi sequence
	MidiFileToNotes midiNotes; //read a midi file
	UnitTests unitTest = new UnitTests(); // create unit tests
	
	
	String filePath;

	public static void main(String[] args) {
		PApplet.main("AIProject5Main"); 
	}

	//setting the window size to 300x300
	public void settings() {
		size(375, 500);
	}

	//doing all the setup stuff
	public void setup() {		
		// create my generators for pitch and rhythm
	
				
		// returns a url
		filePath = getPath("mid/Super_Mario_Bros_Theme.mid"); // use this for probabilistic generation

		midiNotes = new MidiFileToNotes(filePath); //creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
												   //be created with "new". Note how every object is a pointer or reference. Every. single. one.

	    // which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
		midiNotes.setWhichLine(0);

		player = new MelodyPlayer(this, 100.0f);
		
		player.setup();

		// play the midi notes as they are in the file
		player.setMelody(midiNotes.getPitchArray());
		player.setRhythm(midiNotes.getRhythmArray());
	}

	public void draw() {
	    player.play();		//play each note in the sequence -- the player will determine whether is time for a note onset
	    background(250);
	    showInstructions();
	}

	//this finds the absolute path of a file
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

	// this function is not currently called. you may call this from setup() if you want to test
	// this just plays the midi file -- all of it via your software synth. You will not use this
	// function in upcoming projects but it could be a good debug tool.
	void playMidiFile(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	
	// this starts & restarts the melody and runs unit tests
	public void keyPressed() {
		if (key == ' ') {
			player.reset();
			player.setMelody(midiNotes.getPitchArray());
			player.setRhythm(midiNotes.getRhythmArray());
			player.hasMelody = true; // starts the player
			println("Melody started!");
		} else if (key == 'p') {
			P5GenerateNotes(); 
			System.out.println("Generating notes . . . enjoy!");
		} else if (key == '1') {
			//unitTest.P5UnitTest1();
		} else if (key == '2') {
			//unitTest.P5UnitTest2();
		} else if (key == '3') {
			//unitTest.P5UnitTest3();
		} else if (key == 'o') {		
			player.hasMelody = false; // stops the player
		} 
	}
	
	
	// display instructions to the user
	public void showInstructions() {
		textAlign(CENTER);
		textSize(25);
		fill(255, 75, 75);
		text("Welcome to the", width/2, height*2/28);
		text("PST Melody Generator", width/2, height*4/28);
		textSize(16);
		fill(240, 75, 90);
		text("Press p to play 35 generated notes", width/2, height*6/28);
		text("from Super_Mario_Bros_Theme.mid", width/2, height*7/28);
		fill(225, 75, 105);
		text("Press the spacebar to restart original melody", width/2, height*11/28);
		text("Press o to stop playing", width/2, height*12/28);
		fill(210, 75, 120);
		text("Press 1 for Project 5: Unit Test 1", width/2, height*14/28);
		text("Press 2 for Project 5: Unit Test 2", width/2, height*15/28); 
		text("Press 3 for Project 5: Unit Test 3", width/2, height*16/28);
	}
	
	
	// generate a melody using the PST Generator from Project 5
	public void P5GenerateNotes() {
		filePath = getPath("mid/Super_Mario_Bros_Theme.mid");
		midiNotes = new MidiFileToNotes(filePath);
		midiNotes.setWhichLine(0);
		
		
	}
}
