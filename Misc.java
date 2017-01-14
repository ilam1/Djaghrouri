public class Misc extends Woo{
    // instance variables
    public static String[] questions = {"What is the best month for a parade?",
	    "What gets whipped and beaten without ever squealing?",
	    "What has a foot on the left, a foot on the right, and a foot in between?","What is it that you can keep after you have given it away?",
	    "I always run; I never walk.\nI often murmur, but I never talk.\nI have a bed, but I never sleep;\nI have a mouth, but I never eat."};
    public static String[] answers ={"March","egg","yardstick","your word","river"};
    // Methods begin
   /* public String riddleQ(){
    }
    public String riddleA(){
    }
    public String punQ(){
    }
    public String punA(){
    }
    public String brainTeaserQ(){
    }
    public String brainTeaserA(){
    }*/
    public String logicQ(){
	return "logicQ";
    }
    public String logicA(){
	return "logicA";
    }
     public String[] popQ(){
	questions[0]= logicQ();
	return questions;
    }
    public String[] popA(){
	answers[0]=logicA();
	return answers;
    }
    
    
}
