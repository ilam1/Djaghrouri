public class History extends Category{
    //instance variables
    public static String[] questions = {"This process turns iron into steel.","This French term refers to the American policy towards business in the late 1800's,meaning 'hands off'","This man is the one who brought the Ten Commandments to the Jews.","Which explorer circumnavigated the world?(First and Last name)","Which renaissance writer was best known for Praise of Folly?"};
    public static String[] answers ={"Bessemer process","laissez-Faire","Moses","Ferdinand Megellan","Erasmus"};
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
