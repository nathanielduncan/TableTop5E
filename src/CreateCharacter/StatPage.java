package CreateCharacter;

import Data.AbilityScore;
import Data.Skill;
import Data.dataAccess;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static CreateCharacter.DefineChangeListener.addChangeListener;


public class StatPage extends JPanel {
    JPanel scoresPane = new JPanel();//Panel on the left will hold ability scores and modifiers
    JPanel rightPane = new JPanel();//Holds proficiency bonus, saving throws, and skills
    SkillBox profBonusBox;
    JPanel savingThrowsPane;
    JPanel skillsPane;

    public StatPage() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));//Set the layout manager for the statPage. Two panes will be added in line horizontally.

        scoresPane.setLayout(new BoxLayout(scoresPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        scoresPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//Creates spacer border around the pane
        makeScoresPane();
        this.add(scoresPane);

        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        rightPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//Creates spacer border around the pane
        makeRightPane();//Puts other panels inside this one for proficiency bonus, saving throws, and skills
        this.add(rightPane);

        this.add(Box.createHorizontalGlue());//Add glue between the left pane and the right pane
    }

    private void makeScoresPane() {
        ArrayList<AbilityScore> abilityScores = dataAccess.getScores();
        assert abilityScores != null : "No ability scores were found in the database, when making the scoresPane on the StatPage";
        for (AbilityScore score : abilityScores) {
            AbilityScoreBox temp = new AbilityScoreBox(score);
            addChangeListener(temp.getScore(), e -> fillSkills(temp));//Custom listener, when the score box is added to, removed from, or changed the skills respond

            scoresPane.add(temp);
        }
        scoresPane.add(Box.createVerticalGlue());
    }

    private void makeRightPane() {//Makes panes for proficiency bonus,saving throws, and skills
        //Two borders defined to be used for each of the three panes
        ArcCornerBorder border1 = new ArcCornerBorder();//Instance of custom Border
        Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);//Instance of empty Border

        //Proficiency bonus box
        profBonusBox = new SkillBox("Proficiency Bonus");//Won't make a radio button
        profBonusBox.setBorder(BorderFactory.createCompoundBorder(border2, border1));//Assign the combined 2 borders to the score box
        rightPane.add((profBonusBox));

        //Saving throws box
        savingThrowsPane = new JPanel();
        savingThrowsPane.setLayout(new BoxLayout(savingThrowsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        savingThrowsPane.setBorder(BorderFactory.createCompoundBorder(border2, border1));//Assign the combined 2 borders to the score box
        ArrayList<AbilityScore> abilityScores = dataAccess.getScores();
        assert abilityScores != null : "No ability scores were found in the database, when making the saving throws box on the StatPage";
        for (AbilityScore score : abilityScores) {
            SkillBox temp = new SkillBox(score);
            temp.getProf().addActionListener(e -> makeSkillProfAction(temp));

            savingThrowsPane.add(temp);
        }
        rightPane.add(savingThrowsPane);

        //Skills box
        skillsPane = new JPanel();//Defined outside function so it can be used elsewhere
        skillsPane.setLayout(new BoxLayout(skillsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        skillsPane.setBorder(BorderFactory.createCompoundBorder(border2, border1));//Assign the combined 2 borders to the score box
        ArrayList<Skill> skills = dataAccess.getSkills();//Get a list of skill objects from the database
        assert skills != null : "No skills were found in the database while making the skillsBox on the StatPage";
        for (Skill skill : skills) {//for each skill from the database
            SkillBox temp = new SkillBox(skill);//make a skill box
            temp.getProf().addActionListener(e -> makeSkillProfAction(temp));//Listener that makes the radio button add the proficiency bonus

            skillsPane.add(temp);//Add it to the Pane
        }
        rightPane.add(skillsPane);
        rightPane.add(Box.createVerticalGlue());
    }

    private void fillSkills(AbilityScoreBox abilityScoreBox) {//is called when an ability score is entered
        //Calculate and get modifier
        try {
            int score = Integer.parseInt(abilityScoreBox.getScore().getText());//Gets the entered score from the box
            if (score >= 1 && score <= 30) {//Must be between 1 and 30
                abilityScoreBox.getModifier().setText(String.valueOf(getMod(score)));//Modifier is calculated in getMod function
            } else {
                System.out.println("Please enter a number between 1 and 30.");          //TODO make error show in UI
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number between 1 and 30.");               //TODO make error show in UI
        }

        //Fills corresponding saving throws
        Component[] saves = savingThrowsPane.getComponents();//Gets all components in the skillsPane
        for (Component save : saves) {//For all the components
            if (save instanceof SkillBox skillBox) {//If it is a skill box
                if (Objects.equals(skillBox.getContainedAbility().getAbility(), abilityScoreBox.getContainedAbility().getAbility())) {//If the skill corresponds to the ability score that was entered
                    skillBox.setScoreBoxText(abilityScoreBox.getModifier().getText());//Set the skill bonus as the calculated modifier from the ability score
                }
            }
        }

        //Fills corresponding skills
        Component[] components = skillsPane.getComponents();//Gets all components in the skillsPane
        for (Component component : components) {//For all the components
            if (component instanceof SkillBox skillBox) {//If it is a skill box
                if (Objects.equals(skillBox.getContainedSkill().getAbility(), abilityScoreBox.getContainedAbility().getAbility())) {//If the skill corresponds to the ability score that was entered
                    skillBox.setScoreBoxText(abilityScoreBox.getModifier().getText());//Set the skill bonus as the calculated modifier from the ability score
                }
            }
        }
    }

    private int getMod(int score) {//calculates the modifier for the given ability score
        int mod = score - 10;
        if (mod < 0) {//Accounts for rounding with negative numbers
            return (mod - 1) / 2;
        } else { //zero or higher
            return mod / 2;
        }
    }

    private void makeSkillProfAction(SkillBox skillBox) {//Called when radio button for skill or saving throw is en/disabled
        try {//Button only works if ability score is entered first
            int score = Integer.parseInt(skillBox.getScoreBox().getText());//Get the relating modifier

            //Add or subtract the prof bonus depending on the state of the button
            if (skillBox.getProf().isSelected()) {//If button is enabled
                score += Integer.parseInt(profBonusBox.getScoreBox().getText());//Add the prof bonus to the skill/save
            } else {//if radioButton is disabled
                score -= Integer.parseInt(profBonusBox.getScoreBox().getText());//Add the prof bonus to the skill/save
            }
            skillBox.setScoreBoxText(String.valueOf(score));//Set skill/save score

        } catch (NumberFormatException e){//If score is not entered, nothing happens
            System.out.println("Ability Score needs to be entered first."); //TODO: make a UI error for this
            skillBox.getProf().setSelected(false);//Unselects the button
        }
    }


    //getters and setters
    public Component[] getScoreBoxes() {//Returns all the components inside the scoresPane, should only be 6 scoreBoxes
        return scoresPane.getComponents();
    }
}
