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

    //Race page
    private JTabbedPane tabRace;
    private JPanel panelRaceButtons;
    private ButtonGroup buttonGroupRace;
    private JLabel labelRaceName;
    private JLabel labelAge;
    private JLabel labelSize;
    private JLabel labelSpeed;
    private JLabel labelLanguages;
    private JLabel labelSubraces;
    private JLabel labelAlignment;
    private JLabel labelRaceFeatures;

    //Class page
    private JPanel panelClassButtons;
    private ButtonGroup buttonGroupClass;
    private JLabel labelClassName;
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

        //CreateUIComponents builds all the buttons with their onClick actions and data from the DB for all the labels


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
        selectedClass.clear();
        buttonGroupClass.clearSelection();
        selectedRace.clear();
    }

    public void raceButtonClicked(AbstractButton button, PCRaceDesc raceDesc) {//When creating action
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

    public void classButtonClicked(AbstractButton button, PCClassDesc classDesc) {//When creating action
        System.out.println(classDesc.getName() + " clicked!");
        int width = 250;//For wrapping the description tests
        String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text

                    //Assign each associated label with information from the class chosen
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
        oglDescription oglInfo = new oglDescription();//Gets database info for races and classes TODO: add backgrounds

        //Choose class page
        panelClassButtons = new JPanel();//Panel for class buttons
        panelClassButtons.setLayout(new GridLayout(0,1));//Rows not specified, one column
        buttonGroupClass = new ButtonGroup();//Buttongroup for class buttons
        makeClassButtons(oglInfo.getClassDescs());

        //Choose Race page
        panelRaceButtons = new JPanel();//Panel for race buttons
        panelRaceButtons.setLayout(new GridLayout(0,1));//Rows not specified, one column
        buttonGroupRace = new ButtonGroup();//Buttongroup for race buttons
        makeRaceButtons(oglInfo.getRaceDescs());
    }

    private void makeRaceButtons(ArrayList<PCRaceDesc> races) {
        for (PCRaceDesc PCRace : races) {//Creates a button for every race listed in the DB
            JRadioButton button = new JRadioButton();

            button.setText(PCRace.getName());
            panelRaceButtons.add(button);
            buttonGroupRace.add(button);

            int width = 250;
            String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
            button.addActionListener(new ActionListener() {//Add click function for the button that fills in all labels with info from DB
                @Override
                public void actionPerformed(ActionEvent e) {
                    raceButtonClicked(button, PCRace);
                }
            });
        }
    }

    private void makeClassButtons(ArrayList<PCClassDesc> classes) {
        for (PCClassDesc PCClass : classes) {//Creates a button for every class listed in the DB
            JRadioButton button = new JRadioButton();//Create a new button

            button.setText(PCClass.getName());//Set button text
            panelClassButtons.add(button);//Add the button to the panel
            buttonGroupClass.add(button);//Add the button to the group

            int width = 250;
            String format = "<html><div style=\"width:%dpx;\">%s</div></html>";//HTML that sets the style of the label text
            button.addActionListener(new ActionListener() {//Add click function for the button that fills in all labels with info from DB
                @Override
                public void actionPerformed(ActionEvent e) {
                    classButtonClicked(button, PCClass);
                }
            });

        }
    }

}
