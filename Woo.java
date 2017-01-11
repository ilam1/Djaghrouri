//Djaghrouri: Bayan Berri, Irene Lam, Jason Lam
//APCS1 pd5
//Final Project - JEOPARDY!
//2017-01-23

/*=============================================
  class Woo -- Driver file for JEOPARDY game.
  =============================================*/

import java.io.*;
import java.util.*;
import cs1.Keyboard.java*;

public abstract class Woo {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    private int totalPlayers;
    private String[] playerNames;
    private int[] questionWorth;
    private int[] points;
    private String[][] board;
    protected abstract String[] questions;
    protected abstract String[] answers;
    private boolean gameOn;
    private String[] categories = ["History","Math","Miscellaneous","Science"];	

    //Default constructor
    public Woo() {
	gameOn = true;
    }

    public void newGame() {
	//Fun printing :D -- essentially prints Loading... 3x at 1 second intervals to increase suspense for the game
	for (int i = 0; i < 3; i++) {
	    System.out.println("Loading...\n");
	    try {
		Thread.sleep(1000); //What gives the 1 second interval
	    }
	    catch (InterruptedException ex) { }
	}

	System.out.println("You are now playing JEOPARDY \n Dedicated to Ms. Djaghrouri\n");
	
	System.out.println("\t1: Create your own game!");
	System.out.println("\t2: Play our game!");
	int option;

	try {
	    option = Integer.parseInt( in.readLine() ); }
	catch ( IOException e ) { }
	
	if (option == 1) {
	    //Implementation to create your own game
	}

	System.out.println("How many players are playing in total?"); //Presumese 2+ players for now
	totalPlayers = Integer.parseInt( in.readLine() );

	System.out.println("\nToday, one of these " + totalPlayers + " contestants will win the Jeopardy!");
	
	playerNames = new String[totalPlayers]; //Updates the array playerNames to the size of the number of players
	
	//Updates the array playerNames to the size of the number of players    
	for (int i = 0; i < totalPlayers(); i++ ) {
	    System.out.println("Who is player " + i + " ?");
	    playerNames[i] = String.parseString( in.readLine() );
	}
	
	System.out.println("\nSelect the categories you wish to play");		   
	
    }

    //Overwritten toString that prints the game board
    public String toString() {
	ans = "";
	//Adds the category naems    
	for (int i = 0; i < categories.length; i++) {
		ans += categories[i] + " "; }
	ans += "\n"
	//Implement point values here	
    }

    //String of each player ordered in terms of their points
    public String sortRank() {
	
    }

    //Returns if the given answer is correct
    public boolean compareAnswers() {

    }

    //Returns a calculation of each player's total points
    public int totalPoints(int index) {

    }

}
