package CreateCharacter;

import Data.AbilityScore;

import javax.swing.*;
import java.awt.*;

public class AbilityScoreBox extends JPanel {
    JLabel title;
    JTextField score;
    JLabel modifier;
    AbilityScore containedAbility;

    public AbilityScoreBox(AbilityScore abilityScore) {
        containedAbility = abilityScore;

        this.setBorder(new ArcCornerBorder());//Custom Border
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Items added vertically

        title = new JLabel(containedAbility.getAbility());//Add title label
        title.setAlignmentX(0.5F);//Item aligns center
        this.add(title);

        score = new JTextField();//Add textField for label
        score.setAlignmentX(0.5F);//Item aligns center
        score.setHorizontalAlignment(JTextField.CENTER);
        //score.addActionListener(e -> scoreEntered());//When a score is added the modifier is calculated and placed
        this.add(score);

        modifier = new JLabel("-");//Add Label for modifier
        modifier.setAlignmentX(0.5F);//Item aligns center
        modifier.setBorder(new ArcCornerBorder(Color.DARK_GRAY));//Has its own border
        this.add(modifier);
    }

    public String getTitle() {
        return title.getText();
    }
    public JTextField getScore() {
        return score;
    }
    public AbilityScore getContainedAbility() {return containedAbility;}
    public JLabel getModifier() {return modifier;}
}