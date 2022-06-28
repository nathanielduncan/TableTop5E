import javax.swing.*;
import java.awt.*;

public class NavigationFrame extends JFrame {
    JPanel cardsPane; //A panel that uses CardLayout
    final static String CREATEPANEL = "Create A Character";
    final static String HOMEPANEL = "Home";
    final static String PLAYPANEL = "Play on TableTop";

    public NavigationFrame(String title) {//When Frame is made
        super(title);//Run the JFrame initializer, put title in place
    }

    public void addComponentToPane(Container pane) {
        //Create the body of the navigation panel, with links to the other pages
        JPanel panel = new JPanel(new FlowLayout(//Add all components in a line
                FlowLayout.CENTER, 100, 5));//center components, Horizontal buffer size, Vertical buffer size
                panel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));//Makes a black border, only on bottom(t,l,b,r)
        //Make buttons
        JButton createB = new JButton(CREATEPANEL);
        JButton homeB = new JButton(HOMEPANEL);
        JButton playB = new JButton(PLAYPANEL);
        //Add them to the Content Pane
        panel.add(createB);
        panel.add(homeB);
        panel.add(playB);
        //Give them each a listener
        createB.addActionListener(e -> naviButtonClicked(createB));
        homeB.addActionListener(e -> naviButtonClicked(homeB));
        playB.addActionListener(e -> naviButtonClicked(playB));


        //Create the "cards", items added inside individual classes
        //Card1 card1 = new Card1();
        JPanel card1 = new JPanel();
        card1.add(new JLabel("Home Page"));
        card1.add(new JLabel("Not implemented yet"));

        //CreateCharacterPage CCPage = new CreateCharacterPage();
        CharacterPage CCPage = new CharacterPage();

        JPanel card3 = new JPanel();
        card3.add(new JLabel("Play TableTop page"));
        card3.add(new JLabel("Not implemented yet"));

        //Create the panel that contains the "cards"
        cardsPane = new JPanel(new CardLayout());
        cardsPane.add(card1, HOMEPANEL);
        cardsPane.add(CCPage, CREATEPANEL);
        cardsPane.add(card3, PLAYPANEL);

        pane.add(panel, BorderLayout.PAGE_START);
        pane.add(cardsPane, BorderLayout.CENTER);
    }

    public void naviButtonClicked(AbstractButton btn) {
        CardLayout cl = (CardLayout)(cardsPane.getLayout());//Defines the card layout
        cl.show(cardsPane, btn.getText());//Gets the button text, goes to the matching page
    }
}
