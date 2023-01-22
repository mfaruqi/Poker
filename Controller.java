import java.net.*;
import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * This class implemets action listener for all the buttons. 
 * All the buttons are initialized here and added this action listener to them.
 * This class also runs most of the on screen program.
 */
public class Controller implements ActionListener {

    /**
     * This is the small blind of the game
     */
    private final int smallBlind = 25;

    /**
     * This is the big blind for the game
     */
    private final int bigBlind = smallBlind*2;

    /**
     * This is the panel everything is on
     */
    private Panel panel;

    /**
     * This is the card deck
     */
    private CardDeck deck;

    /**
     * This is the user's player profile
     */
    private PokerPlayer player;

    /**
     * This is the bot player's profile
     */
    private CPUPlayer cpuPlayer;

    /**
     * This variable keeps track of the round we are in
     */
    private static int counter = 0;

    /**
     * This variable keeps track of the round number we are in
     */
    private static int roundNumber = 0;
    /**
     * This is the width of each card
     */
    public static final int sizeX = 125;

    /**
     * This is the height of each card
     */
    public static final int sizeY = 193;

    /**
     * This is the x location of card
     */
    private int cardX;

    /**
     * This is the y location of card
     */
    private int cardY;

    /**
     * This is how much to increase the bet by when user clicks to increase
     */
    public static final int BET_INCREASE = 25;

    /**
     * This is the height of the screen
     */
    private int height;

    /**
     * This is the width of the screen
     */
    private int width;

    /**
     * This is the string representation of what someone has
     */
    private JLabel handString;

    /**
     * This is a button to check
     */
    private JButton checkButton;

    /**
     * This is a button to bet
     */
    private JButton betButton;

    /** 
     * This button adds to the bet
     */
    private JButton addBet;

    /**
     * This buttons subtracts to the bet
     */
    private JButton subtractBet;

    /**
     * This button confirms the bet
     */
    private JButton confirmBet;

    /**
     * This button is to fold the bet
     */
    private JButton foldButton;

    /**
     * This is the display for the bet
     */
    private Label betDisplay;

    /**
     * This is the display for the pot
     */
    private Label potDisplay;

    /**
     * This is the amount bet by the player
     */
    private int betAmountPlayer = 0;

    /**
     * This is the amount bet by the CPU
     */
    private int betAmountCPU = 0;

    /**
     * This is the total amount bet by both players, or the pot
     */
    private int totalBetAmount = 0;

    /**
     * This is to display the chip number
     */
    private JLabel displayChips;

    /**
     * This displays the chips number of the cpu
     */
    private JLabel displayChipsCPU;

    /**
     * This is your first hole card the user gets
     */
    private JLabel holeCard11;

    /** 
     * This is the second hold card the user gets
     */
    private JLabel holeCard12;

    /**
     * This is the first hole card that the CPU gets
     */
    private JLabel holeCard21;

    /**
     * This is the second hole card that the CPU gets
     */
    private JLabel holeCard22;

    /**
     * This is the first community card
     */
    private JLabel card1;

    /**
     * This is the second community card
     */
    private JLabel card2;

    /**
     * This is the third community card
     */
    private JLabel card3;

    /**
     * This is the fourth community card
     */
    private JLabel card4;

    /**
     * This is the fifth community card
     */
    private JLabel card5;

    /**
     * This is to display when the CPU folds
     */
    private JLabel foldText;

    /**
     * This is a boolean to alteranate between small blind and big blind
     */
    private boolean playerSmallB = false;

