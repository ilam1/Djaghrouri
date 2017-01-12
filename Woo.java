//Djaghrouri: Bayan Berri, Irene Lam, Jason Lam
//APCS1 pd5
//Final Project - JEOPARDY!
//2017-01-23

/*=============================================
  class Woo -- Driver file for JEOPARDY game.
  =============================================*/

import java.io.*;
import java.util.*;
import cs1.Keyboard;

public class Woo {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    private int totalPlayers;
    private String[] playerNames;
    private int[] questionWorth;
    private int[] points;
    private String[][] board;
    protected String[] questions;
    protected String[] answers;
    private boolean gameOn;
    private String[] categories = {"Math","Science","History","Miscellaneous"};
    	
    private BufferedReader in;
    private boolean diffPoints; //True if and only if the user creates a game with different point values

    //Default constructor
    public Woo() {
        gameOn = true;
    }

    public void newGame() {
        //Fun printing :D -- essentially prints Loading... 3x at 1 second intervals to increase suspense for the game
        System.out.print("Loading");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000); //What gives the 1 second interval
            } catch (InterruptedException ex) {
            }
            System.out.print(".");
        }
        System.out.println();

        in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("You are now playing JEOPARDY \n Dedicated to Ms. Djaghrouri\n");
        System.out.println("\t1: Create your own game!");
        System.out.println("\t2: Play our game!");
        int option = 0;

        try {
            option = Integer.parseInt(in.readLine());
        } catch (IOException e) {
        }

        if (option == 1) {
            //Implementation to create your own game
        }

        System.out.println("How many players are playing in total?"); //Presumese 2+ players for now
        try {
            totalPlayers = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nToday, one of these " + totalPlayers + " contestants will win the Jeopardy!");

        playerNames = new String[totalPlayers]; //Updates the array playerNames to the size of the number of players

        //Updates the array playerNames to the size of the number of players
        for (int i = 0; i < totalPlayers; i++) {
            System.out.println("Who is player " + (i+1) + " ?");
            try {
                playerNames[i] = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	    
        points = new int[totalPlayers];//Updates the points array to number of players in the game and default is already 0
	for (int ind = 0; ind < (board[0].length)*(board.length-1); ind++) {
	System.out.println(board);
	    
        System.out.println("\nSelect the categories you wish to play. Please type in the number before the points\n");
        System.out.println("\t1: Math");
        System.out.println("\t2: Science");
        System.out.println("\t3: History");
        System.out.println("\t4: Miscellaneous");
	
	//For use when there are categories to be chosen
	//if (Integer.parseInt(in.readLine() ) == 1) 
	//{
	int col = Keyboard.readInt();
	    
	System.out.println("\n How many points would you like to play for? Please type in the number before the points.\n");

	System.out.println("\t 1: 100\n");
	System.out.println("\t 2: 200\n");
	System.out.println("\t 3: 300\n");
	System.out.println("\t 4: 400\n");
	System.out.println("\t 5: 500\n");
	int row = Keyboard.readInt();
	board[col][row] = "   ";
		
	if (col ==1) {
		System.out.println(Math.questions[row-1]); 
	}
	if (col == 2) {
		System.out.println(Sci.questions[row - 1]);
	} 
	if (col ==3) {
		System.out.println(History.questions[row-1]); 
	}
	if (col == 4) {
		System.out.println(Misc.questions[row - 1]);
	} 		
	String ans = Keyboard.readString();	
	if (compareAnswers(ans)) {
		points[whichPlayer()] += row*100;
		System.out.println("Congratulations!");
	}
	else {
		System.out.println("You got it wrong"); 
	}
		
	} //ends for loop    
    } //ends the game

        //Overwritten toString that prints the game board
    public String toString() {
        String ans = "";
        //Adds the category names
        for (int i = 0; i < categories.length; i++) {
            ans += categories[i] + " ";
        }
        ans += "\n";

        if (!diffPoints) {
            //Implement point values here
            int increment = 100;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; i < categories.length; j++) {
                    ans += increment + " ";
                }
                ans += "\n";
                increment += 100;
            }
        }
        return ans;
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
                String temps = playerNames[i];
                playerNames[i] = playerNames[i+1];
                playerNames[i+1] = temps;
            }
        }
        return null;
    }

    public int whichPlayer ( ) {
	    //Returns index of player playing
	return 0;
    }
			
    //Returns if the given answer is correct
    public boolean compareAnswers(String ans) {
	    /*
1. array of answers(subject. answers[i] compared to readline of what the user inputs.
if .equals--> true.-->
SOP "good job you gained+ ___ + points"
	 */

        //if (cat.answers[pt].equals(
        return false;
    }

    //Returns a calculation of each player's total points
    public int addPoints(int index) {
        //player in index will have the amount of points in points[index]
        points[index] += questionWorth[0];//im not sure];
        return 0;
    }

    public static void main(String[] args) {
        Woo jeopardy = new Woo();
        jeopardy.newGame();
    }
}
