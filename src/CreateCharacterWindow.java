import Data.*;
import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class CreateCharacterWindow extends JFrame {
    //Container panes
    JPanel pagePane = new JPanel();//This pane holds whatever page the user is currently on
    JPanel buttonPane = new JPanel();//This pane is not dependent on what page the user is on

    //Different pages
    RCBPage rcb = new RCBPage();//JTabbedPane where you select race, class, and background
    StatPage sp = new StatPage();//JPanel where you select 6 stats and proficiencies

    public CreateCharacterWindow() {
        JPanel contentPane = new JPanel();//One pane to rule them all
        contentPane.setLayout(new BorderLayout());
        this.add(contentPane);

        contentPane.add(pagePane, BorderLayout.CENTER);//Add page Pane
        contentPane.add(buttonPane, BorderLayout.PAGE_END);//Add button pane

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

        showPage1();//Start at page one
    }

    public void showPage1() {
        pagePane.removeAll();
        pagePane.add(rcb);
        pagePane.revalidate();
        pagePane.repaint();//Show new page
        pack();//resize window to fit

        buttonPane.getComponent(0).setEnabled(false);
        buttonPane.getComponent(1).setEnabled(true);
    }

    public void showPage2() {
        pagePane.removeAll();
        pagePane.add(sp);
        pagePane.revalidate();
        pagePane.repaint();//Show new page
        pack();//resize window to fit

        buttonPane.getComponent(1).setEnabled(false);
        buttonPane.getComponent(0).setEnabled(true);
    }

    private void submitButtonClicked() {//Writes to the DB when creation is done
        PlayerCharacter pc = new PlayerCharacter();
        pc.setClas(getSelectedRadio(rcb.getButtonGroupClass()));
        pc.setRace(getSelectedRadio(rcb.getButtonGroupRace()));
        pc.setBackground(getSelectedRadio(rcb.getButtonGroupBackG()));

        if (pc.getClas() != null && pc.getRace() != null && pc.getBackground() != null) { //TODO: catch error better with a warning in the UI
            dataAccess.putNewCharacter(pc);
        }
        else {
            System.out.println("Not all 3 items have a selection.");
        }
    }

    private String getSelectedRadio (ButtonGroup group) {//Utility function, gets currently text of currently selected radio button in given button group
        for(Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
