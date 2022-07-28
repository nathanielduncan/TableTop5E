package CreateCharacter;

import Data.AbilityScore;
import Data.Skill;
import Data.dataAccess;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class StatPage extends JPanel {
    JPanel scoresPane = new JPanel();//Panel on the left will hold ability scores and modifiers
    JPanel rightPane = new JPanel();//Holds proficiency bonus,saving throws, and skills
    JPanel skillsPane;

    public StatPage() {
        scoresPane.setLayout(new BoxLayout(scoresPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        makeScoresPane();
        this.add(scoresPane);

        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        makeRightPane();//Puts other panels inside this one for three things listed above ^
        this.add(rightPane);
    }

    private void makeScoresPane() {
        ArrayList<AbilityScore> abilityScores = dataAccess.getScores();
        assert abilityScores != null : "No ability scores were found in the database, when making the scoresPane on the StatPage";
        for (AbilityScore score : abilityScores) {
            AbilityScoreBox temp = new AbilityScoreBox(score.getAbility());

            scoresPane.add(temp);
            scoresPane.add(Box.createVerticalGlue());//Add glue between each box, so that extra space moves them away from each other
        }
    }

    private void makeRightPane() {//Makes panes for proficiency bonus,saving throws, and skills
        //Proficiency bonus box
        SkillBox profBonusPane = new SkillBox("Proficiency Bonus");//Won't make a radio button
        profBonusPane.setBorder(new ArcCornerBorder());
        rightPane.add((profBonusPane));

        rightPane.add(Box.createRigidArea(new Dimension(0,20)));//Add a space

        //Saving throws box
        JPanel savingThrowsPane = new JPanel();
        savingThrowsPane.setLayout(new BoxLayout(savingThrowsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        savingThrowsPane.setBorder(new ArcCornerBorder());
        ArrayList<AbilityScore> abilityScores = dataAccess.getScores();
        assert abilityScores != null : "No ability scores were found in the database, when making the saving throws box on the StatPage";
        for (AbilityScore score : abilityScores) {
            SkillBox temp = new SkillBox(score.getAbility());

            savingThrowsPane.add(temp);
        }
        rightPane.add(savingThrowsPane);

        rightPane.add(Box.createRigidArea(new Dimension(0,20)));//Add a space

        //Skills box
        skillsPane = new JPanel();//Defined outside function so it can be used elsewhere
        skillsPane.setLayout(new BoxLayout(skillsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        skillsPane.setBorder(new ArcCornerBorder());
        ArrayList<Skill> skills = dataAccess.getSkills();//Get a list of skill objects from the database
        assert skills != null : "No skills were found in the database while making the skillsBox on the StatPage";
        for (Skill skill : skills) {//for each skill from the database
            SkillBox temp = new SkillBox(skill);//make a skill box

            skillsPane.add(temp);
        }
        rightPane.add(skillsPane);
    }

    //getters and setters
    public Component[] getScoreBoxes() {//Returns all the components inside the scoresPane, should only be 6 scoreBoxes
        return scoresPane.getComponents();
    }
}
