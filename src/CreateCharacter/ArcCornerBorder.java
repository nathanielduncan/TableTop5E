package CreateCharacter;

import javax.swing.border.Border;
import java.awt.*;
/*
class found and modified from
//http://www.java2s.com/Code/Java/Swing-JFC/Ovalborder.htm
*/

public class ArcCornerBorder implements Border {
    protected int ovalWidth;

    protected int ovalHeight;

    protected Color color;

    public ArcCornerBorder() {
        ovalWidth = 6;
        ovalHeight = 6;
        color = Color.BLACK;
    }

    public ArcCornerBorder(Color c) {
        ovalWidth = 6;
        ovalHeight = 6;
        color = c;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(ovalHeight, ovalWidth, ovalHeight, ovalWidth);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        width--;
        height--;

        g.setColor(color);
        g.drawLine(x, y + height - ovalHeight, x, y + ovalHeight);//Left Wall
        g.drawArc(x, y, 2 * ovalWidth, 2 * ovalHeight, 180, -90);//Top Left Corner
        g.drawLine(x + ovalWidth, y, x + width - ovalWidth, y);//Top Wall
        g.drawArc(x + width - 2 * ovalWidth, y,
                2 * ovalWidth, 2 * ovalHeight, 90, -90);//Top Right Corner
        g.drawLine(x + width, y + ovalHeight, x + width, y + height - ovalHeight);//Right Wall
        g.drawArc(x + width - 2 * ovalWidth, y + height - 2 * ovalHeight,
                2 * ovalWidth, 2 * ovalHeight, 0, -90);//Bottom Right Corner
        g.drawLine(x + ovalWidth, y + height, x + width - ovalWidth, y + height);//Bottom Wall
        g.drawArc(x, y + height - 2 * ovalHeight, 2 * ovalWidth,
                2 * ovalHeight, -90, -90);//Bottom Left Corner
    }
}