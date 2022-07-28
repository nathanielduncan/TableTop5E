package Data;

public class AbilityScore {
    String ability;
    int score;
    int modifier;
    int raceBonus;

    public AbilityScore(String name) {
        ability = name;
    }

    //Getters and Setters
    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public int getRaceBonus() {
        return raceBonus;
    }

    public void setRaceBonus(int raceBonus) {
        this.raceBonus = raceBonus;
    }
}
