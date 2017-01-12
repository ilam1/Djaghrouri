public class Misc extends Woo{
    // instance variables
    public static String[] questions = {"q1","q2","q3","q4","q5"};
    public static String[] answers ={"a1","a2","a3","a4","a5"};
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