    /**
     * This constructor intitalizes all the variables that are neeeded to be.
     * It also adds the action listener to the buttons.
     * @param panel the panel to play on
     * @param deck this is the card deck
     * @param cpuPlayer this is the bot player
     * @param player this is the actual player
     * @param height this is the height of the screen
     * @param width this is the width of the screen
     * @param handString this is the string representation of your hand
     */
    public Controller(Panel panel, CardDeck deck, CPUPlayer cpuPlayer, PokerPlayer player, int height, int width, JLabel handString) {
        Font font = new Font("Verdana", Font.PLAIN, 30);
        this.panel = panel;
        this.deck = deck;
        this.player = player;
        this.cpuPlayer = cpuPlayer;
        this.height = height;
        this.width = width;
        cardX = (width-(sizeX*2))/2;
        cardY = (height - sizeY) - 50;
        this.handString = handString;
        checkButton = makeButton(panel, "Check", 140, 120, 100, 600);
        betButton = makeButton(panel, "Bet", 140, 120, 240, 600);
        checkButton.addActionListener(this);
        betButton.addActionListener(this);
        checkButton.setVisible(true);
        betButton.setVisible(true);
        foldButton = makeButton(panel, "Fold", 140, 120, 100, 480);
        foldButton.setVisible(true);
        foldButton.addActionListener(this);
        betDisplay = new Label("<html><div style='text-align: center;'>" + betAmountPlayer + "</div></html>", 125, 50, 275, 610);
        betDisplay.setVisible(false);
        foldText = new Label("Fold", 125, 50, 650, 275);
        foldText.setFont(font);
        foldText.setVisible(false);
        panel.add(foldText);
        ImageIcon image = new ImageIcon("Images/PokerChips.png");
        Image image1 = getScaledImage(image.getImage(), 100, 100);
        image = new ImageIcon(image1);
        potDisplay = new Label(image, "Pot: " + totalBetAmount, 400, 200, 900, 350);
        betDisplay.setFont(font);
        panel.add(betDisplay);
        potDisplay.setFont(font);
        potDisplay.setVisible(false);
        panel.add(potDisplay);
        addBet = makeButton(panel, "+", 75, 75, 400, 600);
        subtractBet = makeButton(panel, "-", 75, 75, 200, 600);
        confirmBet = makeButton(panel, "✔", 75, 75, 475, 600);
        displayChipsCPU = new Label("Chips: " + cpuPlayer.getChips(), 200, 100, 600, 315);
        displayChipsCPU.setFont(new Font("Verdana", Font.PLAIN, 20));
        displayChipsCPU.setVisible(true);
        displayChips = new Label("Chips: " + player.getChips(), 200, 100, 600, 580);
        displayChips.setFont(new Font("Verdana", Font.PLAIN, 20));
        displayChips.setVisible(true);
        panel.add(displayChips);
        panel.add(displayChipsCPU);
        addBet.addActionListener(this);
        subtractBet.addActionListener(this);
        confirmBet.addActionListener(this);
        card1 = new JLabel();
        card2 = new JLabel();
        card3 = new JLabel();
        card4 = new JLabel();
        card5 = new JLabel();
        //getSmallBlind and big blind
        /*        if(cpuPlayer.getSmallBlind()) {
                  cpuPlayer.setChips(cpuPlayer.getChips() - smallBlind);
                  player.setChips(player.getChips() - bigBlind);
                  totalBetAmount+=smallBlind+bigBlind;
        }
        else {
        cpuPlayer.setChips(cpuPlayer.getChips() - bigBlind);
        player.setChips(player.getChips() - smallBlind);
        totalBetAmount +=smallBlind+bigBlind;
        }
        */
        displayChipsCPU.setText("Chips: " + cpuPlayer.getChips());
        displayChips.setText("Chips: " + player.getChips());
        makeHoleCards(deck, panel, player, cpuPlayer, playerSmallB);
    }


