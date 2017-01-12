public class Math extends Woo{
    //Instance Variables    
    public static String[] questions = new String[2];
    public static String[] answers =new String[2];
    //Methods begin:
    /* public String logQ(){
    
    }
    public int logA(){
    }
    
    public String trigQ(){
	String ret="A right triangle has one side length of";
	ret+= 
    }
    public String trigA(){//answer will be a string in case theres a square root
    }
    
    public String radQ(){
    }
    public String radA(){
    }
   
    public String quadQ(){
    }
    public int quadA(){
    }
    
    public String wordQ(){
    }
    public int wordA(){
    }
   */
    public String circleQ(){
       return "what's a circle";
   }
    public String circleA(){
	return "a shape";
    }
    public String[] popQ(){
	questions[0]= circleQ();
	return questions;
    }
    public String[] popA(){
	answers[0]=circleA();
	return answers;
    }

}
