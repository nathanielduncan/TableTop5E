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
    private JTextPane textPaneRace;

    public guiDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        getInfo();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void fillInfo(ArrayList<PCRace> races) {
        ArrayList<AbstractButton> buttons = doWork.getButtonArray(buttonGroupRace.getElements());//get all buttons, convert from enum to array
        for(int i = 0; i < buttons.size(); i++) {//For each button in array
            buttons.get(i).setText((races.get(i).name));//Set text = name

            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() {//Add listener to button
                @Override
                public void actionPerformed(ActionEvent e) {
                    textPaneRace.setText(races.get(finalI).printRace());
                }
            });

        }
    }

    private void getInfo() {
        try {
            //Open file reader for Races
            File dataSource = new File("src\\Data\\Races.txt");
            Scanner reader = new Scanner(dataSource);

            //Make a list of all Races
            ArrayList<PCRace> races = new ArrayList<PCRace>();
            while (reader.hasNextLine()) {//Fill in the Race list from Race file
                String line = reader.nextLine();
                PCRace temp = new PCRace(line);
                races.add(temp);
            }
            reader.close();

            fillInfo(races);//Assigns races to buttons

        } catch (FileNotFoundException e) {//Catches if Races.txt cannot be found
            System.out.println("Races.txt was not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        guiDialog dialog = new guiDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
