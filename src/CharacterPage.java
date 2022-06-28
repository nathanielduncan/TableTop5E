import javax.swing.*;
import java.awt.*;

public class CharacterPage extends JPanel {
    public CharacterPage() {
        this.setLayout(new GridLayout(0,1));//Layout manager for the frame will have components stacked vertically

        JButton makeButton = new JButton("Make a new character");//This button opens the create character window
        makeButton.addActionListener(e -> makeButtonClicked());
        this.add(makeButton);

        this.add(new JButton("View my Characters"));//TODO: opens a window that displays current stored characters
    }

    private void makeButtonClicked() {
        CreateCharacterPage CCPage = new CreateCharacterPage();//Extends JFrame, makes a new window
        CCPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Doesn't end the program on close, just the windows gets disposed

        CCPage.pack();//Sets the window size to fit all components
        CCPage.setVisible(true);//Makes it visible
    }
}
