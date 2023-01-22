import java.util.ArrayList;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import java.util.List;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 * This is a poker game, designed for a 1v1 against a bot
 */
public class Poker {

    /**
     * This is the width of the card
     */
    public static final int sizeX = 125;
    /**
     * This is the height of the card
     */
    public static final int sizeY = 193;
    /**
     * This is the height of the screen size
     */
    public static int height;
    /**
     * This is the width of the screen size
     */
    public static int width;

    /**
     * This main method initializes the main things, like the deck, player, bot and the whole game itself
     * @param args the arguments to this program
     */
    public static void main(String[] args) {
        //get screen width and height and set it as class variable
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = (int)screenSize.getHeight();
        width = (int)screenSize.getWidth();

        CardDeck deck = new CardDeck(1);
        //initialize poker player and the poker bot 
        PokerPlayer player = new PokerPlayer(500, false); 
        CPUPlayer cpuPlayer = new CPUPlayer(500, true);
        Panel panel = new Panel("Poker", width, height);
        JLabel label = printHandString1(panel, player.getCards());
        Controller controller = new Controller(panel, deck, cpuPlayer,  player, height, width, label);
        panel.setVisible(true);
    }
    
    /**
     * This method makes a label for the type of hand you have for better accessibility
     * @param panel the frame to be put on
     * @param hand the hand to print out
     * @return returns the label
     */
    private static JLabel printHandString1(Panel panel, ArrayList<Card> hand) {
        JLabel label = new JLabel();
        int cardX = (width-(sizeX*2))/2;
        int cardY = (height - sizeY) - 50;
        label.setBounds(cardX + 300, cardY - 30, 500, 50);
        Font font = new Font("Verdana", Font.PLAIN, 30);
        label.setFont(font);
        label.setVisible(true);
        panel.add(label);
        panel.repaint();
        return label;
    }
}
