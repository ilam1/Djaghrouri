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
    private int[] points;
    private int[][] board = new int[5][5];
    private String[] buzzer;
    private boolean[] buzzers;      // sees who buzzed already
    private boolean gameOn;
    private int[] ind = new int[5];
    private String[] categories =
            {"Nature", "Math", "Science", "History", "Logic", "StuyTrivia",
                     "Cheese", "China", "CompSci", "Geography",
                    "Pop", "Women", "USA", "College", "Plays",
                    "Food", "Tech", "Shows", "Music", "Earth", "Culture",
                    "Literature", "Space", "Sports"};

    //COLORS
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    String[] newCat = createCategory();
    String buzzed = "";

    private BufferedReader in;
    private boolean diffPoints; //True if and only if the user creates a game with different point values

    //Default constructor
    public Woo() {
        gameOn = true;
    }
    /*
     *This method loads the game in the beginning.
     * It prints out loading followed by periods with a lag time.
     */
    public void load(){
        System.out.print(ANSI_GREEN + "Loading" + ANSI_RESET);
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000); //What gives the 1 second interval
            } catch (InterruptedException ignored) {}
            System.out.print(ANSI_GREEN + "." + ANSI_RESET);
        }
        System.out.println("\n" + ANSI_YELLOW + "You are now playing JEOPARDY \nDedicated to Ms. Djaghrouri (≧∀≦)" + ANSI_RESET + "\n\n" );
    }
    /*
      This method populates the category array in Custom based on what the user inputs.
      Limits the user to only 5 categories
    */
    public String[] makeCat(){
        int ctr=1;
        while (ctr<=5){
            System.out.println(ANSI_GREEN + "What's the topic for category "+ ctr + "?" + ANSI_RESET);
            Custom.category[ctr-1]= Keyboard.readString();
            ctr+=1;
        }
        return Custom.category;
    }
    /**
     * This method takes in two arrays as parameter and populates it with the questions and answer that the user inputs
     * Limits the user to only 5 questions+answers per category
     */
    public void makeQuesAns(String[] quesArr, String[] ansArr, int cat) {
        for(int ind=0; ind<5; ind++){
            System.out.println(ANSI_GREEN + "What is the question worth "+((ind+1)*100)+" points for the "+ Custom.category[cat]+" category" + ANSI_RESET);
            quesArr[ind]=Keyboard.readString();
            System.out.println(ANSI_GREEN + "What is the answer worth "+((ind+1)*100)+" points for the "+ Custom.category[cat]+" category" + ANSI_RESET);
            ansArr[ind]=Keyboard.readString();
        }
    }

    /*
     *This method is used to answer the first question we ask the user:
     *whether they would like to create their own game or play premade questions.
     *If they choose to create their own game they get the option of populating it the arrays of the class Custom
     */
    public void choice1(){

        //Implementation to create your own game
        makeCat();
        //testPrintArr(Custom.category);
        makeQuesAns(Custom.questions1, Custom.answers1, 0);
        makeQuesAns(Custom.questions2, Custom.answers2, 1);
        makeQuesAns(Custom.questions3, Custom.answers3, 2);
        makeQuesAns(Custom.questions4, Custom.answers4, 3);
        makeQuesAns(Custom.questions5, Custom.answers5, 4);
    }
    /*
     *This methos is just used to test whether or not the arrays are being populated or not.
     * loops through a string array and prints each element
     */
    public void testPrintArr(String[] arr){
        for (String x: arr){
            System.out.println(x);
        }
    }
    /*
     *Updates the array playerNames to the size of the number of players
     *Populates the array playerNames with the names that the players input
     *Updates the array buzzer to include every players' buzzer
     */
    public void setBuzzer(int totalPlayers){
        for (int i = 0; i < totalPlayers; i++) {
            System.out.println("\n" + ANSI_GREEN + "Who is player " + (i + 1) + " ?" + ANSI_RESET);
            try {
                playerNames[i] = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (totalPlayers > 1) {
                System.out.println("\n" + ANSI_GREEN + playerNames[i] + " please select your ONE CHARACTER desired buzzer (e.g. a)" + ANSI_RESET);

                while (buzzer[i] == null) {
                    boolean any = false;
                    String buzzerName="";
                    try {
                        buzzerName = in.readLine().trim().substring(0,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (String x: buzzer) {
                        if (x != null && x.equals(buzzerName) ) {
                            any = true; }
                    }
                    if (any) {
                        System.out.println(ANSI_RED + "Your buzzer has already been taken! Please select another buzzer:" + ANSI_RESET);
                    }
                    else if (buzzerName.length() != 1) {
                        System.out.println(ANSI_RED + "Your buzzer is invalid! Please select another buzzer:" + ANSI_RESET);
                    }
                    else {
                        buzzer[i] = buzzerName;
                    }
                }
            }
        }
    }
    public void newGame() {
        load();

        in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(ANSI_RED + "\t1: Create your own game!" + ANSI_RESET);
        System.out.println(ANSI_RED + "\t2: Play our game!" + ANSI_RESET);
        int option = 0;

        try {
            option = Integer.parseInt(in.readLine());
        } catch (IOException ignored) {}

        if (option == 1)
            choice1();

        System.out.println("\n" + ANSI_GREEN + "How many players are playing in total?" + ANSI_RESET);

        try {
            totalPlayers = Integer.parseInt(in.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + ANSI_GREEN + "Today, one of these " + totalPlayers + " contestants will win the Jeopardy!" + ANSI_RESET);

        playerNames = new String[totalPlayers]; //Updates the array playerNames to the size of the number of players
        buzzer = new String[totalPlayers];

        //Updates the array playerNames to the size of the number of players

        setBuzzer(totalPlayers);

        if (option == 2) {
            System.out.printf("\n" + ANSI_GREEN + "Would you %s like to play a randomized game or a customized game?%n" + ANSI_RESET, (totalPlayers == 1) ? "" : "all" );
            System.out.println("\t" + ANSI_RED + "1: Random Game" + "\n\t2: Customized Game" + ANSI_RESET);

            String choice = "";

            while (!choice.trim().equals("1") && !choice.trim().equals("2"))
                try {
                    choice = in.readLine();
                } catch (IOException ignored) {}

            if (choice.equals("2")) {

                int i = 0;
                int left = 5;       // categories to choose left
                newCat = new String[5];
                while (left != 0) {
                    System.out.println("\n");
                    for (String s : categories) {
                        boolean exists = false;
                        for (String str : newCat)
                            if (s.equalsIgnoreCase(str))
                                exists = true;
                        System.out.printf("%-12s", exists ? "" : s);
                        if (i++ == 4) {
                            System.out.println();
                            i = 0;
                        }
                    }
                    System.out.printf(ANSI_GREEN + "%nCategories left: %d%n_> " + ANSI_RESET, left);

                    String str = "";
                    try {
                        str = in.readLine().trim().toUpperCase();
                    } catch (IOException ignored) {}
                    boolean invalid = false;
                    for (String s : newCat)
                        if (str.equals(s))
                            invalid = true;

                    boolean exists = false;
                    for (String s : categories)
                        if (str.equalsIgnoreCase(s))
                            exists = true;

                    if (exists && !invalid) {
                        newCat[5 - left] = str;
                        left--;
                        System.out.printf(ANSI_GREEN + "%s has been added to our game." + ANSI_GREEN, str);
                        if (left > 0)
                            System.out.printf("Select %d more categories.", left);
                        else
                            System.out.println();
                    } else if (invalid)
                        System.out.printf(ANSI_RED + "%s is already taken, please select another category" + ANSI_RESET, str);
                    else
                        System.out.printf(ANSI_RED + "Sorry, we do not recognize \"%s\", please select another category" + ANSI_RESET, str);
                }
            }
        }
        System.out.println("\n*            *            *\n\n" + ANSI_GREEN + "Thank you, ladies and gentlemen. Hello & welcome to " +
                "Jeopardy!, America's favorite answer-and-question game. Yes, we give the QUESTIONS, and then it's " +
                "up to these " + totalPlayers + " contestants to come up with the ANSWERS. Players, as you know, " +
                "whenever you recognize an answer you're free to ring in; but, I want to warn you about the " +
                "Jeopardy!: if you are wrong, the value of the question will be deducted from your winnings. " +
                "Right now, put your hands on the buttons, but please don't ring in until the answer is exposed. " +
                "If all " + totalPlayers + " of you are ready, then let's play Jeopardy!" + ANSI_RESET);

        points = new int[totalPlayers];//Updates the points array to number of players in the game
        if (option == 1) {
            pointTable(board);
            for (int ind = 0; ind < board[0].length * board.length; ind++) {
                sortRank();
                System.out.println();
                System.out.println(printCustom());
                print2(board);

                int col = 0;
                do {
                    System.out.println("\n" + ANSI_GREEN + "Select the categories you wish to play. Please type in the number before the points" + ANSI_RESET + "\n");

                    if (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0)
                        System.out.println("\t" + ANSI_RED + "1: " + Custom.category[0] + ANSI_RESET);
                    if (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0)
                        System.out.println("\t" + ANSI_RED + "2: " + Custom.category[1] + ANSI_RESET);
                    if (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0)
                        System.out.println("\t" + ANSI_RED + "3: " + Custom.category[2] + ANSI_RESET);
                    if (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0)
                        System.out.println("\t" + ANSI_RED + "4: " + Custom.category[3] + ANSI_RESET);
                    if (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0)
                        System.out.println("\t" + ANSI_RED + "5: " + Custom.category[4] + ANSI_RESET);

                    col = Keyboard.readInt();
                } while (!((col == 1 && (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0))
                        || (col == 2 && (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0))
                        || (col == 3 && (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0))
                        || (col == 4 && (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0))
                        || (col == 5 && (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0))
                        || (col == 6 && (board[0][5] + board[1][5] + board[2][5] + board[3][5] + board[4][5] != 0))
                ));

                int row = 0;
                do {
                    System.out.println("\n" + ANSI_GREEN +"How many points would you like to play for? " +
                            "Please type in the number before the points.\n" + ANSI_RESET);
                    if (board[0][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "1: 100" + ANSI_RESET);
                    if (board[1][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "2: 200" + ANSI_RESET);
                    if (board[2][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "3: 300" + ANSI_RESET);
                    if (board[3][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "4: 400" + ANSI_RESET);
                    if (board[4][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "5: 500" + ANSI_RESET);
                    row = Keyboard.readInt();
                } while (!((row == 1 && board[0][col - 1] != 0)
                        || (row == 2 && board[1][col - 1] != 0)
                        || (row == 3 && board[2][col - 1] != 0)
                        || (row == 4 && board[3][col - 1] != 0)
                        || (row == 5 && board[4][col - 1] != 0)));

                buzzers = new boolean[totalPlayers];        // nobody attempted this problem yet

                while (true) {                              // until somebody gets it right
                    int count = 0;      // how many people still have a buzzer
                    for (int i = 0; i < totalPlayers; i++)
                        if (!buzzers[i]) {
                            count++;
                            buzzed = buzzer[i];     // only matters if last person buzzed, else overwritten below
                        }
                    if (count == 0)
                        break;

                    printCustomQues(col, row);
                    if (totalPlayers > 1 && count > 1) {
                        System.out.println(ANSI_GREEN + "Buzz to answer the question! Don't remember your buzzer?? Type \"Buzzer\"." + ANSI_RESET);
                        try {
                            buzzed = in.readLine().trim();
                            if (buzzed.equalsIgnoreCase("Buzzer"))
                                for (int i = 0; i < totalPlayers; i++)
                                    if (!buzzers[i])
                                        System.out.printf("%-10s -> %s%n", playerNames[i], buzzer[i]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        while (hasAnswered(buzzed)) {   //playerToAnswer(buzzed).equals("Error")
			    if (!buzzed.equalsIgnoreCase("Buzzer"))
				System.out.println("\n" + ANSI_GREEN + "No one has that buzzer!!" + ANSI_RESET);
			    System.out.println(ANSI_GREEN + "Playing players buzz again!" + ANSI_RESET + "\n");
                            try {
                                buzzed = in.readLine().trim();
                                if (buzzed.equalsIgnoreCase("Buzzer"))
                                    for (int i = 0; i < totalPlayers; i++)
                                        if (!buzzers[i])
                                            System.out.printf("%-10s -> %s%n", playerNames[i], buzzer[i]);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (totalPlayers > 1) {
                        System.out.println(ANSI_GREEN + playerToAnswer(buzzed) + " buzzed, so you get to answer!" + ANSI_RESET);
                        hasAnswered(buzzed);
                    }
                    System.out.print(ANSI_GREEN + "Ans: " + ANSI_RESET);
                    String ans = Keyboard.readString();
                    if (checkCustomAns(ans, col, row, count == 1) || (totalPlayers == 1))
                        break;
                }
                finalJeopardy();
            }
        }

        //------------------------------MARKER FOR HELP-------
        else {
            pointTable(board);
            for (int ind = 0; ind < board[0].length * board.length; ind++) {
                sortRank();
                System.out.println();
                System.out.println(toString());
                print2(board);

                int col = 0;
                do {
                    System.out.println("\n" + ANSI_GREEN + "Select the categories you wish to play. Please type in the number before the points" + ANSI_RESET);

                    if (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0)
                        System.out.println("\t" + ANSI_RED + "1: " + newCat[0] + ANSI_RESET);
                    if (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0)
                        System.out.println("\t" + ANSI_RED + "2: " + newCat[1] + ANSI_RESET);
                    if (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0)
                        System.out.println("\t" + ANSI_RED + "3: " + newCat[2] + ANSI_RESET);
                    if (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0)
                        System.out.println("\t" + ANSI_RED + "4: " + newCat[3] + ANSI_RESET);
                    if (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0)
                        System.out.println("\t" + ANSI_RED + "5: " + newCat[4] + ANSI_RESET);

                    col = Keyboard.readInt();
                } while (!((col == 1 && (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0))
                        || (col == 2 && (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0))
                        || (col == 3 && (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0))
                        || (col == 4 && (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0))
                        || (col == 5 && (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0))
                        || (col == 6 && (board[0][5] + board[1][5] + board[2][5] + board[3][5] + board[4][5] != 0))
                ));

                int row = 0;
                do {
                    System.out.println("\n" + ANSI_GREEN +"How many points would you like to play for? " +
                            "Please type in the number before the points.\n" + ANSI_RESET);
                    if (board[0][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "1: 100" + ANSI_RESET);
                    if (board[1][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "2: 200" + ANSI_RESET);
                    if (board[2][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "3: 300" + ANSI_RESET);
                    if (board[3][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "4: 400" + ANSI_RESET);
                    if (board[4][col - 1] != 0)
                        System.out.println("\t" + ANSI_RED + "5: 500" + ANSI_RESET);
                    row = Keyboard.readInt();
                } while (!((row == 1 && board[0][col - 1] != 0)
                        || (row == 2 && board[1][col - 1] != 0)
                        || (row == 3 && board[2][col - 1] != 0)
                        || (row == 4 && board[3][col - 1] != 0)
                        || (row == 5 && board[4][col - 1] != 0)));

                buzzers = new boolean[totalPlayers];        // nobody attempted this problem yet


                while (true) {                              // until somebody answers the question right
                    int count = 0;      // how many people still have a buzzer
                    for (int i = 0; i < totalPlayers; i++)
                        if (!buzzers[i]) {
                            count++;
                            buzzed = buzzer[i];     // only matters if last person buzzed, else overwritten below
                        }
                    if (count == 0)
                        break;
                    printQues(col, row);

                    if (totalPlayers > 1 && count > 1) {
                        System.out.println(ANSI_GREEN + "Buzz to answer the question!  Not sure? Don't Remember? Type \"Buzzer\"." + ANSI_RESET);
                        try {
                            buzzed = in.readLine();
                            if (buzzed.equalsIgnoreCase("Buzzer"))
                                for (int i = 0; i < totalPlayers; i++)
                                    if (!buzzers[i])
                                        System.out.printf("%-10s -> %s%n", playerNames[i], buzzer[i]);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        while (hasAnswered(buzzed)) {//playerToAnswer(buzzed).equals("Error")) {
			    if (!buzzed.equalsIgnoreCase("Buzzer"))
				System.out.println("\n" + ANSI_RED + "No one active has that buzzer!!" + ANSI_RESET);
			    System.out.println(ANSI_GREEN + "Playing players buzz again!" + ANSI_RESET + "\n");
                            try {
                                buzzed = in.readLine();
                                if (buzzed.equalsIgnoreCase("Buzzer"))
                                    for (int i = 0; i < totalPlayers; i++)
                                        if (!buzzers[i])
                                            System.out.printf("%-10s -> %s%n", playerNames[i], buzzer[i]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (totalPlayers > 1) {
                        System.out.println(ANSI_GREEN + playerToAnswer(buzzed) + " buzzed, so you get to answer!" + ANSI_RESET);
                        hasAnswered(buzzed);
                    }
                    System.out.print(ANSI_GREEN + "Ans: " + ANSI_RESET);
                    String ans = Keyboard.readString();
                    if (checkAns(ans, col, row, count == 1) || (totalPlayers == 1))
                        break;
                }
            } //ends for loop
            finalJeopardy();
        } //ends if/else statement
    } //ends the game
    /*
     *After the user chooses the category they would like and the number of points they would like to play for
     *This uses the user's input data to go into the array of category names and compares the category name to the class name
     *Then prints out the points worth index of the question array in the category subclass.
     */

    public void printCustomQues(int col, int row) {
        if (col == 1) { System.out.println(ANSI_GREEN + Custom.questions1[row-1]); }
        else if (col == 2) { System.out.println(Custom.questions2[row-1]); }
        else if (col == 3) { System.out.println(Custom.questions3[row-1]); }
        else if (col == 4) { System.out.println(Custom.questions4[row-1]); }
        else if (col == 5) { System.out.println(Custom.questions5[row-1] + ANSI_RESET); }
    }

    public void printQues(int col, int row) {
        //prints the questions corresponding to the random array
        if (newCat[col - 1].trim().equalsIgnoreCase("Nature"))
            System.out.println(ANSI_GREEN + Nature.questions[row - 1]);
        if (newCat[col - 1].trim().equalsIgnoreCase("Math"))
            System.out.println(Mathematics.questions[row - 1]);
        if (newCat[col - 1].trim().equalsIgnoreCase("Science"))
            System.out.println(Sci.questions[row - 1]);
        if (newCat[col - 1].trim().equalsIgnoreCase("History")) {
            System.out.println(History.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Logic")) {
            System.out.println(Misc.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("StuyTrivia")) {
            System.out.println(Stuy.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Literature")) {
            System.out.println(Literature.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Cheese")) {
            System.out.println(Cheese.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("China")) {
            System.out.println(China.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("CompSci")) {
            System.out.println(CompSci.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Geography")) {
            System.out.println(Geography.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Pop")) {
            System.out.println(Impossible.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Women")) {
            System.out.println(Women.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("USA")) {
            System.out.println(USA.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("College")) {
            System.out.println(Universities.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Plays")) {
            System.out.println(Theatre.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Food")) {
            System.out.println(Food.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Tech")) {
            System.out.println(Technology.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Shows")) {
            System.out.println(Shows.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Music")) {
            System.out.println(Music.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Earth")) {
            System.out.println(Earth.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Culture")) {
            System.out.println(Culture.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Composers")) {
            System.out.println(Composers.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Space")) {
            System.out.println(Astronomy.questions[row - 1]);
        }
        if (newCat[col - 1].trim().equalsIgnoreCase("Sports")) {
            System.out.println(Sport.questions[row - 1] + ANSI_RESET);
        }
    }

    public boolean checkCustomAns(String ans, int col, int row, boolean toPrint) {
        if (col == 1 && ans.equalsIgnoreCase(Custom.answers1[row - 1])
                || col == 2 && ans.equalsIgnoreCase(Custom.answers2[row - 1])
                || col == 3 && ans.equalsIgnoreCase(Custom.answers3[row - 1])
                || col == 4 && ans.equalsIgnoreCase(Custom.answers4[row - 1])
                || col == 5 && ans.equalsIgnoreCase(Custom.answers5[row - 1]))
        {
            points[(totalPlayers > 1) ? whichPlayer(buzzed) : 0] += row * 100;
            System.out.println(ANSI_GREEN + "Congratulations! You answered the question correctly!" + ANSI_RESET);
            board[row - 1][col - 1] = 0;
            return true;
        }
        else {
            System.out.println(ANSI_GREEN + "You got it wrong. " + ANSI_RESET);
	    if (totalPlayers > 1) {
		points[whichPlayer(buzzed)] -= row * 100; }
            if (totalPlayers == 1 || toPrint) {
                board[row - 1][col - 1] = 0;
                System.out.println(ANSI_GREEN + "The correct answer was: " + ANSI_RESET);
                if (col == 1 )
                    { System.out.println(Custom.answers1[row - 1]); }
                if (col == 2 )
                    { System.out.println(Custom.answers2[row - 1]); }
                if (col == 3 )
                    { System.out.println(Custom.answers3[row - 1]); }
                if (col == 4 )
                    { System.out.println(Custom.answers4[row - 1]); }
                if (col == 5 )
                    { System.out.println(Custom.answers5[row - 1]); }
            }
            return false;
        }
        //sortRank();
    }


    /*
     *After the user answers the question, this method compares what the user inputs to the subclass's corresponding answer.
     *If the user gets it right then that part of the game board disappears and the user gets the points
     *Otherwise the game prints out the correct answer.
     */
    public boolean checkAns(String ans, int col, int row, boolean toPrint) {
        //this if statement checks if the user's answer is correct and adds points if it is.
        if ((newCat[col - 1].trim().equalsIgnoreCase("Nature") && ans.equalsIgnoreCase(Nature.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Math")     && ans.equalsIgnoreCase(Mathematics.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Science")  && ans.equalsIgnoreCase(Sci.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("History")  && ans.equalsIgnoreCase(History.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Logic")    && ans.equalsIgnoreCase(Misc.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("StuyTrivia")&& ans.equalsIgnoreCase(Stuy.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Literature")     && ans.equalsIgnoreCase(Literature.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Cheese")   && ans.equalsIgnoreCase(Cheese.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("China")    && ans.equalsIgnoreCase(China.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("CompSci")  && ans.equalsIgnoreCase(CompSci.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Geography")&& ans.equalsIgnoreCase(Geography.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Pop")      && ans.equalsIgnoreCase(Impossible.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Women")    && ans.equalsIgnoreCase(Women.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("USA")      && ans.equalsIgnoreCase(USA.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("College")  && ans.equalsIgnoreCase(Universities.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Plays")    && ans.equalsIgnoreCase(Theatre.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Food")     && ans.equalsIgnoreCase(Food.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Tech")     && ans.equalsIgnoreCase(Technology.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Shows")    && ans.equalsIgnoreCase(Shows.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Music")    && ans.equalsIgnoreCase(Music.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Earth")    && ans.equalsIgnoreCase(Earth.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Culture")  && ans.equalsIgnoreCase(Culture.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Composers")&& ans.equalsIgnoreCase(Composers.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Space")    && ans.equalsIgnoreCase(Astronomy.answers[row - 1]))
                || (newCat[col - 1].trim().equalsIgnoreCase("Sports")   && ans.equalsIgnoreCase(Sport.answers[row - 1]))) {
            points[(totalPlayers > 1) ? whichPlayer(buzzed) : 0] += row * 100;
            System.out.println(ANSI_GREEN + "Congratulations! You answered the question correctly!" + ANSI_RESET);
            board[row - 1][col - 1] = 0;
            return true;
        } else {
            System.out.println(ANSI_GREEN + "You got it wrong. " + ANSI_RESET);
            points[(totalPlayers > 1) ? whichPlayer(buzzed) : 0] -= row * 100;
            if (totalPlayers == 1 || toPrint) {
                board[row - 1][col - 1] = 0;
                System.out.println(ANSI_GREEN + "The correct answer is: " + ANSI_RESET);
                if ((newCat[col - 1].trim().equals("Nature"))) {
                    System.out.println(Nature.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Math"))) {
                    System.out.println(Mathematics.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Science"))) {
                    System.out.println(Sci.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("History"))) {
                    System.out.println(History.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Logic"))) {
                    System.out.println(Misc.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("StuyTrivia"))) {
                    System.out.println(Stuy.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Literature"))) {
                    System.out.println(Literature.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Cheese"))) {
                    System.out.println(Cheese.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("China"))) {
                    System.out.println(China.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Compsci"))) {
                    System.out.println(CompSci.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Geography"))) {
                    System.out.println(Geography.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Pop")))
                    System.out.println(Impossible.answers[row - 1] + "\nDisclaimer: Pop was short for Impossible c:");
                if ((newCat[col - 1].trim().equalsIgnoreCase("Women"))) {
                    System.out.println(Women.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("USA"))) {
                    System.out.println(USA.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("College"))) {
                    System.out.println(Universities.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Plays"))) {
                    System.out.println(Theatre.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Food"))) {
                    System.out.println(Food.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Tech"))) {
                    System.out.println(Technology.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Shows"))) {
                    System.out.println(Shows.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Music"))) {
                    System.out.println(Music.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Earth"))) {
                    System.out.println(Earth.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Culture"))) {
                    System.out.println(Culture.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Composers"))) {
                    System.out.println(Composers.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Space"))) {
                    System.out.println(Astronomy.answers[row - 1]); }
                if ((newCat[col - 1].trim().equalsIgnoreCase("Sports"))) {
                    System.out.println(Sport.answers[row - 1]); }

            }
            return false;
        }
        //sortRank();
    }

    /*
     *This method takes in an int array and an input and checks that it is not repeating.
     *it is used in categoryChooser to makeQuesAns sure that the index is not repeating before appending it to the ind array
     */
    public boolean checkArray(int[] arr, int input) {
        for (int anArr : arr)
            if (anArr == input)
                return false;
        return true;
    }

    /*
     * categoryChooser randomly generates a number between 0 and the length of the categories instance variable.
     * then it returns an int array of randomly generated numbers that are not repeating.
     * these numbers represent the index of the category in the categories instance variable
     */
    public int[] categoryChooser(String[] categories, int[] ind) {
        //Randomly generates a number and checks that it is not repeating.
        //this is used to randomly generate categories.
        int ctr = 0;
        while (ctr < 5) {
            int rand = (int) (Math.random() * categories.length);
            if (checkArray(ind, rand)) {
                ind[ctr] = rand;
                ctr += 1;
            }
        }
        return ind;
    }

    /*
     *This method calls category chooser and fills the String array answer with the corresponding categories of the ind array
     * that is generated using categoryChooser and checkArray.
     */
    public String[] createCategory(){
        String[] ans = new String[5];
        int[] ind = new int[5];
        ind = categoryChooser(categories, ind);
        for (int i = 0; i < 5; i++) {
            ans[i] = categories[ind[i]];
        }
        return ans;
    }


    //Overwritten toString that prints the game board headers
    public String toString() {
        //Adds the category names
        String ans = "";
        for (String category : newCat) {//newcat is an instance variable array that is randomly generated
            ans += String.format("%-11s", category);
        }
        ans += "\n";
        return ans;
    }

    public String printCustom() {
        String retString = "";
        for (String category : Custom.category) {//Custom.category is an instance variable array inside a subclass
            retString += String.format("%-11s", category);
        }
        retString += "\n";
        return retString;
    }

    /*
     *This method is similar to the method we previously wrote when we were making the 2D array utils class.
     *it populates a 2D array with the points.
     */
    public int[][] pointTable(int[][] board) {
        //Creates the two d array that will be used to print the points.
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = (row + 1) * 100;
            }
        }
        return board;
    }

    /*
     * This method is similaar to the method we previously wrote when we were making the 2D array utils class.
     * It loops through a 2D array and prints it out in the form of a table.
     */
    public static void print2(int[][] board) {
        //prints two d array with a tab in between
        for (int[] k : board) {
            for (int j : k) {
                System.out.printf("%3s%8s", (j != 0) ? j : "", "");
            }
            System.out.println();
        }
    }

    /*
     *String of each player ordered in terms of their points
     */
    public void sortRank() {
        if (totalPlayers == 1 ) {
            System.out.println("\n" + ANSI_YELLOW +"Your points: " + points[0] + ANSI_YELLOW);
            System.out.println(comments(points[0]));
            return;
        }
        boolean[] accessed = new boolean[totalPlayers];
        System.out.println("\n" + ANSI_YELLOW + "====================\nLeaderboard:\n");
        for (int i = 0; i < totalPlayers; i++) {
            int maxPlace = -1;
            for (int j = 0; j < totalPlayers; j++)
                if (!accessed[j]) {
                    maxPlace = j;
                    break;
                }
            if (maxPlace == -1)
                System.out.println(ANSI_WHITE + "Congratulations, you beat the program! Please report it to the creators of this game :)" + ANSI_RESET);

            for (int j = maxPlace; j < totalPlayers; j++)
                if (!accessed[j] && points[maxPlace] < points[j])
                    maxPlace = j;

            accessed[maxPlace] = true;      // max place is used, do not repeat again

            System.out.println(playerNames[maxPlace] + " -- " + points[maxPlace]);
        }
        System.out.println("====================");
    }

    public int whichPlayer(String buzz) {
        for (int i = 0; i < buzzer.length; i++) {
            if (buzz.equals(buzzer[i])) {
                return i; }
        }
        return 0;
    }

    public String playerToAnswer(String character) {
        for (int x = 0; x < buzzer.length; x++) {
            if (buzzer[x].equals(character)) {
                return playerNames[x];
            }
        }
        return "Error";
    }

    public boolean hasAnswered(String character) {
        for (int x = 0; x < buzzer.length; x++)
            if (buzzer[x].equals(character) && !buzzers[x]) {
                buzzers[x] = true;
                return false;
            }

        return true;
    }

    public String comments(int point) {
        if (point < -1000) {
            return ANSI_BLUE + "And you call yourself a Stuyvesant student?" + ANSI_RESET; }
        else if (point < -500) {
            return ANSI_BLUE + "Almost beyond hope - Selling 1 point for $1, please find Ms. D for change" + ANSI_RESET; }
        else if (point < 0) {
            return ANSI_BLUE + "We offer ARISTA and AIS Tutoring to cater to your every needs. Academic needs." + ANSI_RESET; }
        else if (point > 2000) {
            return ANSI_BLUE + "Average" + ANSI_RESET; }
        return "";
    }

    public void finalJeopardy() {
        sortRank();     // prints the final scores

        int maxScore =  points[0];      // max score for a player(s)
        ArrayList<String> who = new ArrayList<>();      // who hold the max scores
        ArrayList<String> whoChar = new ArrayList<>();  // corresponding char

        for (int i = 0; i < totalPlayers; i++)
            if (points[i] > maxScore) {
                maxScore = points[i];       // max score beaten, renew maxScore

                who.clear();                // reset list
                who.add(playerNames[i]);

                whoChar.clear();
                whoChar.add(buzzer[i]);
            } else if (points[i] == maxScore) {
                who.add(playerNames[i]);
                whoChar.add(buzzer[i]);
            }


        while (who.size() > 1) {             // until there is one
            System.out.println("\n~~  Instant Death  ~~\n\tFinal Jeopardy");
            System.out.println("What is our team name?");

            System.out.println("Only the best may play  \nRemember your buzzers!!");
            for (int i = 0; i < who.size(); i++)
                System.out.printf("%-10s -> %s%n", who.get(i), whoChar.get(i));
	    System.out.print("BUZZ IN");
	    for (int i = 0; i < 3; i++) {
		try {
		    Thread.sleep(1000); //What gives the 1 second interval
		} catch (InterruptedException ignored) {}
		System.out.print(".");
	    }
	    System.out.print("\nNOW");

            buzzed = "";
            try {
                while (true) {
                    buzzed = in.readLine();
                    if (!whoChar.contains(buzzed))
                        System.out.println("No playing winner has that buzzer!! Buzz again!");
                    else
                        break;
                }
            } catch (IOException ignored) {}

            System.out.println(playerToAnswer(buzzed) + " buzzed first, so you get to answer!");
            if ("Djaghrouri".equalsIgnoreCase(Keyboard.readString())) {
                for (int i = 0; i < totalPlayers; i++)
                    if (buzzer[i].equalsIgnoreCase(buzzed))
                        points[i]++;        // adds one point = victory
                break;
            } else {
                who.remove(whoChar.indexOf(buzzed));
                whoChar.remove(buzzed);
		for (int i = 0; i < totalPlayers; i++)
                    if (buzzer[i].equalsIgnoreCase(buzzed))
			points[i]--;        // loses one point = defeat

            }
        }
        System.out.println("Thank you for playing Jeopardy.  We have a winner~~");
	    System.out.println("\nThe winner is: " + who.get(0));
        sortRank();
    }


    public static void main(String[] args) {
        Woo jeopardy = new Woo();
        jeopardy.newGame();
    }
}
