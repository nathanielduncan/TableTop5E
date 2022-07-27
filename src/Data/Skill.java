package Data;

public class Skill extends AbilityScore{
    boolean proficient;
    String skill;

    public Skill(String score, String skill) {
        super(score);
        this.skill = skill;
    }
}
