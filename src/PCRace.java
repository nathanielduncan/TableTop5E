import java.util.ArrayList;

public class PCRace {
    String name;
    ArrayList<ASI> asi = new ArrayList<ASI>();
    Integer age;
    Integer size;
    Integer speed;
    String languages;
    String  subrace;

    public PCRace(String line) {//Constructor
        ArrayList<String> tokens = doWork.getTokens(line, " ");//Gets all tokens for the Race
        name = tokens.get(0);//First token is name

        //Second token is for all ASI
        ArrayList<String> asiTokens = doWork.getTokens(tokens.get(1), ",");//Gets all tokens that have ACI
        for(int i = 0; i < asiTokens.size(); i++) {                         //For each ACI modification
            ArrayList<String> pair = doWork.getTokens(asiTokens.get(i), ":");//Split the ability and modifier
            asi.add(new ASI(pair.get(0), pair.get(1)));                     //Add ACI to class
        }

        age = 0;
        size = 0;
        speed = 0;
        languages = "none";
        subrace = "none";
    }

    public String printRace() {//Makes a string that represents all race data
        String print = name + "\n";
        for(int i = 0; i < asi.size(); i++) {
            print = print + asi.get(i).ability + "+" + asi.get(i).modifier;
            if((i+1) < asi.size()) {
                print = print + ", ";
            }
        }


        return print;
    }

}


