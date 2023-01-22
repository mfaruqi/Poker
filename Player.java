import java.util.*;
/**
 * This is a parent class to each individual players
 */
public abstract class Player {
    
    /**
     * This is the number of chips
     */
    private int chips;

    /**
     * This is the state of blind
     */
    private boolean smallBlind;

    /**
     * This is the hand the player has
     */
    private ArrayList<Card> hand;

    /**
     * This constructor intializes a player
     * @param chips the chips it has
     * @param sb state of blind
     */
    public Player(int chips, boolean sb) {
        this.chips = chips;
        smallBlind = sb;
        hand = new ArrayList<>();
    }

    /**
     * This method returns small blind or not
     * @return state of blind
     */
    public boolean getSmallBlind() {
        return smallBlind;
    }
    
    /**
     * This method adds a card
     * @param card the card to add
     */
    public void addCards(Card card) {
        hand.add(card);
    } 

    /**
     * This method sets small blind
     * @param smallBlind to change it to
     */
    public void setSmallBlind(boolean smallBlind) {
        this.smallBlind = smallBlind;
    }

    /**
     * This method returns the chips of the players
     * @return the chips
     */
    public int getChips() { 
        return chips;
    }

    /**
     * This method sets the chips of the player
     * @param chips the new chips to set it to
     */
    public void setChips(int chips) {
        this.chips = chips;
    }
    
    /**
     * This method gets the cards of the player
     * @return the list of cards
     */
    public ArrayList<Card> getCards() {
        return hand;
    }
}
