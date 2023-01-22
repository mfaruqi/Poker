import javax.swing.*;
import java.awt.*;
/**
 * This class makes multiple types of labels using the JLabel class
 */
public class Label extends JLabel{

    /**
     * This constructor makes the label with certain parameters
     * @param image the image on the label
     * @param x the x location
     * @param y the y location
     * @param width the width
     * @param height the height
     */
    public Label(ImageIcon image, int x, int y,  int width, int height) {
        super(image);
        setVisible(true);
        this.setSize(width, height);
        this.setLocation(x, y);
    }

    /**
     * This constructor makes the label with certain parameters
     * @param image the image on the label
     * @param width the width
     * @param height the height
     */
    public Label(ImageIcon image, int width, int height) {
        super(image);
        setVisible(true);
        this.setSize(width, height);
    }

    /**
     * This constructor makes the label with certain parameters
     * @param image the image on the label
     * @param str the string for the label
     * @param sizeX the width
     * @param sizeY the height
     * @param x the x location
     * @param y the y location
     */
    public Label(ImageIcon image, String str, int sizeX, int sizeY, int x, int y) {
        super(image);
        setVisible(true);
        this.setSize(sizeX, sizeY);
        this.setLocation(x,y);
    }

    /**
     * This constructor makes the label with certain parameters
     * @param str the string for the label
     * @param sizeX the width
     * @param sizeY the height
     * @param x the x location
     * @param y the y location
     */
    public Label(String str, int sizeX, int sizeY, int x, int y) {
        super(str, SwingConstants.CENTER);
        setVisible(true);
        this.setBounds(x, y, sizeX, sizeY);
    }
}
