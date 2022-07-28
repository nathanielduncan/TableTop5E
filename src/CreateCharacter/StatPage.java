package CreateCharacter;

import Data.CharacterAttributes;
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
        for (CharacterAttributes.AbilityScores score : CharacterAttributes.AbilityScores.values()) {
            AbilityScoreBox temp = new AbilityScoreBox(score.toString());

            temp.score.addActionListener(e -> fillSkills());//When a score is entered, it fills all the corresponding skills

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
        ArrayList<Skill> skills = dataAccess.getSkills();//Get a list of skill objects from the database
        for (Skill skill : skills) {//for each skill from the database
            SkillBox temp = new SkillBox(skill);//make a skill box

            skillsPane.add(temp);
        }
        makeSkillActions();//Add actions to all the radioButtons inside the skillsPane
        rightPane.add(skillsPane);

    }

    private void fillSkills() {
        //Fill skill modifiers in
        Component[] components = skillsPane.getComponents();//Get a list of all skillBoxes from the skillsPane
        for (Component component : components) {//For each item in the skills pane
            if (component instanceof SkillBox box) {//If component is a skillBox
                int bonus = 5;//TODO set this value to the corresponding ability score
                box.setScore(bonus);
            }
        }
    }

    private void makeSkillActions() {
        Component[] items = skillsPane.getComponents();//Gets all components inside the skillsPane
        for (Component temp : items) {
            if (temp instanceof SkillBox skill) {
                skill.getProf().addActionListener(e -> fillSkills());
            }
        }
    }

    //getters and setters
    public Component[] getScoreBoxes() {//Returns all the components inside the scoresPane, should only be 6 scoreBoxes
        return scoresPane.getComponents();
    }
}
