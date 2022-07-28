package Data;

import java.util.Objects;
import java.util.stream.Stream;

public class PlayerCharacter {
    String clas;
    String race;
    String background;

    AbilityScore strength;
    AbilityScore dexterity;
    AbilityScore constitution;
    AbilityScore intelligence;
    AbilityScore wisdom;
    AbilityScore charisma;

    public PlayerCharacter() {
        strength = new AbilityScore("Strength");
        dexterity = new AbilityScore("Dexterity");
        constitution = new AbilityScore("Constitution");
        intelligence = new AbilityScore("Intelligence");
        wisdom = new AbilityScore("Wisdom");
        charisma = new AbilityScore("Charisma");
    }

    public boolean isCharFull() {
        if (Stream.of(clas, race, background)//Checks if selection has been made
                .anyMatch(Objects::isNull)) {return false;}
        //Checks if scores have been changed from zero(initial value)
        if (Stream.of(strength.getScore(), dexterity.getScore(), constitution.getScore(), intelligence.getScore(), wisdom.getScore(), charisma.getScore())
                .anyMatch(n -> n == 0)) {return false;}

        return true;//If everything has been selected, return true
    }

    //Getters and Setters
    public void setRCBByName(String group, String name) {
        switch (group) {//Set the found button text to the correct character attribute
            case "Class" -> this.setClas(name);
            case "Race" -> this.setRace(name);
            case "Background" -> this.setBackground(name);
        }
    }

    public void setScoreByName(String name, String score) {
        System.out.println("Setting " + name + " as " + score);
        switch (name) {
            case "Strength" -> setStrength(Integer.parseInt(score));
            case "Dexterity" -> setDexterity(Integer.parseInt(score));
            case "Constitution" -> setConstitution(Integer.parseInt(score));
            case "Intelligence" -> setIntelligence(Integer.parseInt(score));
            case "Wisdom" -> setWisdom(Integer.parseInt(score));
            case "Charisma" -> setCharisma(Integer.parseInt(score));
            default -> System.out.println("No setter function called");
        }
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getStrength() {
        return strength.getScore();
    }

    public void setStrength(int strength) {this.strength.setScore(strength);}

    public int getDexterity() {
        return dexterity.getScore();
    }

    public void setDexterity(int dexterity) {
        this.dexterity.setScore(dexterity);
    }

    public int getConstitution() {
        return constitution.getScore();
    }

    public void setConstitution(int constitution) {
        this.constitution.setScore(constitution);
    }

    public int getIntelligence() {
        return intelligence.getScore();
    }

    public void setIntelligence(int intelligence) {this.intelligence.setScore(intelligence);}

    public int getWisdom() {
        return wisdom.getScore();
    }

    public void setWisdom(int wisdom) {
        this.wisdom.setScore(wisdom);
    }

    public int getCharisma() {
        return charisma.getScore();
    }

    public void setCharisma(int charisma) {
        this.charisma.setScore(charisma);
    }
}
