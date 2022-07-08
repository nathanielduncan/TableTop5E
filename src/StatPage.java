import javax.swing.*;
import java.awt.*;

public class StatPage extends JPanel {
    JPanel scoresPane = new JPanel();//Panel on the left will hold ability scores and modifiers
    JPanel rightPane = new JPanel();//Holds proficiency bonus,saving throws, and skills <

    public StatPage() {
        JPanel contentPane = new JPanel();//One pane to rule them all
        this.add(contentPane);

        scoresPane.setLayout(new BoxLayout(scoresPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        makeScoresPane();
        contentPane.add(scoresPane);

        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        makeRightPane();//Puts other panels inside this one for three things listed above ^
        contentPane.add(rightPane);

    }

    private void makeScoresPane() {
        scoresPane.add(new JLabel("Strength score"));
        scoresPane.add(new JLabel("Dexterity score"));
        scoresPane.add(new JLabel("Constitution score"));
        scoresPane.add(new JLabel("Intelligence score"));
        scoresPane.add(new JLabel("Wisdom score"));
        scoresPane.add(new JLabel("Charisma score"));
    }

    private void makeRightPane() {//Makes panes for proficiency bonus,saving throws, and skills
        JPanel profBonusPane = new JPanel();
        profBonusPane.add(new JLabel("Proficiency Bonus"));
        rightPane.add(profBonusPane);

        rightPane.add(Box.createRigidArea(new Dimension(0,50)));

        JPanel savingThrowsPane = new JPanel();
        savingThrowsPane.setLayout(new BoxLayout(savingThrowsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        savingThrowsPane.add(new JLabel("Strength"));
        savingThrowsPane.add(new JLabel("Dexterity"));
        savingThrowsPane.add(new JLabel("Constitution"));
        savingThrowsPane.add(new JLabel("Intelligence"));
        savingThrowsPane.add(new JLabel("Wisdom"));
        savingThrowsPane.add(new JLabel("Charisma"));
        rightPane.add(savingThrowsPane);

        rightPane.add(Box.createRigidArea(new Dimension(0,50)));

        JPanel skillsPane = new JPanel();
        skillsPane.setLayout(new BoxLayout(skillsPane, BoxLayout.Y_AXIS));//Buttons and labels panels added horizontally
        skillsPane.add(new JLabel("Acrobatics"));
        skillsPane.add(new JLabel("Animal Handling"));
        skillsPane.add(new JLabel("Arcana"));
        skillsPane.add(new JLabel("Athletics"));
        skillsPane.add(new JLabel("Deception"));
        skillsPane.add(new JLabel("History"));
        skillsPane.add(new JLabel("Insight"));
        skillsPane.add(new JLabel("Intimidation"));
        skillsPane.add(new JLabel("Investigation"));
        skillsPane.add(new JLabel("Medicine"));
        skillsPane.add(new JLabel("Nature"));
        skillsPane.add(new JLabel("Perception"));
        skillsPane.add(new JLabel("Performance"));
        skillsPane.add(new JLabel("Persuasion"));
        skillsPane.add(new JLabel("Religion"));
        skillsPane.add(new JLabel("Sleight of Hand"));
        skillsPane.add(new JLabel("Stealth"));
        skillsPane.add(new JLabel("Survival"));
        rightPane.add(skillsPane);


    }
}
