public class History extends Woo{
    //instance variables
    public static String[] questions = {"q1","q2","q3","q4","q5"};
    public static String[] answers ={"a1","a2","a3","a4","a5"};
    //Methods begin
    /* public String presidentsQ(){
    }
    public String presidentsA(){
    }
    public String geographyQ(){
    }
    public String geographyA(){
    }
    public String constitutionQ(){
    }
    public String constitutionA(){
    }
    public String warsQ(){
    }
    public String warsA(){
    }*/
    public String empireQ(){
	return "empireQ";
    }
    public String empireA(){
	return "empireA";
    }
     public String[] popQ(){
	questions[0]= empireQ();
	return questions;
    }
    public String[] popA(){
	answers[0]=empireA();
	return answers;
    }
}
