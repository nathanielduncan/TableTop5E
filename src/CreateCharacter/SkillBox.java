package CreateCharacter;

import Data.AbilityScore;
import Data.Skill;

import javax.swing.*;
import java.awt.*;

public class SkillBox extends JPanel {
    JLabel scoreBox;
    JRadioButton prof;
    JLabel title;
    Skill containedSkill;
    AbilityScore containedAbility;

    public SkillBox(Skill skill) {//Defines how items in the skill box looks
        containedSkill = skill;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));//Items added from left to right
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Add radio button
        prof = new JRadioButton();//Radio button marks if player is proficient in that skill
        this.add(prof);//Add to box

        this.add(Box.createRigidArea(new Dimension(5,0)));//Add a space

        //Add Label for the skill bonus
        scoreBox = new JLabel("--");
        scoreBox.setBackground(Color.white);//Show background as white
        scoreBox.setOpaque(true);//Makes the background viewable
        this.add(scoreBox);//Add to box

        this.add(Box.createRigidArea(new Dimension(15,0)));//Add a space

        //Add label for the skill name
        this.title = new JLabel(skill.getSkill() + " (" + skill.getAbility().substring(0,3) + ")");//Get the skill name and the corresponding
        this.add(title);
    }

    public SkillBox(AbilityScore score) {//defines how items in the saving throws box looks
        containedAbility = score;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));//Items added from Left to Right
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Add radio button
        prof = new JRadioButton();//Radio button marks if player is proficient in that skill
        this.add(prof);//Add to box

        this.add(Box.createRigidArea(new Dimension(5,0)));//Add a space

        //Add Label for the skill bonus
        scoreBox = new JLabel("--");
        scoreBox.setBackground(Color.white);//Show background as white
        scoreBox.setOpaque(true);//Makes the background viewable
        this.add(scoreBox);//Add to box

        this.add(Box.createRigidArea(new Dimension(15,0)));//Add a space

        //Add label for the skill name
        this.title = new JLabel(score.getAbility());//Get the skill name and the corresponding
        this.add(title);
    }

    public SkillBox(String name) {//This is for the proficiency bonus box, it does not have a radio button
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));//Items added from Left to Right
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Add Label for the skill bonus
        scoreBox = new JLabel("2");
        scoreBox.setBackground(Color.white);//Show background as white
        scoreBox.setOpaque(true);//Makes the background viewable
        this.add(scoreBox);//Add to box

        this.add(Box.createRigidArea(new Dimension(15,0)));//Add a space

        //Add label for the skill name
        this.title = new JLabel(name);//Uses arg from function call
        this.add(title);
    }

    public Skill getContainedSkill() {return containedSkill;}
    public AbilityScore getContainedAbility() {return  containedAbility;}
    public void setScoreBoxText(String score) {this.scoreBox.setText(score);}
    public JRadioButton getProf() {return prof;}
    public JLabel getScoreBox() {return scoreBox;}
}
