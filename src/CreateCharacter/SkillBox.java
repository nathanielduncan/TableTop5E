package CreateCharacter;

import Data.Skill;

import javax.swing.*;
import java.awt.*;

public class SkillBox extends JPanel {
    JLabel scoreBox;
    JRadioButton prof;
    JLabel title;

    public SkillBox(Skill skill) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));//Items added from top to bottom

        //Add radio button
        prof = new JRadioButton();//Radio button marks if player is proficient in that skill
        this.add(prof);//Add to box

        this.add(Box.createRigidArea(new Dimension(5,0)));//Add a space

        //Add Label for the skill bonus
        scoreBox = new JLabel("--");
        scoreBox.setBackground(Color.white);//Show background as white
        scoreBox.setOpaque(true);//Makes the background viewable
        this.add(scoreBox);//Ad to box

        this.add(Box.createRigidArea(new Dimension(15,0)));//Add a space

        //Add label for the skill name
        this.title = new JLabel(skill.getSkill() + " (" + skill.getAbility().substring(0,3) + ")");//Get the skill name and the corresponding
        this.add(title);

        this.add(Box.createHorizontalGlue());//Glue here on the right side makes sure all extra space is added to the end of the box
    }

    public SkillBox(String name) {//This is for the proficiency bonus box, it does not have a radio button
        //Add Label for the skill bonus
        scoreBox = new JLabel("2");
        scoreBox.setBackground(Color.white);//Show background as white
        scoreBox.setOpaque(true);//Makes the background viewable
        this.add(scoreBox);//Ad to box

        this.add(Box.createRigidArea(new Dimension(15,0)));//Add a space

        //Add label for the skill name
        this.title = new JLabel(name);//Uses arg from function call
        this.add(title);

        this.add(Box.createHorizontalGlue());//Glue here on the right side makes sure all extra space is added to the end of the box

    }

    //Getters and Setters
    public JRadioButton getProf() {
        return prof;
    }
    public void setScore(int score) {
        this.scoreBox.setText(String.valueOf(score));
    }
}
