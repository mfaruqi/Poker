import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
/**
 * This class extends JFrame and makes a panel for this game
 */
public class Panel extends JFrame{  

    /**
     * This constructor makes a panel with certian parameters, it calls the super to get JFrame's attributes
     * @param name this is the name of the panel
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public Panel(String name, int width, int height) {
        super(name);
        //full screen
        setSize(width, height);
        //setUndecorated(true);
        setVisible(true);  
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // this.getContentPane().setBackground(Color.red);
        ImageIcon image = new ImageIcon("Images/PokerTable.png");
        Image image1 = getScaledImage(image.getImage(), width, height);
        image = new ImageIcon(image1);
        setContentPane(new JLabel(image));
        pack();
        setVisible(true);

    }

    /**
     * This image returns a scaled image
     * @param srcImg the image needed to be resized
     * @param w the width of the new card
     * @param h the height of the new card
     * @return the newly resized image
     */
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}  
