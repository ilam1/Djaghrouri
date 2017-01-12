public class History extends Woo{
    //instance variables
 public static String[] questions = new String[2];
    public static String[] answers =new String[2];
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
