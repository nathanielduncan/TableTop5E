package CreateCharacter;

import Data.PlayerCharacter;
import Data.dataAccess;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Enumeration;

public class CreateCharacterWindow extends JFrame {
    //Container panes
    JPanel pagePane = new JPanel();//This pane holds whatever page the user is currently on
    JPanel buttonPane = new JPanel();//This pane is not dependent on what page the user is on

    //Different pages
    RCBPage rcb = new RCBPage();//JTabbedPane where you select race, class, and background
    StatPage sp = new StatPage();//JPanel where you select 6 stats and proficiencies

    //Character Object
    PlayerCharacter character = new PlayerCharacter();

    public CreateCharacterWindow() {
        JPanel contentPane = new JPanel();//One pane to rule them all
        contentPane.setLayout(new BorderLayout());
        this.add(contentPane);

        contentPane.add(pagePane, BorderLayout.CENTER);//Add page Pane
        contentPane.add(buttonPane, BorderLayout.PAGE_END);//Add button pane

        pagePane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        pagePane.setLayout(new BoxLayout(pagePane, BoxLayout.X_AXIS));
        contentPane.add(pagePane);
        makeRCBPageActions();//Make actions to store PC race, class, and background as they are chosen

        //Make a Prev button to change panes back
        JButton prev = new JButton("Prev");
        prev.addActionListener(e -> showPage1());
        buttonPane.add(prev);
        //Make a Next button to change panes
        JButton next = new JButton("Next");
        next.addActionListener(e -> showPage2());
        buttonPane.add(next);
        //Make a submit button for race-class-background content
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> submitButtonClicked());
        buttonPane.add(submit);

        pagePane.add(rcb);
        pagePane.add(sp);

        showPage1();
    }

    public void showPage1() {
        Component[] pages = pagePane.getComponents();
        for (Component page : pages) {
                page.setVisible(false);//Hides all pages from view
        }
        rcb.setVisible(true);//Make the page we want visible

        buttonPane.getComponent(0).setEnabled(false);//Prev button turns off
        buttonPane.getComponent(1).setEnabled(true);//Next button turns on
    }

    public void showPage2() {
        Component[] pages = pagePane.getComponents();//Get all the pages
        for (Component page : pages) {//For each page
            page.setVisible(false);//Hide the page from view
        }
        sp.setVisible(true);//Make the page we want visible

        buttonPane.getComponent(0).setEnabled(true);//Prev button turns on
        buttonPane.getComponent(1).setEnabled(false);//Next button turns off
    }

    private void makeRCBPageActions() {
        //For each button group in race,class, and background page, set all buttons to have and action to store itself in the PC Object
        //For all the class buttons
        for(Enumeration<AbstractButton> buttons = rcb.getButtonGroupClass().getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.addActionListener(e -> character.setRCBByName("Class", button.getText()));
        }//For all the race buttons
        for(Enumeration<AbstractButton> buttons = rcb.getButtonGroupRace().getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.addActionListener(e -> character.setRCBByName("Race", button.getText()));
        }//For all the background buttons
        for(Enumeration<AbstractButton> buttons = rcb.getButtonGroupBackG().getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.addActionListener(e -> character.setRCBByName("Background", button.getText()));
        }
    }

    private void checkStatBoxes() {//When a score is entered, it gets saved to the character
        Component[] skillBoxes = sp.getScoreBoxes();
        for (Component temp : skillBoxes) {
            if (temp instanceof AbilityScoreBox box) {//If item can't be cast as CreateCharacter.AbilityScoreBox (Glue and spacer items) it is skipped
                JTextField score = box.getScore();//Get the score box
                character.setScoreByName(box.getTitle(), score.getText());//Call this function when a score is entered, using the score from the box and the title of it from the label
            }
        }
    }

    private void submitButtonClicked() {//Writes to the DB when creation is done
        //Check all skill boxes to see if they are filled
        checkStatBoxes();

        if (character.isCharFull()) {
            dataAccess.putNewCharacter(character);
        }
        else {
            System.out.println("Not all items have a selection.");//TODO: catch error better with a warning in the UI
        }
    }
}