    /**
     * This is the method to check when buttons are clicked, it does a lot of running the program on screen. 
     * There are many buttons and depending on which one it does different things
     * @param e the action event if a button is pressed
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checkButton || e.getSource() == confirmBet) {
            if(counter == 0) {
                flop(this.deck, this.panel, this.player);
            }
            else if(counter == 1) {
                turn(this.deck, this.panel, this.player);
            }
            else if(counter == 2) {
                river(this.deck, this.panel, this.player);
                roundNumber++;
            }
            counter++;
        }
        if(e.getSource() == betButton) {
            checkButton.setVisible(false);
            betButton.setVisible(false);
            foldButton.setVisible(false);
            addBet.setVisible(true);
            subtractBet.setVisible(true);
            confirmBet.setVisible(true);
            betDisplay.setVisible(true);
        }
        if(e.getSource() == addBet) {
            if(betAmountPlayer<=player.getChips() - BET_INCREASE && betAmountPlayer<=cpuPlayer.getChips() - BET_INCREASE) {
                betAmountPlayer+=BET_INCREASE;
                betAmountCPU+=BET_INCREASE;
                //totalBetAmount+=BET_INCREASE;
                betDisplay.setText("<html><div style='text-align: center;'>" + String.valueOf(betAmountPlayer) + "</div></html>");
            }
        }
        if(e.getSource() == subtractBet) {
            if(betAmountPlayer>0) {
                betAmountPlayer-=BET_INCREASE;
                betAmountCPU -= BET_INCREASE;
                //totalBetAmount-=BET_INCREASE;
                betDisplay.setText("<html><div style='text-align: center;'>" + String.valueOf(betAmountPlayer) + "</div></html>");
            }

        }

        if(e.getSource() == foldButton) {
            roundNumber++;
            System.out.println("Round: " + roundNumber + " CPU Wins, You Fold");
            fold(true);

        }

        if(e.getSource() == confirmBet) {
            addBet.setVisible(false);
            subtractBet.setVisible(false);
            confirmBet.setVisible(false);
            betDisplay.setVisible(false);
            player.setChips(player.getChips()-betAmountPlayer);
            //System.out.println(Hands.getHandValue(cpuPlayer.getCards()));
            if(counter < 3 && betAmountPlayer>=bigBlind && Hands.getHandValue(cpuPlayer.getCards()) > 8 && Math.random()*4 == 1
                    || betAmountPlayer >= bigBlind*2 && Hands.getHandValue(cpuPlayer.getCards()) > 8 && Math.random()*6 != 5) {
                player.setChips(player.getChips() + totalBetAmount-betAmountPlayer);
                displayChips.setText("Chips: " + player.getChips());
                displayChipsCPU.setText("Chips: " + cpuPlayer.getChips());
                totalBetAmount+=betAmountPlayer + betAmountCPU;
                //System.out.println(totalBetAmount);
                if(totalBetAmount>0) {
                    potDisplay.setVisible(true);
                    potDisplay.setText("Pot: " + totalBetAmount);
                    //System.out.println(totalBetAmount);
                }

                new Thread() {
                    public void run() {
                        try {
                            foldText.setVisible(true);
                            Thread.sleep(500);
                        }
                     catch (Exception e) {
                        e.printStackTrace();
                    }
                    }
                } .start();
                if(counter < 2) {
                    roundNumber++;
                }
                System.out.println("Round: " + roundNumber + " You win, CPU Folds");
                fold(false);
                foldText.setVisible(false);
                betAmountPlayer = 0;
                betAmountCPU = 0;

                betDisplay.setText("<html><div style='text-align: center;'>" + String.valueOf(betAmountPlayer) + "</div></html>");
                    }
            else {
                cpuPlayer.setChips(cpuPlayer.getChips() - betAmountCPU);

                displayChips.setText("Chips: " + player.getChips());
                displayChipsCPU.setText("Chips: " + cpuPlayer.getChips());
                totalBetAmount+=betAmountPlayer + betAmountCPU;
                //System.out.println(totalBetAmount);
                if(totalBetAmount>0) {
                    potDisplay.setVisible(true);
                    potDisplay.setText("Pot: " + totalBetAmount);
                    //System.out.println(totalBetAmount);
                }
            }
            betAmountPlayer = 0;
            betAmountCPU = 0;
            betDisplay.setText("<html><div style='text-align: center;'>" + String.valueOf(betAmountPlayer) + "</div></html>");
            if(counter<4) {
                checkButton.setVisible(true);
                betButton.setVisible(true);
                foldButton.setVisible(true);
                if(player.getChips() == 0) {
                    checkButton.setVisible(false);
                    betButton.setVisible(false);
                    foldButton.setVisible(false);
                    //counter++;
                    while(counter<3) {
                        if(counter == 0) {
                            flop(this.deck, this.panel, this.player);
                        }
                        else if(counter == 1) {
                            turn(this.deck, this.panel, this.player);
                        }
                        else if(counter == 2) {
                            river(this.deck, this.panel, this.player);
                        }
                        counter++;
                        if(counter == 3) counter++;
                    }
                    displayChipsCPU.setText("Chips: 1000");
                    cpuPlayer.setChips(1000);
                    player.setChips(0);
                    handString.setText("CPU WINS"); 
                    potDisplay.setVisible(false);
                }
                else if(cpuPlayer.getChips() == 0) {
                    checkButton.setVisible(false);
                    betButton.setVisible(false);
                    foldButton.setVisible(false);
                    //counter++;
                    while(counter<3) {
                        if(counter == 0) {
                            flop(this.deck, this.panel, this.player);
                        }
                        else if(counter == 1) {
                            turn(this.deck, this.panel, this.player);
                        }
                        else if(counter == 2) {
                            river(this.deck, this.panel, this.player);
                        }
                        counter++;
                        if(counter == 3) counter++;
                    }

                    displayChips.setText("Chips: 1000");
                    player.setChips(1000);
                    cpuPlayer.setChips(0);
                    potDisplay.setVisible(false);
                    handString.setText("YOU WIN"); 
                }
            }
        }
        if(counter==4) {
            if(Hands.getWinner(player.getCards(), cpuPlayer.getCards()) == 1){
                player.setChips(totalBetAmount + player.getChips()); 
                System.out.println("Round: " + roundNumber + ": You win with " + Hands.getHandString(player.getCards()) + "   CPU hand, " + Hands.getHandString(cpuPlayer.getCards()) + "  "  + cpuPlayer.getCards());
                //System.out.println("PLAYER ONE WINS");
                //reset all the cards here
            }
            else if(Hands.getWinner(player.getCards(), cpuPlayer.getCards()) == 0){
                cpuPlayer.setChips(totalBetAmount + cpuPlayer.getChips());
                System.out.println("Round: " + roundNumber + ": CPU wins with " + Hands.getHandString(cpuPlayer.getCards()) + "  " + cpuPlayer.getCards());

            } else {
                cpuPlayer.setChips(cpuPlayer.getChips() + (totalBetAmount/2));
                player.setChips(player.getChips() + (totalBetAmount/2));
                System.out.println("Round: " + roundNumber + ": Tie Your hand, " + Hands.getHandString(player.getCards()) + " CPU hand, " + Hands.getHandString(cpuPlayer.getCards()) + "  " + cpuPlayer.getCards());
            }
            //System.out.println(Hands.getWinner(player.getCards(), cpuPlayer.getCards()));
            displayChipsCPU.setText("Chips: " + cpuPlayer.getChips());
            displayChips.setText("Chips: " + player.getChips());
            if(player.getChips() > 0 && cpuPlayer.getChips() > 0) {
                card1.setVisible(false);
                card2.setVisible(false);
                card3.setVisible(false);
                card4.setVisible(false);
                card5.setVisible(false);
                holeCard11.setVisible(false);
                holeCard12.setVisible(false);
                holeCard21.setVisible(false);
                holeCard22.setVisible(false);
                player.getCards().clear();
                cpuPlayer.getCards().clear();
                deck.shuffle();
                makeHoleCards(deck, panel, player, cpuPlayer, !playerSmallB);
                checkButton.setVisible(true);
                betButton.setVisible(true);
                counter = 0;
                handString.setText("");
                totalBetAmount = 0;
                potDisplay.setVisible(false);
            }
        } 
        if(player.getCards().size()>1) 
            if(player.getChips()>0 && cpuPlayer.getChips()>0) 
            handString.setText(Hands.getHandString(player.getCards()));
            
        //System.out.println(counter);

    }

    /**
     * This is the method if you or the bot wants to fold
     * @param player1 if player wants to fold, use true, if bot then false
     */
    private void fold(boolean player1) {
        card1.setVisible(false);
        card2.setVisible(false);
        card3.setVisible(false);
        card4.setVisible(false);
        card5.setVisible(false);
        holeCard11.setVisible(false);
        holeCard12.setVisible(false);
        holeCard21.setVisible(false);
        holeCard22.setVisible(false);
        player.getCards().clear();
        cpuPlayer.getCards().clear();
        deck.shuffle();
        makeHoleCards(deck, panel, player, cpuPlayer, !playerSmallB);
        checkButton.setVisible(true);
        betButton.setVisible(true);
        counter = 0;
        handString.setText("");
        if(player1)
            cpuPlayer.setChips(cpuPlayer.getChips() + totalBetAmount);
        else player.setChips(player.getChips() + totalBetAmount);
        totalBetAmount = 0;
        counter = 0;
        potDisplay.setVisible(false);
        displayChips.setText("Chips: " + player.getChips());
        displayChipsCPU.setText("Chips: " + cpuPlayer.getChips());
    }

