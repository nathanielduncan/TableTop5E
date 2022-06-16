import javax.swing.*;

public class TableTop5E {

    private static void createAndShowGUI() {
        //Create and set up the window
        NavigationFrame frame = new NavigationFrame("CardLayoutDemo");//Creates the frame(Window) and sets title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane
        frame.addComponentToPane(frame.getContentPane());//Makes all content inside the frame

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Makes this thread safe I guess?
        javax.swing.SwingUtilities.invokeLater(TableTop5E::createAndShowGUI);
    }
}
