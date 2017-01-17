# Djaghrouri
Bayan Berri, Irene Lam, Jason Lam

### Description
Upon running Woo.java, the user is prompted with two options, either to play the pre-existing game or to create their own game, that is, the game of JEOPARDY! The user is given an option to either play Normal Jeopardy or Special Edition Jeopardy (described below). From there, categories are chosen and the game follows (to a degree) regular jeopardy, with a tie invoking either All or Nothing! Further explanations of the mechanics of the game and usage of topics learned this past semester are explained below.

### Jargon Explained
* Special Edition Jeopardy randomly enhances certain boxes with a chance for player(s) to double the amount of money they win (similar to Double Jeopardy), except failure comes with a randomly generated twist, losing double thier amount.
* Normal Jeopardy fills x location(s) with Double Jeopardy, and follows the regular format for Jeopardy.

### Extra Aspects that may be Included
* An overarching riddle/ puzzle that when answered wins the game.
* User attracting qualities (e.g. color, timer).
* Saving the game file by updating a text file with the information of the game.
  - Separate files make it easier to see separate information. 
 
# Topics Covered During the Semester

### Arrays
The “board” that is displayed is a 2D array. Questions and answers will also be located in separate arrays, in which the index of the question has its answer in its corresponding index.

### ArrayList
While the user is creating their own Jeopardy game, everything will be done through the use of an ArrayList, efficiently utilizing ArrayList's expand method.
* The ArrayList will be converted into an array after the game board is finalized.

### Inheritance
The game will incorporate inheritance by having a superclass of game that would branch out to different subjects, for example, math would be one branch and science would be another.
* Abstract superclass Game would have non-abstract methods to calculate points, overwritten toString to display the questions, sort methods to rank people (explained below). We will also have a method for comparing the user input to the correct answer.
* Math, for example, would be a subclass to abstract superclass Game.

### Iteration
For every single time the user chooses and answers a question, the board will be re-displayed but with that question already answered. While the board still has at least one question still not chosen, the game will still be displayed until all questions have been answered. Once board has no more questions remaining, it will go into All or Nothing! or Bet!

### Sort
Following each round , the scoreboard will be printed, in which each player’s names will be listed in order based on their scores. This will be done through the use of early exit bubble sort, because the points following each round should be marginally close to the previous round’s points.