    /**
     * This method makes the hole cards each round for the player and the bot
     * @param deck this is the card deck
     * @param panel this is the panel it is on
     * @param player this is the user player
     * @param cpuPlayer this is the bot player
     * @param smallB this check who is smallBlind as they get the first card
     */
    private void makeHoleCards(CardDeck deck, Panel panel, PokerPlayer player, CPUPlayer cpuPlayer, boolean smallB) {
        player.addCards(deck.get(1)); 
        player.addCards(deck.get(3));
        cpuPlayer.addCards(deck.get(0));
        cpuPlayer.addCards(deck.get(2));
        BufferedImage image = null;
        int y = 0;
        for(int i = 0; i < 2; i++) {
            try {
                URL url = new URL(player.getCards().get(i).getImage());
                image = ImageIO.read(url);

            } catch(Exception e) {
            }
            ImageIcon image1 = new ImageIcon(image);
            ImageIcon image2 = new ImageIcon("Images/purple_back.png");
            makeCommunityCards(deck, panel, image1, cardX+y, cardY, i+10);
            makeCommunityCards(deck, panel, image2, cardX+y, cardY-600, i-10);
            y+=sizeX-3;
        } 
    }

    /**
     * This method performs the flop for the game
     * @param deck this is the card deck
     * @param panel this is the panel of the game
     * @param player the is the user
     */
    private void flop(CardDeck deck, Panel panel, PokerPlayer player) {
        BufferedImage image = null;
        int y = 0;
        for(int i = 0; i < 3; i++) {
            try {
                URL url = new URL(deck.get(5+i).getImage());
                image = ImageIO.read(url);
            } catch(Exception e) {
            }
            ImageIcon image1 = new ImageIcon(image);
            makeCommunityCards(deck, panel, image1, 325+y, 375, i+1);
            y+=sizeX-3;
            player.addCards(deck.get(5+i));
            cpuPlayer.addCards(deck.get(5+i));
        }
    }

