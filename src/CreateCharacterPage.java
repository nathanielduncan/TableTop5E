import Data.*;
import Data.PCClass.*;
import Data.PCRace.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class CreateCharacterPage extends JFrame {
    //Tabbed Pane used as content pane
    JTabbedPane tabPane;

    //Race page
    private JPanel panelRace;
    private JPanel panelRaceButtons;
    private ButtonGroup buttonGroupRace;
    private JPanel panelRaceLabels;
    private final ArrayList<JLabel> raceLabels = new ArrayList<>();

    //Class page
    private JPanel panelClass;
    private JPanel panelClassButtons;
    private ButtonGroup buttonGroupClass;
    private JPanel panelClassLabels;
    private final ArrayList<JLabel> classLabels = new ArrayList<>();

    //Background page
    private JPanel panelBackG;
    private JPanel panelBackGButtons;
    private ButtonGroup buttonGroupBackG;
    private JPanel panelBackGLabels;
    private final ArrayList<JLabel> backGLabels = new ArrayList<>();

    public CreateCharacterPage() {
        JPanel pane = new JPanel();
        this.add(pane);

        tabPane = new JTabbedPane();
        pane.add(tabPane);//Adds TabbedPane to content panel

        createTabs();//Make tabs and all content inside them

        tabPane.add("Class", panelClass);//Add the created panels to the tabs
        tabPane.add("Race", panelRace);
        tabPane.add("Background", panelBackG);

        //Make a submit button for all tabs content
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> submitButtonClicked());
        pane.add(submit);
    }

    private void createTabs() {
        oglDescription oglInfo = new oglDescription();//Gets database info for races and classes

        //Choose class page
        panelClass = new JPanel();//Holds both buttons and labels panels
        panelClass.setLayout(new BoxLayout(panelClass, BoxLayout.X_AXIS));//Buttons and labels panels added horizontally
        panelClass.setAlignmentX(0.0f);//Aligns the labels and buttons to the top, to keep them from moving

        panelClassLabels = new JPanel();//Panel for class labels
        panelClassLabels.setLayout(new BoxLayout(panelClassLabels, BoxLayout.Y_AXIS));//Labels are added vertically
        makeClassLabels(oglInfo.getClassColsNames());//Makes all content in class labelsPane

        panelClassButtons = new JPanel();//Panel for class buttons
        panelClassButtons.setLayout(new BoxLayout(panelClassButtons, BoxLayout.Y_AXIS));//Buttons are added vertically
        buttonGroupClass = new ButtonGroup();//Buttongroup for class buttons
        makeClassButtons(oglInfo.getClassDescs(), oglInfo.getClassColsNames());

        panelClass.add(panelClassButtons);//Add both buttons and labels to the ClassPane
        panelClass.add(Box.createRigidArea(new Dimension(35, 0)));//With a space between to widen the window
        panelClass.add(panelClassLabels);

        //Choose Race page
        panelRace = new JPanel();//Holds both buttons and labels panels
        panelRace.setLayout(new BoxLayout(panelRace, BoxLayout.X_AXIS));//Buttons and labels panels added horizontally
        panelRace.setAlignmentX(0.0f);//Aligns the labels and buttons to the top, to keep them from moving

        panelRaceLabels = new JPanel();//Panel for race labels
        panelRaceLabels.setLayout(new BoxLayout(panelRaceLabels, BoxLayout.Y_AXIS));//Labels are added vertically
        makeRaceLabels(oglInfo.getRaceColsNames());//Makes all content in the race labelsPane

        panelRaceButtons = new JPanel();//Panel for race buttons
        panelRaceButtons.setLayout(new BoxLayout(panelRaceButtons, BoxLayout.Y_AXIS));//Labels are added vertically
        buttonGroupRace = new ButtonGroup();//Buttongroup for race buttons
        makeRaceButtons(oglInfo.getRaceDescs(), oglInfo.getRaceColsNames());

        panelRace.add(panelRaceButtons);//Add both buttons and labels to the racePane
        panelRace.add(Box.createRigidArea(new Dimension(35, 0)));//With a space between to widen the window
        panelRace.add(panelRaceLabels);

        //Choose Background page
        panelBackG = new JPanel();//Holds both buttons and labels panels
        panelBackG.setLayout(new BoxLayout(panelBackG, BoxLayout.X_AXIS));//Buttons and labels panels added horizontally
        panelBackG.setAlignmentX(0.0f);//Aligns the labels and buttons to the top, to keep them from moving

        panelBackGLabels = new JPanel();//Panel for backGround labels
        panelBackGLabels.setLayout(new BoxLayout(panelBackGLabels, BoxLayout.Y_AXIS));//Labels are added vertically
        makeBackGLabels(oglInfo.getBackGColsNames());//Make all content in the backGround labelsPane

        panelBackGButtons = new JPanel();//Panel for background buttons
        panelBackGButtons.setLayout(new BoxLayout(panelBackGButtons, BoxLayout.Y_AXIS));//Labels are added vertically
        buttonGroupBackG = new ButtonGroup();//Buttongroup for background buttons
        makeBackGButtons(oglInfo.getBackGDescs(), oglInfo.getBackGColsNames());

        panelBackG.add(panelBackGButtons);//Add both buttons and labels panels
        panelBackG.add(Box.createRigidArea(new Dimension(35, 0)));//With a space between to widen the window
        panelBackG.add(panelBackGLabels);
    }

    private void makeRaceButtons(ArrayList<PCRaceDesc> races, ArrayList<String> colNames) {
        for (PCRaceDesc PCRace : races) {//Creates a button for every race listed in the DB
            JRadioButton button = new JRadioButton();

            button.setText(PCRace.getName());
            panelRaceButtons.add(button);
            buttonGroupRace.add(button);

            //When getAction is called, it will return the text on the button
            button.setActionCommand(PCRace.getName());

            //Add click function for the button that fills in all labels with info from DB
            button.addActionListener(e -> raceButtonClicked(PCRace, colNames));

            //Add a space after each button, so they are evenly spaced and do not move
            panelRaceButtons.add(Box.createRigidArea(new Dimension(0,50)));
        }
    }

    private void makeClassButtons(ArrayList<PCClassDesc> classes, ArrayList<String> colNames) {
        for (PCClassDesc PCClass : classes) {//Creates a button for every class listed in the DB
            JRadioButton button = new JRadioButton();//Create a new button

            button.setText(PCClass.getName());//Set button text
            panelClassButtons.add(button);//Add the button to the panel
            buttonGroupClass.add(button);//Add the button to the group

            //When getAction is called, it will return the text on the button
            button.setActionCommand(PCClass.getName());

            //Add click function for the button that fills in all labels with info from DB
            button.addActionListener(e -> classButtonClicked(PCClass, colNames));

            //Add a space after each button, so they are evenly spaced and do not move
            panelClassButtons.add(Box.createRigidArea(new Dimension(0,50)));

        }
    }

    private void makeBackGButtons(ArrayList<PCBackGDesc> backgrounds, ArrayList<String> colNames) {
        for (PCBackGDesc PCBackG : backgrounds) {//Creates a button for every background listed in the DB
            JRadioButton button = new JRadioButton();//Create a new button

            button.setText(PCBackG.getName());//Set button Text
            panelBackGButtons.add(button);//Add the button to the panel
            buttonGroupBackG.add(button);//Add the button to the group

            //When getAction is called, it will return the text on the button
            button.setActionCommand(PCBackG.getName());

            //Add client function for the button, that fills in all labels with info from DB
            button.addActionListener(e -> backGButtonClicked(PCBackG, colNames));

            //Add a space after each button, so they are evenly spaced and do not move
            panelClassButtons.add(Box.createRigidArea(new Dimension(0,50)));
        }
    }



    private void raceButtonClicked(PCRaceDesc raceDesc, ArrayList<String> colNames) {//When creating action
        int width = 250;//For wrapping the description tests
        String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
        int colCount = 0;//Keeps track of what column we are on

        for (JLabel label: raceLabels) {//for each label that exist on this page
            label.setText((String.format(format, width,
                    colNames.get(colCount) + ": " + raceDesc.getFrom(colNames.get(colCount)))));//Set the text format, and fill with information
            colCount++;
        }
    }

    private void classButtonClicked(PCClassDesc classDesc, ArrayList<String> colNames) {
        int width = 250;//For wrapping the description tests
        String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
        int colCount = 0;//Keeps track of what column we are on

        for (JLabel label: classLabels) {//for each label that exist on this page
            label.setText((String.format(format, width,
                    colNames.get(colCount) + ": " + classDesc.getFrom(colNames.get(colCount)))));//Set the text format, and fill with information
            colCount++;
        }
    }

    private void backGButtonClicked(PCBackGDesc backGDesc, ArrayList<String> colNames) {
        int width = 250;//For wrapping the description tests
        String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
        int colCount = 0;//Keeps track of what column we are on

        for (JLabel label: backGLabels) {//for each label that exist on this page
            label.setText((String.format(format, width,
                    colNames.get(colCount) + ": " + backGDesc.getFrom(colNames.get(colCount)))));//Set the text format, and fill with information
            colCount++;
        }
    }

    private void submitButtonClicked() {
        String clas = getSelectedRadio(buttonGroupClass);
        String race = getSelectedRadio(buttonGroupRace);
        String background = getSelectedRadio(buttonGroupBackG);

        if (clas != null && race != null && background != null) { //TODO: catch error better with a warning in the UI
            dataAccess.putNewCharacter(clas, race, background);
        }
        else {
            System.out.println("Not all 3 items have a selection.");
        }
    }

    private void makeClassLabels(ArrayList<String> columns) {
            //Defines all labels for the class page, and puts them in an array for use later
            for (String column: columns) {//For each category that needs a label
            JLabel tempLabel = new JLabel("" + column);//Make a new label
            classLabels.add(tempLabel);//Add it to the labelArray
            panelClassLabels.add(tempLabel);//Add it to the panel
            panelClassLabels.add(Box.createRigidArea(new Dimension(0,35)));//Add a space to keep them from touching
        }
    }

    private void makeRaceLabels(ArrayList<String> columns) {
        //Defines all labels for the race page, and puts them in an array for use later
        for (String column : columns) {//For each category that needs a label
            JLabel tempLabel = new JLabel("" + column);//Make a new label
            raceLabels.add(tempLabel);//Add it to the label Array
            panelRaceLabels.add(tempLabel);//Add it to the panel
            panelRaceLabels.add(Box.createRigidArea(new Dimension(0,35)));//Add a space to keep them from touching
        }
    }

    private void makeBackGLabels(ArrayList<String> columns) {
        //Defines all labels for the backgrounds page, and puts them in an array for use later
        for (String column : columns) {//For each category that needs a label
            JLabel tempLabel = new JLabel("" + column);//Make a new label
            backGLabels.add(tempLabel);//Add it to the label Array
            panelBackGLabels.add(tempLabel);//Add it to the panel
            panelBackGLabels.add(Box.createRigidArea(new Dimension(0,35)));//Add a space to keep them from touching
        }
    }

    private String getSelectedRadio (ButtonGroup group) {
        for(Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}
