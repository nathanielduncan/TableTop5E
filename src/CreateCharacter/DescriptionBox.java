package CreateCharacter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DescriptionBox extends JPanel {
    JLabel description = new JLabel("");

    public DescriptionBox(String Title) {
        this.setBorder(BorderFactory.createCompoundBorder(//Combines two borders and assigns them to this Panel
                BorderFactory.createEmptyBorder(10, 10, 10, 10),    //Empty border, for padding
                new ArcCornerBorder()));                            //Custom black line border
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//Items added vertically

        //defines this about the title label
        JLabel title = new JLabel(Title);//Text is set by passed in variable
        title.setBorder(new ArcCornerBorder());//Custom border
        title.setAlignmentX(Component.CENTER_ALIGNMENT);//Center align the label
        this.add(title);//Add to the pane

        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(description);
    }

    public void putDescription(String desc) {
        description.setText(desc);
    }
}
