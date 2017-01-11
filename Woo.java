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
	for (int i = 0; i < totalPlayers; i++ ) {
	    System.out.println("Who is player " + i + " ?");
	    playerNames[i] = String.parseString( in.readLine() );
	}
	
	points = new int[totalPlayers];//Updates the points array to number of players in the game and default is already 0

	System.out.println("\nSelect the categories you wish to play\n");
	System.out.println("\t1: Math\n");
	System.out.println("\t2: Science\n");
	System.out.println("\t3: History\n");
	System.out.println("\t4: Miscellaneous\n");

	String cat= String.parseString( in.readLine()); 
	System.out.println("\n How many points would you like to play for? Please type in the number before the points.\n");

	System.out.println("\t 1: 100\n");
	System.out.println("\t 2: 200\n");
	System.out.println("\t 3: 300\n");
	System.out.println("\t 4: 400\n");
	System.out.println("\t 5: 500\n");
	String pt= Integer.parseInteger( in.readLine());	
    }

    //Overwritten toString that prints the game board
    public String toString() {
	ans = "";
	//Adds the category names
	for (int i = 0; i < categories.length; i++) {
	    ans += categories[i] + " "; 
	}
	ans += "\n";

	//Implement point values here
	int increment = 100;
	for (int i = 0; i < 5; i++) {
	    for (int i = 0; i < categories.length; i++) {
		ans += increment + " ";
	    }
	    ans += "\n";
	    increment += 100;
	}
    }

    //String of each player ordered in terms of their points
    public String sortRank() {
	for (int i = 0; i < playerNames.length - 1; i++) {
	    if (points[i] < points[i+1]) {
		//switches points
		int temp = points[i];
		points[i] = points[i+1];
		points[i+1] = temp;
		//switches playerNames when points are switched
		String temp = playerNames[i];
		playerNames[i] = playerNames[i+1];
	        playerNames[i+1] = temp;
	    }
	}
	return 
    }

    //Returns if the given answer is correct
    public boolean compareAnswers() {
	/*
1. array of answers(subject. answers[i] compared to readline of what the user inputs. 
if .equals--> true.-->
SOP "good job you gained+ ___ + points"
	 */
	if (cat.answers[pt].equals(
    }

    //Returns a calculation of each player's total points
    public int addPoints(int index) {
	//player in index will have the amount of points in points[index]
	points[index] += questionWorth[//im not sure];
    }

}
