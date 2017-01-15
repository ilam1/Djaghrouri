public class Sci extends Woo{

    //Instance Variables
    public static String[] questions = {"How much force does it take to accelerate a 5 kg object at 4 m/s^2? (in Newtons)",
                                        "What is 9000 km/h in mi/s?",
                                        "In the reaction between an acid and a base, the pH changes to a value closer to what?",
                                        "How many known elements are there on the periodic table?",
                                        "What is the LARGEST organ in the human body?"};
    public static String[] answers ={"20 N",
                                     "1.5 mi/s",
                                     "7",
                                     "118",
                                     "Skin"};

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
