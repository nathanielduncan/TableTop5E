package CreateCharacter;

import Data.AbilityScore;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AbilityScoreBox extends JPanel {
    JLabel title;
    JTextField score;
    JLabel modifier;
    AbilityScore containedAbility;

    public AbilityScoreBox(AbilityScore abilityScore) {
        containedAbility = abilityScore;

        ArcCornerBorder border1 = new ArcCornerBorder();//Instance of custom Border
        Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);//Instance of empty Border
        this.setBorder(BorderFactory.createCompoundBorder(border2, border1));//Assign the combined 2 borders to the score box
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Items added vertically

        title = new JLabel(containedAbility.getAbility());//Add title label
        title.setAlignmentX(0.5F);//Item aligns center
        this.add(title);

        score = new JTextField();//Add textField for label
        score.setAlignmentX(0.5F);//Item aligns center
        score.setHorizontalAlignment(JTextField.CENTER);
        score.setMaximumSize(this.getPreferredSize());
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