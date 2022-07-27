package CreateCharacter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SkillBox extends JPanel {
    JLabel scoreBox;
    public SkillBox(String skill) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));//Items added from top to bottom

        if (!Objects.equals(skill, "Proficiency Bonus")) {//Add the radio button only if it is not the proficiency bonus box
            //Add radio button
            JRadioButton prof = new JRadioButton();//Radio button marks if player is proficient in that skill
            prof.addActionListener(a -> profButtonMarked());
            this.add(prof);//Add to box

            this.add(Box.createRigidArea(new Dimension(5,0)));//Add a space
        }

        //Add Label for the skill bonus
        scoreBox = new JLabel("--");
        scoreBox.setBackground(Color.white);//Show background as white
        scoreBox.setOpaque(true);//Makes the background viewable
        this.add(scoreBox);//Ad to box

        this.add(Box.createRigidArea(new Dimension(15,0)));//Add a space

        //Add label for the skill name
        JLabel label = new JLabel(skill);//Uses arg from function call
        this.add(label);

        this.add(Box.createHorizontalGlue());//Glue here on the right side makes sure all extra space is added to the end of the box
    }

    private void profButtonMarked() {
        // TODO: Put something here, needs to add proficiency to score
    }

    public void setScoreBox(int score) {
        this.scoreBox.setText(String.valueOf(score));
    }
}
