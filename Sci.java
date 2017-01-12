public class Sci extends Woo{

    //Instance Variables
    public static String[] questions = new String[2];
    public static String[] answers =new String[2];

    //Methods begin: 

    /*   public String forceQ() {
    }
    public String forceA() {
    }

    public String metricConversionsQ() {
    }
    public String metricConversionsQ() {
    }

    public String acidsBaseQ() {
    }
    public String acidsBaseA() {
    }

    public String organSystemsQ() {
    }
    public String organSystemsA() {
    }

    public String bodyPartsQ() {
    }
    public String bodyPartsA() {
    }*/

    public String periodicTableQ() {
	return "periodicTableQ";
    }
    public String periodicTableA() {
	return "periodicTableA";
    }
     public String[] popQ(){
	questions[0]= periodicTableQ();
	return questions;
    }
    public String[] popA(){
	answers[0]=periodicTableA();
	return answers;
    }

}