    /**
     * This method performs the fourth card of the round or the turn
     * @param deck this is the card deck
     * @param panel this is the panel of the game
     * @param player this is the poker player
     */
    private void turn(CardDeck deck, Panel panel, PokerPlayer player) {
        BufferedImage image1 = null;
        try {
            URL url = new URL(deck.get(9).getImage());
            image1 = ImageIO.read(url);
        } catch (Exception e) {
        }
        ImageIcon image = new ImageIcon(image1);
        makeCommunityCards(deck, panel, image, 691, 375, 4);
        player.addCards(deck.get(9));
        cpuPlayer.addCards(deck.get(9));
    }

    /**
     * This method performs the fifth and last card of the round or the river
     * @param deck this is the card deck
     * @param panel this is the panel
     * @param player this is the poker player
     */
    private void river(CardDeck deck, Panel panel, PokerPlayer player) {
        BufferedImage image1 = null;
        try {
            URL url = new URL(deck.get(11).getImage());
            image1 = ImageIO.read(url);
        } catch(Exception e) {
        }
        ImageIcon image = new ImageIcon(image1);
        makeCommunityCards(deck, panel, image, 813, 375, 5);
        player.addCards(deck.get(11));
        cpuPlayer.addCards(deck.get(11));
    }

    /**
     * This is a method to make those lablels for each card, and resize it accordingly
     * @param deck this is the card deck
     * @param panel this is the panel to use
     * @param image this is the image for the card
     * @param x the x location of the card
     * @param y this is the y location of the card
     * @param number this is the number of the card we want to make
     */
    public void makeCommunityCards(CardDeck deck, Panel panel, ImageIcon image, int x, int y, int number) {
        Image image1 = getScaledImage(image.getImage(), sizeX, sizeY);
        image = new ImageIcon(image1);
        if(number == 10) {
            holeCard11 = new Label(image, sizeX, sizeY);
            holeCard11.setBounds(x, y, sizeX, sizeY);
            panel.add(holeCard11);
        } else if(number == 11) {
            holeCard12 = new Label(image, sizeX, sizeY);
            holeCard12.setBounds(x, y, sizeX, sizeY);
            panel.add(holeCard12);
        } else if(number == -10) {
            holeCard21 = new Label(image, sizeX, sizeY);
            holeCard21.setBounds(x, y, sizeX, sizeY);
            panel.add(holeCard21);
        } else if(number == -9) {
            holeCard22 = new Label(image, sizeX, sizeY);
            holeCard22.setBounds(x, y, sizeX, sizeY);
            panel.add(holeCard22);
        } 
        else if(number == 1){
            card1 = new Label(image, sizeX, sizeY);
            card1.setBounds(x, y, sizeX, sizeY);
            panel.add(card1);
        } else if(number == 2) {
            card2 = new Label(image, sizeX, sizeY);
            card2.setBounds(x, y, sizeX, sizeY);
            panel.add(card2);
        } else if(number == 3) {
            card3 = new Label(image, sizeX, sizeY);
            card3.setBounds(x, y, sizeX, sizeY);
            panel.add(card3);
        } else if(number == 4) {
            card4 = new Label(image, sizeX, sizeY);
            card4.setBounds(x, y, sizeX, sizeY);
            panel.add(card4);
        } else {
            card5 = new Label(image, sizeX, sizeY);
            card5.setBounds(x, y, sizeX, sizeY);
            panel.add(card5);
        } 
        panel.repaint();
    }

