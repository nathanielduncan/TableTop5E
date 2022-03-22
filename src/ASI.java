public class ASI {//Ability Score Increase
    String ability;
    Integer modifier;

    public ASI(String a, String m) {//Constructor
        ability = a;
        modifier = Integer.parseInt(m);
    }
}
