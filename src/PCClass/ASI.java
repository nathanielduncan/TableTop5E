package PCClass;

public class ASI {//Ability Score Increase
    String ability;
    Integer modifier;

    public ASI(String a, String m) {//Constructor
        ability = a;
        modifier = Integer.parseInt(m);
    }

    public Integer getModifier() {
        return modifier;
    }

    public String getAbility() {
        return ability;
    }
}
