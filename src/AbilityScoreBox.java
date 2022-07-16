import javax.swing.*;
import java.awt.*;

public class AbilityScoreBox extends JPanel {
    JLabel title;
    JTextField score;
    JLabel modifier;

    public AbilityScoreBox(String t) {
        this.setBorder(new ArcCornerBorder());//Custom Border
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Items added vertically

        title = new JLabel(t);//Add title label
        title.setAlignmentX(0.5F);//Item aligns center
        this.add(title);

        score = new JTextField();//Add textField for label
        score.setAlignmentX(0.5F);//Item aligns center
        score.setHorizontalAlignment(JTextField.CENTER);
        score.addActionListener(e -> scoreEntered());
        this.add(score);

        modifier = new JLabel("-");//Add Label for modifier
        modifier.setAlignmentX(0.5F);//Item aligns center
        modifier.setBorder(new ArcCornerBorder(Color.DARK_GRAY));//Has its own border
        this.add(modifier);
    }

    private void scoreEntered() {
        try {
            int scor = Integer.parseInt(score.getText());
            if (scor >= 1 && scor <= 30) {//Must be between 1 and 30
                modifier.setText(String.valueOf(getMod(scor)));
            } else {
                System.out.println("Please enter a number between 1 and 30.");          //TODO make error show in UI
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number between 1 and 30.");               //TODO make error show in UI
        }
    }

    private int getMod(int score) {
        int mod = score - 10;
        if (mod < 0) {//Accounts for rounding with negative numbers
            return (mod - 1) / 2;
        } else { //zero or higher
            return mod / 2;
        }
    }
}