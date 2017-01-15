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
    private int[][] board= new int[5][5];
    protected String[] questions;
    protected String[] answers;
    private boolean gameOn;
    private int[] ind = new int[5];
    private String[] categories = {" ", "Math","Science","History","Misc","Stuy","Literature"};
    String[] newCat = createCategory();

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

        System.out.println("How many players are playing in total?"); //Currently only supports a single player
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
	pointTable(board);
	for (int ind = 0; ind < (board[0].length)*((board.length)-1); ind++) {
        System.out.println(toString());
        print2(board);

        if (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0)
            System.out.println("\t1: " + newCat[0]);
        if (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0)
            System.out.println("\t2: " + newCat[1]);
        if (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0)
            System.out.println("\t3: " + newCat[2]);
        if (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0)
            System.out.println("\t4: " + newCat[3]);
        if (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0)
            System.out.println("\t5: " + newCat[4]);
        //if (board[0][5] + board[1][5] + board[2][5] + board[3][5] + board[4][5] != 0)
            //System.out.println("\t6: " + newCat[5]);

        //For use when there are categories to be chosen
        //if (Integer.parseInt(in.readLine() ) == 1)
        //{
        int col = 0;
        do {
            System.out.println("\nSelect the categories you wish to play. Please type in the number before the points\n");
            col = Keyboard.readInt();
        } while (!((col == 1 && (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0))
		   || (col == 2 && (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0))
		   || (col == 3 && (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0))
		   || (col == 4 && (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0))
		   || (col == 5 && (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0))
		   || (col == 6 && (board[0][5] + board[1][5] + board[2][5] + board[3][5] + board[4][5] != 0))
		   ) );


        if (board[0][col - 1] != 0)
            System.out.println("\t 1: 100");
        if (board[1][col - 1] != 0)
            System.out.println("\t 2: 200");
        if (board[2][col - 1] != 0)
            System.out.println("\t 3: 300");
        if (board[3][col - 1] != 0)
            System.out.println("\t 4: 400");
        if (board[4][col - 1] != 0)
            System.out.println("\t 5: 500");
        int row = 0;
        do {
            System.out.println("\n How many points would you like to play for? " +
                    "Please type in the number before the points.\n");
            row = Keyboard.readInt();
        } while (!((row == 1 && board[0][col - 1] != 0)
                || (row == 2 && board[1][col - 1] != 0)
                || (row == 3 && board[2][col - 1] != 0)
                || (row == 4 && board[3][col - 1] != 0)
                || (row == 5 && board[4][col - 1] != 0)));

        /*if (col == 1) {
            System.out.println(Mathematics.questions[row - 1]);
        }
        if (col == 2) {
            System.out.println(Sci.questions[row - 1]);
        }
        if (col == 3) {
            System.out.println(History.questions[row - 1]);
        }
        if (col == 4) {
            System.out.println(Misc.questions[row - 1]);
        }*/

        if (newCat[col-1].trim().equals("Math")) {
          System.out.println(Mathematics.questions[row-1]);
        }
        if (newCat[col-1].trim().equals("Science")) {
          System.out.println(Sci.questions[row-1]);
        }
        if (newCat[col-1].trim().equals("History")) {
          System.out.println(History.questions[row-1]);
        }
        if (newCat[col-1].trim().equals("Misc")) {
          System.out.println(Misc.questions[row-1]);
        }
        if (newCat[col-1].trim().equals("Stuy")) {
          System.out.println(Stuy.questions[row-1]);
        }
        if (newCat[col-1].trim().equals("Literature")) {
          System.out.println(Literature.questions[row-1]);
        }

        String ans = Keyboard.readString();

        if ((col == 1 && ans.equalsIgnoreCase(Mathematics.answers[row - 1]))
                || (col == 2 && ans.equalsIgnoreCase(Sci.answers[row - 1]))
                || (col == 3 && ans.equalsIgnoreCase(History.answers[row - 1]))
                || (col == 4 && ans.equalsIgnoreCase(Misc.answers[row - 1])))   {
            points[whichPlayer()] += row * 100;
            System.out.println("Congratulations! You answered the question correctly!");
            board[row - 1][col - 1] = 0;
            //	print2(board);
    } else {
		System.out.println("You got it wrong");
	}

	} //ends for loop
    } //ends the game
    public boolean checkArray(int[] arr, int input) {
      boolean retBool = true;
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == input) {
          return false;
        }
      }
      return retBool;
    }

    public int[] categoryChooser(String[] categories, int[] ind) {
      int ctr = 0;
      while (ctr < 5) {
        int rand = (int) (Math.random() * categories.length);
        if (checkArray(ind, rand)) {
          ind[ctr] = rand;
          ctr += 1;
        }
        else {
          ctr += 0;
        }
      }
      return ind;
    }

    public String[] createCategory() {
      String[] ans = new String[5];
      int[] ind = new int[5];
      ind = categoryChooser(categories, ind);
      for (int i = 0; i < 5; i++) {
        ans[i] = categories[ind[i]] + "\t";
      }
      return ans;
    }


        //Overwritten toString that prints the game board
    public String toString() {

        //Adds the category names
        String ans ="";
        for (String category : newCat) {
            ans += category + "\t";
        }
        ans += "\n";
        return ans;
    }

    public int[][] pointTable(int[][] board){
	for (int row=0; row<board.length; row++){
	    for(int col=0; col<board[row].length; col++){
		board[row][col]= (row+1)*100;
	    }
	}
	return board;
    }
    public static void print2( int[][] board ) {
	//prints two d array
	for( int[] k : board ) {
	    for( int j : k ) {
	        if (j != 0)
		        System.out.print( j);
            System.out.print("\t\t");
	    }
	    System.out.println();
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
        return true;
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
