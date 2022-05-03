import Data.PCClass.*;
import Data.*;
import Data.PCRace.PCRaceDesc;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class guiDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private ButtonGroup buttonGroupRace;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton radioButton8;
    private JRadioButton radioButton9;
    private JLabel labelRaceName;
    private JLabel labelAge;
    private JLabel labelSize;
    private JLabel labelSpeed;
    private JLabel labelLanguages;
    private JLabel labelSubraces;
    private JLabel labelAlignment;
    private JLabel labelRaceFeatures;
    private JTabbedPane tabRace;

    private ButtonGroup buttonGroupClass;
    private JLabel labelClassName;
    private JPanel panelClassButtons;
    private JLabel labelHitDice;
    private JLabel labelArmor;
    private JLabel labelWeapons;
    private JLabel labelTools;
    private JLabel labelSavingThrows;
    private JLabel labelSkills;
    private JLabel labelEquipment;
    private JLabel labelClassFeatures;
    private JLabel labelASI;
    private JLabel labelSubclasses;

    private PCRaceDesc selectedRace;
    private PCClassDesc selectedClass;

    public guiDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        oglDescription oglInfo = new oglDescription();//Gets database info for races and classes {Will add backgrounds}
        fillRaceDesc(oglInfo.getRaceDescs());//handles the buttons on the race page, and the label info

    //Class buttons happen where GUI is made
        //fillClassDesc(oglInfo.getClassDescs());
        //makeClassButtons(oglInfo.getClassDescs());

        //getClassInfo();//Reads from flat file and then does class buttons and lables {To be integrated with oglInfo, with DB}

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClear();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSubmit() {
        System.out.println(selectedRace.getName());
        System.out.println(selectedClass.getName());
    }

    private void onCancel() {
        dispose();
    }

    private void onClear() {
        buttonGroupRace.clearSelection();
        selectedClass = new PCClassDesc();
        buttonGroupClass.clearSelection();
        selectedRace = new PCRaceDesc();
    }

    private void fillRaceDesc(ArrayList<PCRaceDesc> races) {
        ArrayList<AbstractButton> buttons = doWork.getButtonArray(buttonGroupRace.getElements());//get all buttons, convert from enum to array
        Integer raceCount = 0;
        for (AbstractButton button : buttons) {
            button.setText(races.get(raceCount).getName());//set text = name

            int width = 250;
            String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
            Integer finalRaceCount = raceCount;
            button.addActionListener(new ActionListener() {//Add listener to button
                @Override
                public void actionPerformed(ActionEvent e) {//On button click, fill all lables with that races info
                    raceButtonClicked(button, races.get(finalRaceCount));
                }
            });
            raceCount++;
        }
    }

    public void raceButtonClicked(AbstractButton button, PCRaceDesc raceDesc) {
        int width = 250;//For wrapping the description tests
        String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text

                //Set each label on the page with the correct information for that race
        labelRaceName.setText(String.format(format, width,
                raceDesc.getName()));
        labelAlignment.setText(String.format(format, width,
                "Alignment: " + raceDesc.getAlignmentD()));
        labelAge.setText(String.format(format, width,
                "Age: " + raceDesc.getAgeD()));
        labelSize.setText(String.format(format, width,
                "Size: " + raceDesc.getSizeD()));
        labelSpeed.setText(String.format(format, width,
                "Speed: " + raceDesc.getSpeedD()));
        labelRaceFeatures.setText(String.format(format, width,
                "Features: " + raceDesc.getFeatures()));
        labelLanguages.setText(String.format(format, width,
                "Language: " + raceDesc.getLanguageD()));
        labelSubraces.setText(String.format(format, width,
                "Subraces: " + raceDesc.getSubraces()));

        selectedRace = raceDesc;//And set that race to the 'selected race' variable
    }

    public void classButtonClicked(AbstractButton button, PCClassDesc classDesc) {
        System.out.println(classDesc.getName() + " clicked!");
        int width = 250;//For wrapping the description tests
        String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text

        labelClassName.setText((String.format(format, width,
                classDesc.getName())));
        labelHitDice.setText((String.format(format, width,
                "Hit Dice: " + classDesc.getHitDice())));
        labelArmor.setText((String.format(format, width,
                "Armor: " + classDesc.getArmor())));
        labelWeapons.setText((String.format(format, width,
                "Weapons: " + classDesc.getWeapons())));
        labelTools.setText((String.format(format, width,
                "Tools: " + classDesc.getTools())));
        labelSavingThrows.setText((String.format(format, width,
                "Saving Throws: " + classDesc.getSavingThrows())));
        labelSkills.setText((String.format(format, width,
                "Skills: " + classDesc.getSkills())));
        labelEquipment.setText((String.format(format, width,
                "Equipment: " + classDesc.getEquipment())));
        labelClassFeatures.setText((String.format(format, width,
                "Features: " + classDesc.getFeatures())));
        labelASI.setText((String.format(format, width,
                "Ability Score Improvement: " + classDesc.getAsi())));
        labelSubclasses.setText((String.format(format, width,
                "Subclasses: " + classDesc.getSubclasses())));

        selectedClass = classDesc;
    }

        public static void main(String[] args) {
        guiDialog dialog = new guiDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        oglDescription oglInfo = new oglDescription();//Gets database info for races and classes {Will add backgrounds}
            //^^^Probably need a function to just do the names for the buttons, don't need all ogl info here^^^

        panelClassButtons = new JPanel();//Panel for class buttons
        panelClassButtons.setLayout(new GridLayout(0,1));//Rows not specified, one column
        buttonGroupClass = new ButtonGroup();//Buttongroup for class buttons

        makeClassButtons(oglInfo.getClassDescs());


    }

    private void makeClassButtons(ArrayList<PCClassDesc> classes) {

        for (PCClassDesc PCClass : classes) { //per class from DB
            JRadioButton button = new JRadioButton();//Create a new button

            button.setText(PCClass.getName());//Set button text
            panelClassButtons.add(button);//Add the button to the panel
            buttonGroupClass.add(button);//Add the button to the group

            int width = 250;
            String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
            button.addActionListener(new ActionListener() {//Add click function for the button
                @Override
                public void actionPerformed(ActionEvent e) {
                    classButtonClicked(button, PCClass);
                }
            });

        }
    }

}
