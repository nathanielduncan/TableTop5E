package Data;

public class Skill extends AbilityScore{
    boolean proficient;
    String title;
    int bonus;

    public Skill(String skill, String ability) {
        super(ability);
        title = skill;
    }

    public String getSkill() {
        return title;
    }
}
