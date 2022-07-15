import Data.CharacterAttributes;

import javax.swing.*;
import java.awt.*;

public class StatPage extends JPanel {
    JPanel scoresPane = new JPanel();//Panel on the left will hold ability scores and modifiers
    JPanel rightPane = new JPanel();//Holds proficiency bonus,saving throws, and skills <

    public StatPage() {
        JPanel contentPane = new JPanel();//One pane to rule them all
        this.add(contentPane);

        scoresPane.setLayout(new BoxLayout(scoresPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        scoresPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        makeScoresPane();
        contentPane.add(scoresPane);

        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        rightPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        makeRightPane();//Puts other panels inside this one for three things listed above ^
        contentPane.add(rightPane);

    }

    private void makeScoresPane() {
        for (CharacterAttributes.AbilityScores score : CharacterAttributes.AbilityScores.values()) {
            JLabel temp = new JLabel(String.valueOf(score));
            temp.setAlignmentX(0.5f);

            scoresPane.add(temp);
        }
    }

    private void makeRightPane() {//Makes panes for proficiency bonus,saving throws, and skills
        //Proficiency bonus box
        JPanel profBonusPane = new JPanel();
        profBonusPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        profBonusPane.add(new JLabel("Proficiency Bonus"));
        rightPane.add(profBonusPane);

        rightPane.add(Box.createRigidArea(new Dimension(0,50)));//Add a space

        //Saving throws box
        JPanel savingThrowsPane = new JPanel();
        savingThrowsPane.setLayout(new BoxLayout(savingThrowsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        savingThrowsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (CharacterAttributes.AbilityScores score : CharacterAttributes.AbilityScores.values()) {
            JLabel temp = new JLabel(String.valueOf(score));
            temp.setAlignmentX(0.5f);

            savingThrowsPane.add(temp);
        }
        rightPane.add(savingThrowsPane);

        rightPane.add(Box.createRigidArea(new Dimension(0,50)));//Add a space

        //Skills box
        JPanel skillsPane = new JPanel();
        skillsPane.setLayout(new BoxLayout(skillsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        skillsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for (CharacterAttributes.Skills skill : CharacterAttributes.Skills.values()) {
            JLabel temp = new JLabel(String.valueOf(skill));
            temp.setAlignmentX(0.5f);

            skillsPane.add(temp);
        }
        rightPane.add(skillsPane);


    }
}