    /**
     * This image returns a scaled image
     * @param srcImg the image needed to be resized
     * @param w the width of the new card
     * @param h the height of the new card
     * @return the newly resized image
     */
    private static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * This method returns the string representation of the hand that a person has
     * @param panel this is the panel that everything is on
     * @param hand this is the hand
     * @return returns the label with the string
     */
    private JLabel printHandString1(Panel panel, ArrayList<Card> hand) {
        String info = Hands.getHandString(hand); 
        JLabel label = new JLabel(info);
        int cardX = (width-(sizeX*2))/2;
        int cardY = (height - sizeY) - 50;
        label.setBounds(cardX + 300, cardY - 30, 300, 50);
        Font font = new Font("Verdana", Font.PLAIN, 30);
        label.setFont(font);
        label.setVisible(true);
        panel.add(label);
        panel.repaint();
        return label;
    }

    /**
     * This method makes a button and puts it on the panel
     * @param panel this is the panel it is on
     * @param message the string on the button
     * @param sizeX this is the x size of the button
     * @param sizeY this is the y sie of the button
     * @param x this is the x location of the button
     * @param y this is the y location of the button
     * @return returns the button with it on the panel
     */
    public static JButton makeButton(Panel panel, String message, int sizeX, int sizeY,  int x, int y) {
        JButton button = new JButton();
        button.setText(message);
        button.setSize(sizeX,sizeY);
        button.setLocation(x,y);
        panel.add(button);
        button.setVisible(false);
        panel.repaint();
        return button;
    } 
}
