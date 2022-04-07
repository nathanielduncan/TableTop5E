import PCClass.ASI;

import java.util.ArrayList;

public class PCRace {
    String name;
    ArrayList<ASI> asi = new ArrayList<ASI>();
    String age;
    String sizeDesc;
    Integer speed;
    String languages;
    String  subrace;

    enum Size {
        Tiny, Small, Medium, Large, Huge, Gargantuan, Unset;
    }
    Size size = Size.Unset;

    public PCRace(String line) {//Constructor
        try {
            ArrayList<String> tokens = doWork.getTokens(line, ";");//Gets all tokens for the Race
            name = tokens.get(0);//First token is name

            //Second token is for all PCClass.ASI
            ArrayList<String> asiTokens = doWork.getTokens(tokens.get(1), ",");//Gets all tokens that have ACI
            for(int i = 0; i < asiTokens.size(); i++) {                         //For each ACI modification
                ArrayList<String> pair = doWork.getTokens(asiTokens.get(i), ":");//Split the ability and modifier
                asi.add(new ASI(pair.get(0), pair.get(1)));                     //Add ACI to class
            }

            age = tokens.get(2);
            sizeDesc = tokens.get(3);//Needs to be converted to enum at some point
            speed = Integer.parseInt(tokens.get(4));//Reads as string, stored as int
            languages = tokens.get(5);
            subrace = tokens.get(6);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("PCRace constructor was called with the incorrect parameters. Empty Race created.");
            name = "none";
            age = "none";
            sizeDesc = "none";
            speed = 0;
            languages = "none";
            subrace = "none";
        }

    }

    public String printRace() {//Makes a string that represents all race data
        String print = name + "\n";
        for(int i = 0; i < asi.size(); i++) {
            print = print + asi.get(i).getAbility() + "+" + asi.get(i).getModifier();
            if((i+1) < asi.size()) {
                print = print + ", ";
            }
        }
        return print;
    }

    public String printASI() {
        String print = "";
        for(int i = 0; i < asi.size(); i++) {
            print = print + asi.get(i).getAbility() + "+" + asi.get(i).getModifier();
            if((i+1) < asi.size()) {
                print = print + ", ";
            }
        }
        return print;
    }

}


