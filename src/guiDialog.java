import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class guiDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton radioButton8;
    private JRadioButton radioButton9;
    private ButtonGroup buttonGroupRace;

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

    private void fillInfo(ArrayList<String> races) {
        Enumeration<AbstractButton> buttons = buttonGroupRace.getElements();//Makes Enum type from race buttons group
        int i = 0;
        while(buttons.hasMoreElements()) {//For each button in enumerator
            buttons.nextElement().setText(races.get(i));//Set text value to current race in ArrayList
            //System.out.println(races.get(i));
            i++;//Next race in ArrayList
        }
    }

    private void getInfo() {
        try {
            //Open file reader for Races
            File dataSource = new File("src\\Data\\Races.txt");
            Scanner reader = new Scanner(dataSource);

            //Make a list of all Races
            ArrayList<String> races = new ArrayList<String>();
            while (reader.hasNextLine()) {//Fill in the Race list from Race file
                races.add(reader.nextLine());
            }
            reader.close();

            fillInfo(races);//Assigns races to buttons

        } catch (FileNotFoundException e) {//Catches if Races.txt cannot be found
            System.out.println("An error occurred.");
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
