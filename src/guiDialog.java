import Data.PCClass.*;
import Data.*;
import Data.PCRace.PCRaceDesc;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
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
    private JLabel labelFeatures;
    private JTabbedPane tabRace;

    private ButtonGroup buttonGroupClass;
    private JRadioButton radioButton10;
    private JRadioButton radioButton11;
    private JRadioButton radioButton12;
    private JRadioButton radioButton13;
    private JRadioButton radioButton14;
    private JRadioButton radioButton15;
    private JRadioButton radioButton16;
    private JRadioButton radioButton17;
    private JRadioButton radioButton18;
    private JRadioButton radioButton19;
    private JRadioButton radioButton20;
    private JRadioButton radioButton21;
    private JLabel labelClassName;

    private PCRaceDesc selectedRace;
    private PCClass selectedClass;

    public guiDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        oglDescription oglInfo = new oglDescription();//Gets database info for races {Will add classes and backgrounds}
        fillRaceDesc(oglInfo.getRaceDescs());//handles the buttons on the race page, and the label info

        getClassInfo();//Reads from flat file and then does class buttons and lables {To be integrated with oglInfo, with DB}

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
        selectedClass = new PCClass("none");
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

    private void fillClassInfo(ArrayList<PCClass> classes) {
        ArrayList<AbstractButton> buttons = doWork.getButtonArray(buttonGroupClass.getElements());//get all buttons, convert from enum to array
        for(int i = 0; i < buttons.size(); i++) {//For each button in array
            buttons.get(i).setText((classes.get(i).getName()));//Set text = name

            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() {//Add listener to button
                @Override
                public void actionPerformed(ActionEvent e) {//On button click, fill all labels with that races info
                    selectedClass = classes.get(finalI);//And set that race to the 'selected race' variable

                }
            });
        }
    }

    private void getClassInfo() {
        try {
            //Open file reader for classes
            File dataSource = new File("src\\Data\\Classes.txt");
            Scanner reader = new Scanner(dataSource);

            //Make a list of all the Classes
            ArrayList<PCClass> classes = new ArrayList<PCClass>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                PCClass temp = new PCClass(line);
                classes.add(temp);
            }
            reader.close();

            fillClassInfo(classes);

        } catch (FileNotFoundException e) {//Catches if Classes.txt cannot be found
            System.out.println("Classes.txt was not found");
            e.printStackTrace();
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
        labelFeatures.setText(String.format(format, width,
                "Features: " + raceDesc.getFeatures()));
        labelLanguages.setText(String.format(format, width,
                "Language: " + raceDesc.getLanguageD()));
        labelSubraces.setText(String.format(format, width,
                "Subraces: " + raceDesc.getSubraces()));

        selectedRace = raceDesc;//And set that race to the 'selected race' variable

    }

        public static void main(String[] args) {
        guiDialog dialog = new guiDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
