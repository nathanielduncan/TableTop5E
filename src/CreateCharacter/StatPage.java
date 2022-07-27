package CreateCharacter;

import Data.CharacterAttributes;

import javax.swing.*;
import java.awt.*;

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
        for (CharacterAttributes.AbilityScores score : CharacterAttributes.AbilityScores.values()) {
            AbilityScoreBox temp = new AbilityScoreBox(score.toString());

            temp.score.addActionListener(e -> scoreAdded());//One of two action functions for when a score is entered. This one fills in skill information.

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
        for (CharacterAttributes.AbilityScores score : CharacterAttributes.AbilityScores.values()) {
            SkillBox temp = new SkillBox(String.valueOf(score));

            savingThrowsPane.add(temp);
        }
        rightPane.add(savingThrowsPane);

        rightPane.add(Box.createRigidArea(new Dimension(0,20)));//Add a space

        //Skills box
        skillsPane = new JPanel();//Defined outside function so it can be used elsewhere
        skillsPane.setLayout(new BoxLayout(skillsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added vertically
        skillsPane.setBorder(new ArcCornerBorder());
        for (CharacterAttributes.AllSkills skill : CharacterAttributes.AllSkills.values()) {
            SkillBox temp = new SkillBox(String.valueOf(skill));

            skillsPane.add(temp);
        }
        rightPane.add(skillsPane);

    }

    private void scoreAdded() {
        //Fill skill modifiers in
        Component[] skills = skillsPane.getComponents();//Get a list of all skillBoxes from the skillsPane
        for (Component skill : skills) {//For each listed skill
            ((SkillBox) skill).setScoreBox(1);//Set the skill bonus equal to TODO get value of modifier from related ability score
        }
    }

    public Component[] getScoreBoxes() {//Returns all the components inside the scoresPane, should only be 6 scoreBoxes
        return scoresPane.getComponents();
    }
}
